/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.util.OpenmrsUtil;

/**
 * utility methods specific to the radiology module
 */
public class RadiologyUtil {
    
    
    private static final Log log = LogFactory.getLog(RadiologyUtil.class);
    
    private static final String RADIOLOGY_HOME = "radiology";
    
    private static final String REPORT_HOME = "reports";
    
    private static final String TEMPLATE_HOME = "templates";
    
    /**
     * get the path to directory that stores templates
     * 
     * @return returns the folder of radiology report templates
     * 
     * @should should return valid directory
     * @should create repo if missing
     * @should throw exception if repo cannot be created
     */
    public static final File getTemplateRepository() {
        return getFullFolder(TEMPLATE_HOME);
    }
    
    /**
     * get the path to directory that stores reports
     * 
     * @return returns the folder containing radiology reports
     * 
     * @should should return valid directory
     * @should create repo if missing
     * @should throw exception if repo cannot be created
     */
    public static final File getReportRepository() {
        return getFullFolder(REPORT_HOME);
    }
    
    private static File getFullFolder(String child) {
        File folder = new File(getRadiologyHome() + File.separator + child);
        
        if (!folder.exists()) {
            log.warn("Repository " + folder.getAbsolutePath() + " doesn't exist.  Creating directories now.");
            folder.mkdirs();
        }
        if (!folder.isDirectory()) {
            throw new APIException("Repository is not a directory at: " + folder.getAbsolutePath());
        }
        return folder;
    }
    
    /**
     * get full path name to the radiology home directory
     * 
     * @return returns the absolute path of radiology home
     * 
     * @should return absolute path to radiology home
     */
    public static final String getRadiologyHome() {
        String openmrsHome = OpenmrsUtil.getApplicationDataDirectory();
        String lastChar = openmrsHome.substring(openmrsHome.length() - 1);
        String pathSeparator = File.separator;
        
        if (lastChar.equals(pathSeparator)) {
            openmrsHome = openmrsHome.substring(0, openmrsHome.length() - 1); // remove the last "/" or "\"
        }
        return openmrsHome + File.separator + RADIOLOGY_HOME;
    }
    
}
