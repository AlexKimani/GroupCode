//package org.openmrs.module.modulehiv.calculation.library;
//
//import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * Created by KIMANI on 6/4/2014.
// */
//@Component
//public class CommonDimensionLibrary {
//    @Autowired
//    private CommonCohortLibary commonCohortLibary;
//
//    public CohortDefinitionDimension gender(){
//        CohortDefinitionDimension sex=new CohortDefinitionDimension();
//        sex.setName("gender");
//        sex.addCohortDefinition("M",map(CommonCohortLibary.males()));
//        sex.addCohortDefinition("F", map(CommonCohortLibary.females()));
//        return sex;
//    }
//
//    public CohortDefinitionDimension standardAgeGroups(){
//        CohortDefinitionDimension age= new CohortDefinitionDimension();
//        age.setName();
//    }
//}
