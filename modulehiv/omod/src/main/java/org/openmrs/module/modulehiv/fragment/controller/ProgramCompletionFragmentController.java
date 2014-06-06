package org.openmrs.module.modulehiv.fragment.controller;

import org.openmrs.Encounter;
import org.openmrs.Form;
import org.openmrs.PatientProgram;
import org.openmrs.module.kenyacore.program.ProgramDescriptor;
import org.openmrs.module.kenyacore.program.ProgramManager;
import org.openmrs.module.kenyaemr.wrapper.Enrollment;
import org.openmrs.module.modulehiv.HIVBOOTCAMPWebContants;
import org.openmrs.ui.framework.annotation.FragmentParam;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;

/**
 * Created by KIMANI on 6/4/2014.
 */
public class ProgramCompletionFragmentController {
    public void controller(@FragmentParam("patientProgram") PatientProgram patientProgram,
                           @FragmentParam("showClinicalData") boolean showClinicalData,
                           @SpringBean ProgramManager programManager,
                           FragmentModel model) {

        ProgramDescriptor programDescriptor = programManager.getProgramDescriptor(patientProgram.getProgram());
        Form defaultCompletionForm = programDescriptor.getDefaultCompletionForm().getTarget();

        Enrollment enrollment = new Enrollment(patientProgram);

        // Might not be the default completion form, but should have the same encounter type
        Encounter encounter = enrollment.lastEncounter(defaultCompletionForm.getEncounterType());

        model.put("summaryFragment", programDescriptor.getFragments().get(HIVBOOTCAMPWebContants.PROGRAM_COMPLETION_SUMMARY_FRAGMENT));
        model.put("enrollment", patientProgram);
        model.put("encounter", encounter);
        model.put("showClinicalData", showClinicalData);
    }
}
