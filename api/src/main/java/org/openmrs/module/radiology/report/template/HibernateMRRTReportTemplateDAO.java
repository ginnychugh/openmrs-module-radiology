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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Hibernate specific MRRTReportTemplate related functions. This class should not be used directly. All
 * calls should go through the {@link org.openmrs.module.radiology.report.MRRTReportTEmplateService} methods.
 *
 * @see org.openmrs.module.radiology.order.MRRTReportTemplateDAO
 * @see org.openmrs.module.radiology.order.MRRTReportTemplateService
 */
class HibernateMRRTReportTemplateDAO implements MRRTReportTemplateDAO {
    
    
    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * Set session factory that allows us to connect to the database that Hibernate knows about.
     *
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * @see org.openmrs.module.radiology.report.template.MRRTReportTemplateService#getMRRTReportTemplate(Integer)
     */
    @Override
    public MRRTReportTemplate getMRRTReportTemplate(Integer templateId) {
        return (MRRTReportTemplate) sessionFactory.getCurrentSession()
                .get(MRRTReportTemplate.class, templateId);
    }
    
    /**
     * @see org.openmrs.module.radiology.report.template.MRRTReportTemplateService#getAllMRRTReportTemplates()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MRRTReportTemplate> getAllMRRTReportTemplates() {
        return sessionFactory.getCurrentSession()
                .createCriteria(MRRTReportTemplate.class)
                .list();
    }
    
    /**
     * @see org.openmrs.module.radiology.report.template.MRRTReportTemplateService#getSaveMRRTReportTemplate(MRRTReportTemplate)
     */
    @Override
    public MRRTReportTemplate saveMRRTReportTemplate(MRRTReportTemplate template) {
        sessionFactory.getCurrentSession()
                .save(template);
        return template;
    }
    
    /**
     * @see org.openmrs.module.radiology.report.template.MRRTReportTemplateService#purgeMRRTReportTemplate(MRRTReportTemplate)
     */
    @Override
    public void purgeMRRTReportTemplate(MRRTReportTemplate template) {
        sessionFactory.getCurrentSession()
                .delete(template);
    }
}
