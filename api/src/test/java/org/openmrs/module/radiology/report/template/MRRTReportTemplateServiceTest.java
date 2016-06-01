package org.openmrs.module.radiology.report.template;

import static org.hamcrest.core.Is.is;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

public class MRRTReportTemplateServiceTest extends BaseModuleContextSensitiveTest {
    
    
    private static final String TEST_DATASET =
            "org/openmrs/module/radiology/include/MRRTReportTemplateServiceTestDataset.xml";
    
    @Autowired
    private MRRTReportTemplateService templateService;
    
    @Before
    public void setup() throws Exception {
        executeDataSet(TEST_DATASET);
    }
    
    /**
    * @see MRRTReportTemplateService#getAllMRRTReportTemplates()
    * @verifies get a list of all templates
    */
    @Test
    public void getAllMRRTReportTemplates_shouldGetAListOfAllTemplates() throws Exception {
        List<MRRTReportTemplate> templates = templateService.getAllMRRTReportTemplates();
        
        Assert.assertNotNull(templates);
        Assert.assertEquals(templates.size(), 3);
    }
    
    /**
    * @see MRRTReportTemplateService#getMRRTReportTemplate(Integer)
    * @verifies get template with given id
    */
    @Test
    public void getMRRTReportTemplate_shouldGetTemplateWithGivenId() throws Exception {
        MRRTReportTemplate template = templateService.getMRRTReportTemplate(1);
        
        Assert.assertNotNull(template);
        Assert.assertThat(template.getTitle(), is("title1"));
        Assert.assertThat(template.getCharset(), is("UTF-8"));
        Assert.assertThat(template.getPath(), is("test/test.html"));
    }
    
    /**
    * @see MRRTReportTemplateService#purgeMRRTReportTemplate(MRRTReportTemplate)
    * @verifies delete report from database
    */
    @Test
    public void purgeMRRTReportTemplate_shouldDeleteReportFromDatabase() throws Exception {
        MRRTReportTemplate template = templateService.getMRRTReportTemplate(2);
        
        Assert.assertNotNull(template);
        Assert.assertEquals(templateService.getAllMRRTReportTemplates()
                .size(),
            3);
        
        templateService.purgeMRRTReportTemplate(template);
        Assert.assertEquals(templateService.getAllMRRTReportTemplates()
                .size(),
            2);
        Assert.assertNull(templateService.getMRRTReportTemplate(2));
    }
    
    /**
    * @see MRRTReportTemplateService#saveMRRTReportTemplate(MRRTReportTemplate)
    * @verifies save report
    */
    @Test
    public void saveMRRTReportTemplate_shouldSaveReport() throws Exception {
        ClassLoader loader = getClass().getClassLoader();
        File file = new File(loader.getResource("TestMRRTReportTemplate.html")
                .getFile());
        MRRTReportTemplate template = MRRTReportTemplateParser.parseTemplateFile(file);
        
        MRRTReportTemplate savedTemplate = templateService.saveMRRTReportTemplate(template);
        
        Assert.assertNotNull(savedTemplate);
        Assert.assertEquals(templateService.getAllMRRTReportTemplates()
                .size(),
            4);
        Assert.assertThat(savedTemplate.getTitle(), is("Cardiac MRI: Adenosine Stress Protocol"));
    }
}
