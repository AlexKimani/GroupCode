package org.openmrs.module.vmmc.fragment.controller.program.vmmc;

import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.PatientProgram;
import org.openmrs.module.kenyaemr.Dictionary;
import org.openmrs.module.kenyaemr.wrapper.EncounterWrapper;
import org.openmrs.ui.framework.annotation.FragmentParam;
import org.openmrs.ui.framework.fragment.FragmentModel;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by KIMANI on 6/5/2014.
 */
public class VmmcEnrollmentFragmentController {
    public String controller(@FragmentParam("patientProgram") PatientProgram enrollment,
                             @FragmentParam(value = "encounter", required = false) Encounter encounter,
                             @FragmentParam("showClinicalData") boolean showClinicalData,
                             FragmentModel model) {

        Map<String, Object> dataPoints = new LinkedHashMap<String, Object>();
        dataPoints.put("Enrolled", enrollment.getDateEnrolled());

        if (encounter != null) {
            EncounterWrapper wrapper = new EncounterWrapper(encounter);
            Obs o = wrapper.firstObs(Dictionary.getConcept(Dictionary.METHOD_OF_ENROLLMENT));

            if (o != null) {
                dataPoints.put("Entry point", o.getValueCoded());
            }
        }

        model.put("dataPoints", dataPoints);
        return "view/dataPoints";
    }
}
