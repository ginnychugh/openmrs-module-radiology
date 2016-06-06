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

/**
 * MRRTReportTemplate-related database functions
 * 
 * @see org.openmrs.module.radiology.report.template.MRRTReportTemplateService
 */
interface MRRTReportTemplateDAO {
    
    
    /**
     * @see org.openmrs.module.radiology.report.template.MRRTReportTemplateService#getMRRTReportTemplate(Integer)
     */
    public MRRTReportTemplate getMRRTReportTemplate(Integer templateId);
    
    /**
     * @see org.openmrs.module.radiology.report.template.MRRTReportTemplateService#getAllMRRTReportTemplates()
     */
    public List<MRRTReportTemplate> getAllMRRTReportTemplates();
    
    /**
     * @see org.openmrs.module.radiology.report.template.MRRTReportTemplateService#getSaveMRRTReportTemplate(MRRTReportTemplate)
     */
    public MRRTReportTemplate saveMRRTReportTemplate(MRRTReportTemplate template);
    
    /**
     * @see org.openmrs.module.radiology.report.template.MRRTReportTemplateService#purgeMRRTReportTemplate(MRRTReportTemplate)
     */
    public void purgeMRRTReportTemplate(MRRTReportTemplate template);
}
