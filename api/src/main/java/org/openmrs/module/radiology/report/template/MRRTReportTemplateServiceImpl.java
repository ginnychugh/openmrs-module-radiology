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

import org.openmrs.api.impl.BaseOpenmrsService;
import org.springframework.beans.factory.annotation.Autowired;

class MRRTReportTemplateServiceImpl extends BaseOpenmrsService implements MRRTReportTemplateService {
    
    
    @Autowired
    private MRRTReportTemplateDAO dao;
    
    /**
     * @see MRRTReportTemplateService#getMRRTReportTemplate(Integer) 
     */
    @Override
    public MRRTReportTemplate getMRRTReportTemplate(Integer id) {
        return dao.getMRRTReportTemplate(id);
    }
    
    /**
     * @see MRRTReportTemplateService#getAllMRRTReportTemplates()
     */
    @Override
    public List<MRRTReportTemplate> getAllMRRTReportTemplates() {
        return dao.getAllMRRTReportTemplates();
    }
    
    /**
     * @see MRRTReportTemplateService#saveMRRTReportTemplate(MRRTReportTemplate)
     */
    @Override
    public MRRTReportTemplate saveMRRTReportTemplate(MRRTReportTemplate template) {
        return dao.saveMRRTReportTemplate(template);
    }
    
    /**
     * @see MRRTReportTemplateService#purgeMRRTReportTemplate(MRRTReportTemplate)
     */
    @Override
    public void purgeMRRTReportTemplate(MRRTReportTemplate template) {
        dao.purgeMRRTReportTemplate(template);
    }
}
