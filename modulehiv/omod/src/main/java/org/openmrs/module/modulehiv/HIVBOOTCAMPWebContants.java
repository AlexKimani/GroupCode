package org.openmrs.module.modulehiv;

/**
 * Created by KIMANI on 5/27/2014.
 */
public class HIVBOOTCAMPWebContants {
    /**
     * Time in milliseconds to lockout an IP or user after repeated
     * failed login attempts
     */
    public static final int FAILED_LOGIN_LOCKOUT_TIME = 300000; // 5 minutes

    /**
     * Name of session attribute for temporary reset passwords
     */
    public static final String SESSION_ATTR_RESET_PASSWORD = "resetPassword";

    /**
     * Page model attributes
     */
    public static final String MODEL_ATTR_CURRENT_PATIENT = "currentPatient";
    public static final String MODEL_ATTR_CURRENT_VISIT = "currentVisit";
    public static final String MODEL_ATTR_ACTIVE_VISIT = "activeVisit";

    /**
     * Fragment identifiers
     */
    public static final String PROGRAM_ENROLLMENT_SUMMARY_FRAGMENT = "enrollment-summary";
    public static final String PROGRAM_CARE_PANEL_FRAGMENT = "care-panel";
    public static final String PROGRAM_COMPLETION_SUMMARY_FRAGMENT = "completion-summary";
}
