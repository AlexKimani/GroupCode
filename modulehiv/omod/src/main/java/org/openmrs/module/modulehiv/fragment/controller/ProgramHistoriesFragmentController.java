package org.openmrs.module.modulehiv.fragment.controller;

import org.openmrs.Patient;
import org.openmrs.module.kenyacore.program.ProgramDescriptor;
import org.openmrs.module.kenyacore.program.ProgramManager;
import org.openmrs.ui.framework.annotation.FragmentParam;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by KIMANI on 6/4/2014.
 */
public class ProgramHistoriesFragmentController {
    public void controller(FragmentModel model,
                           @FragmentParam("patient") Patient patient,
                           @FragmentParam("showClinicalData") boolean showClinicalData,
                           @SpringBean ProgramManager programManager) {

        List<ProgramDescriptor> programs = new ArrayList<ProgramDescriptor>();

        if (!patient.isVoided()) {
            Collection<ProgramDescriptor> activePrograms = programManager.getPatientActivePrograms(patient);
            Collection<ProgramDescriptor> eligiblePrograms = programManager.getPatientEligiblePrograms(patient);

            // Display active programs on top
            programs.addAll(activePrograms);

            // Don't add duplicates for programs for which patient is both active and eligible
            for (ProgramDescriptor descriptor : eligiblePrograms) {
                if (!programs.contains(descriptor)) {
                    programs.add(descriptor);
                }
            }
        }

        model.addAttribute("patient", patient);
        model.addAttribute("programs", programs);
        model.addAttribute("showClinicalData", showClinicalData);
    }
}
