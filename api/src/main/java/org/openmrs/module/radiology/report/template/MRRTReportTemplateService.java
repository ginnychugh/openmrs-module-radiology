/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.report.template;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service for managing MRRTReportTemplates 
 */
@Transactional
public interface MRRTReportTemplateService extends OpenmrsService {
    
    
    /**
     * get a template with a given id 
     * 
     * @param id the template id
     * @return template with given id
     * 
     * @should get template with given id
     */
    public MRRTReportTemplate getMRRTReportTemplate(Integer id);
    
    /**
     * gets a list of all templates in the system
     * 
     * @return list of templates
     * 
     * @should get a list of all templates
     */
    public List<MRRTReportTemplate> getAllMRRTReportTemplates();
    
    /**
     *  saves a new or existing template
     *  
     *  @param template the template to save
     *  @return the saved template 
     *  @should save report
     */
    public MRRTReportTemplate saveMRRTReportTemplate(MRRTReportTemplate template);
    
    /**
     * delete a template from the database
     * 
     * @param template the template that is been deleted
     * @should delete report from database
     */
    public void purgeMRRTReportTemplate(MRRTReportTemplate template);
}
