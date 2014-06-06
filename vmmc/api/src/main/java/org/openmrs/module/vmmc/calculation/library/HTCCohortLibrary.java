package org.openmrs.module.vmmc.calculation.library;

import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.Program;
import org.openmrs.api.PatientSetService;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.common.SetComparator;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.vmmc.Dictionary;
import org.openmrs.module.vmmc.Metadata.VmmcCommonMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * Created by KIMANI on 6/4/2014.
 */
@Component
public class HTCCohortLibrary {
   @Autowired
    private VmmcCommonCohortLibary commonCohorts;
    public CohortDefinition referredFrom(Concept... entryPoints) {
        EncounterType hivEnrollEncType = MetadataUtils.existing(EncounterType.class, VmmcCommonMetadata._EncounterType.ENROLL);
        Concept methodOfEnrollment = Dictionary.getConcept(Dictionary.METHOD_OF_ENROLLMENT);

        CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
        cd.setName("referred from");
        cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
        cd.setQuestion(methodOfEnrollment);
        cd.setValueList(Arrays.asList(entryPoints));
        cd.setOperator(SetComparator.IN);
        cd.setEncounterTypeList(Collections.singletonList(hivEnrollEncType));
        return cd;
    }

    public CohortDefinition referredNotFrom(Concept... entryPoints) {
        EncounterType hivEnrollEncType = MetadataUtils.existing(EncounterType.class, VmmcCommonMetadata._EncounterType.ENROLL);
        Concept methodOfEnrollment = Dictionary.getConcept(Dictionary.METHOD_OF_ENROLLMENT);

        CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
        cd.setName("referred not from");
        cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
        cd.setQuestion(methodOfEnrollment);
        cd.setValueList(Arrays.asList(entryPoints));
        cd.setOperator(SetComparator.NOT_IN);
        cd.setEncounterTypeList(Collections.singletonList(hivEnrollEncType));
        return cd;
    }


    public CohortDefinition enrolled() {
        return commonCohorts.enrolled(MetadataUtils.existing(Program.class, VmmcCommonMetadata._Program.VMMC));
    }


    public CohortDefinition enrolledExcludingTransfers() {
        return commonCohorts.enrolledExcludingTransfers(MetadataUtils.existing(Program.class, VmmcCommonMetadata._Program.VMMC));
    }


}
