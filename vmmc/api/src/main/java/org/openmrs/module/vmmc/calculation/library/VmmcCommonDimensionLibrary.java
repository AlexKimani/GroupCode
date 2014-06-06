package org.openmrs.module.vmmc.calculation.library;

import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.openmrs.module.kenyacore.report.ReportUtils.map;

/**
 * Created by KIMANI on 6/4/2014.
 */
@Component
public class VmmcCommonDimensionLibrary {
    @Autowired
    private VmmcCommonCohortLibary VmmcCommonCohortLibary;

    public CohortDefinitionDimension gender(){
        CohortDefinitionDimension dim=new CohortDefinitionDimension();
        dim.setName("gender");
        dim.addCohortDefinition("M",map(VmmcCommonCohortLibary.males()));
        dim.addCohortDefinition("F", map(VmmcCommonCohortLibary.females()));
        return dim;
    }

    public CohortDefinitionDimension standardAgeGroups() {
        CohortDefinitionDimension dim = new CohortDefinitionDimension();
        dim.setName("age groups (<1, <15, 15+)");
        dim.addParameter(new Parameter("onDate", "Date", Date.class));
        dim.addCohortDefinition("<1", map(VmmcCommonCohortLibary.agedAtMost(0), "effectiveDate=${onDate}"));
        dim.addCohortDefinition("<15", map(VmmcCommonCohortLibary.agedAtMost(14), "effectiveDate=${onDate}"));
        dim.addCohortDefinition("15+", map(VmmcCommonCohortLibary.agedAtLeast(15), "effectiveDate=${onDate}"));
        return dim;
    }
}
