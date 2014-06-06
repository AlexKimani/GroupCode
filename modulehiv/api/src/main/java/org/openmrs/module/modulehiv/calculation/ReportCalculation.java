package org.openmrs.module.modulehiv.calculation;

import org.openmrs.Program;
import org.openmrs.calculation.BaseCalculation;
import org.openmrs.calculation.patient.PatientCalculation;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.module.kenyacore.calculation.BooleanResult;
import org.openmrs.module.kenyacore.calculation.Filters;
import org.openmrs.module.kenyaemr.metadata.TbMetadata;
import org.openmrs.module.metadatadeploy.MetadataUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by KIMANI on 5/28/2014.
 */
public class ReportCalculation extends BaseCalculation implements PatientCalculation{
    @Override
    public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> stringObjectMap, PatientCalculationContext patientCalculationContext){

        Program hivProgram = MetadataUtils.existing(Program.class, TbMetadata._Program.TB);
        Set<Integer> hivPositive = Filters.inProgram(hivProgram, cohort, patientCalculationContext);
        CalculationResultMap test=new CalculationResultMap();
        for (Integer i: cohort){
            if (hivPositive.contains(i)){
                test.put(i, new BooleanResult(true, this));
            }
        }
        return test;
//
    }
}
