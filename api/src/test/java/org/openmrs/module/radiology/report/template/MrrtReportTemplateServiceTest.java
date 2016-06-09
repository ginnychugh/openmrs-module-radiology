/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.report.template;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

public class MrrtReportTemplateServiceTest extends BaseModuleContextSensitiveTest {
    
    private static final String TEST_DATASET = "org/openmrs/module/radiology/include/MrrtReportTemplateServiceTestDataset.xml";
    
    private static final int EXISTING_TEMPLATE_ID = 1;
    
    @Autowired
    private MrrtReportTemplateService mrrtReportTemplateService;
    
    
    @Before
    public void setUp() throws Exception {
        executeDataSet(TEST_DATASET);
    }
    
    /**
    * @see MrrtReportTemplateService#getMrrtReportTemplate(Integer)
    * @verifies get template with given id
    */
    @Test
    public void getMrrtReportTemplate_shouldGetTemplateWithGivenId() throws Exception {
        MrrtReportTemplate existingTemplate = mrrtReportTemplateService.getMrrtReportTemplate(EXISTING_TEMPLATE_ID);
        
        Assert.assertNotNull(existingTemplate);
        Assert.assertEquals(existingTemplate.getCharset(), "UTF-8");
        Assert.assertEquals(existingTemplate.getTitle(), "title1");
        Assert.assertEquals(existingTemplate.getLanguage(), "en");
    }
    
    /**
    * @see MrrtReportTemplateService#purgeMrrtReportTemplate(MrrtReportTemplate)
    * @verifies delete report from database
    */
    @Test
    public void purgeMrrtReportTemplate_shouldDeleteReportFromDatabase() throws Exception {
        MrrtReportTemplate template = mrrtReportTemplateService.getMrrtReportTemplate(EXISTING_TEMPLATE_ID);
        
        Assert.assertNotNull(template);
        mrrtReportTemplateService.purgeMrrtReportTemplate(template);
        
        MrrtReportTemplate deleted = mrrtReportTemplateService.getMrrtReportTemplate(EXISTING_TEMPLATE_ID);
        Assert.assertNull(deleted); // should be null since it's been deleted
    }
    
    /**
    * @see MrrtReportTemplateService#saveMrrtReportTemplate(MrrtReportTemplate)
    * @verifies save report
    */
    @Test
    public void saveMrrtReportTemplate_shouldSaveReport() throws Exception {
       MrrtReportTemplate template = new MrrtReportTemplate();
       
       template.setTemplateId(45);
       template.setTitle("sample title");
       template.setDescription("sample description");
       
       mrrtReportTemplateService.saveMrrtReportTemplate(template);
       MrrtReportTemplate saved = mrrtReportTemplateService.getMrrtReportTemplate(45);
       
       Assert.assertNotNull(saved);
       Assert.assertEquals(saved.getTitle(), "sample title");
       Assert.assertEquals(saved.getDescription(), "sample description");
    }
}