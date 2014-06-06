package org.openmrs.module.vmmc.calculation.library;

import org.openmrs.calculation.BaseCalculation;
import org.openmrs.calculation.patient.PatientCalculation;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.module.kenyacore.calculation.BooleanResult;
import org.openmrs.module.kenyacore.calculation.Calculations;

import java.util.Collection;
import java.util.Map;

/**
 * Created by KIMANI on 6/3/2014.
 */
public class EnrollCalculation extends BaseCalculation implements PatientCalculation {

    @Override
    public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> stringObjectMap, PatientCalculationContext context) {
      CalculationResultMap el =new CalculationResultMap();
        CalculationResultMap genders = Calculations.genders(cohort, context);
        for (Integer ptid: cohort){

            boolean eligible ="M".equals(genders.get(ptid).getValue());
            el.put(ptid, new BooleanResult(eligible, this));
        }



        return el;
    }
}
