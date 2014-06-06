package org.openmrs.module.vmmc.calculation;


import org.openmrs.Program;
import org.openmrs.calculation.BaseCalculation;
import org.openmrs.calculation.patient.PatientCalculation;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.module.kenyacore.calculation.BooleanResult;
import org.openmrs.module.kenyacore.calculation.Filters;
import org.openmrs.module.kenyaemr.metadata.HivMetadata;
import org.openmrs.module.metadatadeploy.MetadataUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by KIMANI on 5/27/2014.
 */
public class EligibleForExampleProgramCalculation extends BaseCalculation implements PatientCalculation{

    @Override
    public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> stringObjectMap, PatientCalculationContext patientCalculationContext) {


        Program hivProgram = MetadataUtils.existing(Program.class, HivMetadata._Program.HIV);
        Set<Integer> hivPositive = Filters.inProgram(hivProgram, cohort, patientCalculationContext);

        CalculationResultMap ret = new CalculationResultMap();
         for (Integer i: cohort){
             if (hivPositive.contains(i)){
                 ret.put(i, new BooleanResult(true, this));
             }
         }
        return ret;
    }
}
