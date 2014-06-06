package org.openmrs.module.vmmc.calculation.vmmc;

import org.openmrs.Program;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.module.kenyacore.calculation.Calculations;
import org.openmrs.module.kenyacore.calculation.Filters;
import org.openmrs.module.kenyaemr.calculation.BaseEmrCalculation;

import java.util.Collection;
import java.util.Map;

/**
 * Created by KIMANI on 5/29/2014.
 */
public class InProgramCalculation extends BaseEmrCalculation{
    @Override
    public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> params, PatientCalculationContext context) {
        Program program = (params != null && params.containsKey("program")) ? (Program) params.get("program") : null;

        return passing(Calculations.activeEnrollment(program, Filters.alive(cohort, context), context));
    }
}
