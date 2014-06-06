package org.openmrs.module.vmmc.fragment.controller.program;

import org.openmrs.Patient;
import org.openmrs.module.kenyacore.UiResource;
import org.openmrs.module.kenyacore.program.ProgramDescriptor;
import org.openmrs.module.kenyacore.program.ProgramManager;
import org.openmrs.module.vmmc.VmmcWebConstants;
import org.openmrs.ui.framework.annotation.FragmentParam;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by KIMANI on 6/5/2014.
 */
public class ProgramCarePanelFragmentController {
    public void controller(FragmentModel model,
                           @FragmentParam("patient") Patient patient,
                           @FragmentParam("complete") boolean complete,
                           @FragmentParam("activeOnly") boolean activeOnly,
                           @SpringBean ProgramManager programManager) {

        List<UiResource> carePanels = new ArrayList<UiResource>();

        Collection<ProgramDescriptor> programs = activeOnly
                ? programManager.getPatientActivePrograms(patient)
                : programManager.getPatientPrograms(patient);

        for (ProgramDescriptor programDescriptor : programs) {
            carePanels.add(programDescriptor.getFragments().get(VmmcWebConstants.PROGRAM_CARE_PANEL_FRAGMENT));
        }

        model.addAttribute("patient", patient);
        model.addAttribute("carePanels", carePanels);
        model.addAttribute("complete", complete);
    }
}
