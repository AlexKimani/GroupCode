package org.openmrs.module.modulehiv.metadata;

import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.openmrs.module.modulehiv.Dictionary;
import org.springframework.stereotype.Component;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.*;

/**
 * Created by KIMANI on 5/26/2014.
 */
@Component
public class HIVBOOTCAMPMetadata  extends AbstractMetadataBundle {
    public static class _EncounterType {
        public static final String  CIRCUMCISION = "56f375c9-aee8-401f-8c64-15ad28836e8c";
        public static final String  ENROLL = "6b6e3928-858e-4061-94bc-920f116bc5a7";
        public static final String  COMPLETE = "dabeac30-4bfa-444d-8dca-eeaeec2a5a70";
    }
//    public static class _PatientIdentifierType {
//        public static final String  CLIENTNUMBER="e3c42206-79b8-4f1e-b5e3-2e1861d36fcf";
//        public static final String THEATRE ="a2cbc2ac-31eb-4eb7-9318-8371fd076746";
//    }
     public static class _Program{
    public static final String VMMC = "a29b4ad2-d418-4395-bc9e-87633ee8025a";
}
    public static class _Form {
        public static final String CIRCUMCISION = "6a5810f0-5312-49ee-ac2f-045e85d81063";
        public static final String ENROLL = "1da9e17a-40eb-4712-be74-31fd5c5d39ba";
        public static final String COMPLETE = "a5d38df8-3e48-47f0-ad64-39ce22b41082";
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

        install(program("Surgical Program","Circumcision Enroll",Dictionary.VMMC, _Program.VMMC));

    }
}
