package org.openmrs.module.vmmc.Metadata;

import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.openmrs.module.vmmc.Dictionary;
import org.springframework.stereotype.Component;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.encounterType;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.form;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.program;

/**
 * Created by KIMANI on 6/4/2014.
 */
@Component
public class VmmcCommonMetadata extends AbstractMetadataBundle{
    public static class _EncounterType {
        public static final String  CIRCUMCISION = "b26ae96f-8deb-4f2e-bf82-be9bcea40350";
        public static final String  ENROLL = "37e1bf6e-3365-4b5a-a4e7-908f4f129b95";
        public static final String  COMPLETE = "f1e8fe2d-531b-486b-b88a-8343a7385d4b";
    }
    //    public static class _PatientIdentifierType {
//        public static final String  CLIENTNUMBER="e3c42206-79b8-4f1e-b5e3-2e1861d36fcf";
//        public static final String THEATRE ="a2cbc2ac-31eb-4eb7-9318-8371fd076746";
//    }
    public static class _Program{
        public static final String VMMC = "70e3f3fb-f45b-4a5d-b2db-8c7fe13812c3";
    }
    public static class _Form {
        public static final String CIRCUMCISION = "e3659cbd-102b-48c9-96a0-1c5744ecd3f2";
        public static final String ENROLL = "217f6a14-345a-4490-a66e-ee9d4b3b8c3d";
        public static final String COMPLETE = "3734dc52-eee0-4f1c-a15c-427397f4a65d";
    }

    /**
     * @see org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle#install()
     */
    @Override
    public void install() {
        install(encounterType("Circumcision encounter", "Visit form for male circumcision", _EncounterType.CIRCUMCISION));
        install(encounterType("Enroll encounter", "Enrollment form for male circumcision", _EncounterType.ENROLL));
        install(encounterType("Completion encounter", "Completion form for male circumcision", _EncounterType.COMPLETE));

//        install(patientIdentifierType("Client Identifier type","Identifier for patient", _PatientIdentifierType.CLIENTNUMBER, Integer size, true,"e3c42206-79b8-4f1e-b5e3-2e1861d36fcf"));
//        install(patientIdentifierType("Theatre Identifier type","Identifier for theatre patient", _PatientIdentifierType.THEATRE, Integer, true));

        install(form("Circumcision form", null, _EncounterType.CIRCUMCISION, "1", _Form.CIRCUMCISION));
        install(form("Enrollment form", null, _EncounterType.ENROLL, "1", _Form.ENROLL));
        install(form("Completion form", null, _EncounterType.COMPLETE, "1", _Form.COMPLETE));
        //install(form("Enro;;ment form", null, _PatientIdentifierType.CLIENTNUMBER, "1", _Form.ENROLL));

        install(program("Surgical Program","Circumcision Enroll", Dictionary.VMMC, _Program.VMMC));

    }
}
