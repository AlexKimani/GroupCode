package org.openmrs.module.modulehiv.report;

import org.openmrs.PatientIdentifierType;

import org.openmrs.module.modulehiv.calculation.EntryCalculation;
import org.openmrs.module.kenyacore.report.CohortReportDescriptor;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyacore.report.builder.CalculationReportBuilder;
import org.openmrs.module.kenyacore.report.data.patient.definition.CalculationDataDefinition;
import org.openmrs.module.kenyaemr.calculation.library.hiv.art.InitialArtStartDateCalculation;
import org.openmrs.module.kenyaemr.calculation.library.hiv.art.IsTransferInCalculation;
import org.openmrs.module.kenyaemr.calculation.library.hiv.art.IsTransferOutCalculation;
import org.openmrs.module.kenyaemr.calculation.library.hiv.art.TransferInDateCalculation;
import org.openmrs.module.kenyaemr.metadata.HivMetadata;
import org.openmrs.module.kenyaemr.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.kenyaemr.reporting.data.converter.CalculationResultConverter;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.*;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.springframework.stereotype.Component;


/**
* Created by agnes on 5/28/14.
*/
@Component
@Builds("modulehiv.common.report.bootcamp1")
public class ReportFields extends CalculationReportBuilder {

    @Override
    protected void addColumns(CohortReportDescriptor report, PatientDataSetDefinition dsd) {

        PatientIdentifierType upn = MetadataUtils.existing(PatientIdentifierType.class, HivMetadata._PatientIdentifierType.UNIQUE_PATIENT_NUMBER);
        DataConverter identifierFormatter = new ObjectFormatter("{identifier}");
        DataDefinition identifierDef = new ConvertedPatientDataDefinition("identifier", new PatientIdentifierDataDefinition(upn.getName(), upn), identifierFormatter);

        DataConverter nameFormatter = new ObjectFormatter("{familyName}, {givenName}");
        DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), nameFormatter);

        dsd.addColumn("id", new PatientIdDataDefinition(), "");
        dsd.addColumn("Name", nameDef, "");
        dsd.addColumn(upn.getName(), identifierDef, "");
        dsd.addColumn("Birth date", new BirthdateDataDefinition(), "", new BirthdateConverter());
        dsd.addColumn("Sex", new GenderDataDefinition(), "");
        dsd.addColumn("ARV Start Date", new CalculationDataDefinition("ARV Start Date", new InitialArtStartDateCalculation()), "", new CalculationResultConverter());
        dsd.addColumn("Transfer In", new CalculationDataDefinition("Transfer In", new IsTransferInCalculation()), "", new CalculationResultConverter());
        dsd.addColumn("Date Transferred In", new CalculationDataDefinition("Date Transferred In", new TransferInDateCalculation()), "", new CalculationResultConverter());
        dsd.addColumn("Transfer Out", new CalculationDataDefinition("Transfer Out", new IsTransferOutCalculation()), "", new CalculationResultConverter());
        dsd.addColumn("Entry Point", new CalculationDataDefinition("Entry Point", new EntryCalculation()), "", new CalculationResultConverter());
        //dsd.addColumn("Alive?", new CalculationDataDefinition("Alive?", new AliveCalculation()), "", new CalculationResultConverter());


    }                                }