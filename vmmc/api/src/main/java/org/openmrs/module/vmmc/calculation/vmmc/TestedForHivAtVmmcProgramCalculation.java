package org.openmrs.module.vmmc.calculation.vmmc;

import org.openmrs.Concept;
import org.openmrs.PatientProgram;
import org.openmrs.Program;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResult;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.module.kenyacore.calculation.*;
import org.openmrs.module.kenyaemr.calculation.EmrCalculationUtils;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.vmmc.Dictionary;
import org.openmrs.module.vmmc.Metadata.VmmcCommonMetadata;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Created by KIMANI on 6/6/2014.
 */
public class TestedForHivAtVmmcProgramCalculation extends AbstractPatientCalculation {
    @Override
    public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> stringObjectMap, PatientCalculationContext context) {
        Program vmmcProgram= MetadataUtils.existing(Program.class, VmmcCommonMetadata._Program.VMMC);

        Set<Integer> alivePatients= Filters.alive(cohort, context);
        CalculationResultMap activePatientPrograms= Calculations.activeEnrollment(vmmcProgram, alivePatients, context);
        Set<Integer> aliveVmmcPatients= CalculationUtils.patientsThatPass(activePatientPrograms);

        Concept hivStatusConcept= Dictionary.getConcept(Dictionary.HIV_STATUS);
        Concept hivTestDate = Dictionary.getConcept(Dictionary.DATE_OF_HIV_DIAGNOSIS);

        CalculationResultMap lastHivStatusObss = Calculations.lastObs(hivStatusConcept, aliveVmmcPatients, context);
        CalculationResultMap lastHivTestDateObss = Calculations.lastObs(hivTestDate, aliveVmmcPatients, context);

        CalculationResultMap vmmc =new CalculationResultMap();

        for (Integer ptId : cohort) {
            Concept lastHivStatus = EmrCalculationUtils.codedObsResultForPatient(lastHivStatusObss, ptId);
            Date lastHivTestDate = EmrCalculationUtils.datetimeObsResultForPatient(lastHivTestDateObss, ptId);

            CalculationResult activePatientProgram = activePatientPrograms.get(ptId);

            boolean qualified = false;
            if (aliveVmmcPatients.contains(ptId)
                    && (lastHivStatus != null &&
                    (lastHivStatus.equals(Dictionary.getConcept(Dictionary.POSITIVE))
                            || lastHivStatus.equals(Dictionary.getConcept(Dictionary.NEGATIVE))))
                    && (activePatientProgram != null)) {
                Date enrollmentDate = ((PatientProgram) activePatientProgram.getValue()).getDateEnrolled();

                           }
            vmmc.put(ptId, new BooleanResult(qualified, this, context));
        }



        return vmmc;
    }
}
