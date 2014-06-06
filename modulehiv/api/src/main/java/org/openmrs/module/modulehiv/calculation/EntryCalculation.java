    package org.openmrs.module.modulehiv.calculation;

    import org.openmrs.Concept;
    import org.openmrs.calculation.BaseCalculation;
    import org.openmrs.calculation.patient.PatientCalculation;
    import org.openmrs.calculation.patient.PatientCalculationContext;
    import org.openmrs.calculation.result.CalculationResultMap;
    import org.openmrs.calculation.result.SimpleResult;
    import org.openmrs.module.kenyacore.calculation.Calculations;
    import org.openmrs.module.kenyaemr.Dictionary;
    import org.openmrs.module.kenyaemr.calculation.EmrCalculationUtils;

    import java.util.Collection;
    import java.util.Map;

    /**
     * Created by KIMANI on 5/29/2014.
     */
    public class EntryCalculation extends BaseCalculation implements PatientCalculation {

        @Override
        public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> stringObjectMap, PatientCalculationContext context) {
            CalculationResultMap rm = Calculations.lastObs(Dictionary.getConcept(Dictionary.METHOD_OF_ENROLLMENT), cohort, context);
            CalculationResultMap bs=new CalculationResultMap();
            for (Integer tt: cohort){
                Concept entryPoint= EmrCalculationUtils.codedObsResultForPatient(rm,tt);
                   if (rm.containsKey(tt)){
                         bs.put(tt, new SimpleResult(entryPoint, this, context));
                   }
            }
            return bs;
        }
    }
