/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.report.template;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.module.radiology.RadiologyUtil;
import org.openmrs.util.OpenmrsUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * in charge of import, creating and deleting templates from system
 */
public class MRRTReportTemplateManager {
    
    
    private static final Log log = LogFactory.getLog(MRRTReportTemplateManager.class);
    
    @Autowired
    private static MRRTReportTemplateService templateService;
    
    /**
     * import an mrrt template file 
     * 
     *  @return returns imported template object
     *  
     *  @should return imported template object
     *  @should throw exception on bad templates
     */
    public static MRRTReportTemplate importMRRTReportTemplate(File file) throws APIException, IOException {
        File templateRepo = RadiologyUtil.getTemplateRepository();
        MRRTReportTemplate template = null;
        
        if (!file.exists()) {
            throw new IOException("No template " + file.getAbsolutePath() + " found");
        }
        
        if (templateRepo.exists() && templateRepo.isDirectory()) {
            try {
                template = MRRTReportTemplateParser.parseTemplateFile(file);
                // if parser had no trouble parsing the file and creating the template object,
                // copy the template file to the template repo
                String destination = copyFileToRepo(file);
                template.setPath(destination);
                templateService.saveMRRTReportTemplate(template);
            }
            catch (MRRTReportTemplateException e) {
                e.printStackTrace();
            }
            catch (FileNotFoundException e) {
                log.error(e);
            }
            catch (IOException e) {
                log.error(e);
            }
        }
        
        else {
            throw new APIException("No directory " + templateRepo.getAbsolutePath() + " found");
        }
        return template;
    }
    
    private static final String copyFileToRepo(File file) throws FileNotFoundException, IOException {
        String desFilePath = RadiologyUtil.getTemplateRepository()
                .getAbsolutePath() + File.separator + file.getName();
        File destinationFile = new File(desFilePath);
        
        if (destinationFile.exists()) {
            // still to decide what to do if file already exist in system
        }
        
        InputStream in = new FileInputStream(file);
        OutputStream os = new FileOutputStream(destinationFile);
        OpenmrsUtil.copyFile(in, os);
        
        return destinationFile.getAbsolutePath();
    }
}
