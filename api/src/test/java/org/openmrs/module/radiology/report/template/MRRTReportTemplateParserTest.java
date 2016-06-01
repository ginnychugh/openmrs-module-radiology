/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.report.template;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class MRRTReportTemplateParserTest {
    
    
    /**
    * @see MRRTReportTemplateParser#parseTemplateFile(File)
    * @verifies properly initialize template object from information in template html file
    */
    @Test
    public void parseTemplateFile_shouldProperlyInitializeTemplateObRjectFromInformationInTemplateHtmlFile()
            throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("TestMRRTReportTemplate.html")
                .getFile());
        
        MRRTReportTemplate template = MRRTReportTemplateParser.parseTemplateFile(file);
        
        assertNotNull(template);
        assertThat(template.getCharset(), is("UTF-8"));
        assertThat(template.getTitle(), is("Cardiac MRI: Adenosine Stress Protocol"));
        assertThat(template.getDescription(),
            is("Cardiac MRI: Adenosine Stress Protocol template :: Authored by Kazerooni EA, et al."));
        assertThat(template.getIdentifier(), is("http://www.radreport.org/template/0000048"));
        assertThat(template.getType(), is("IMAGE_REPORT_TEMPLATE"));
        assertThat(template.getLanguage(), is("en"));
        assertThat(template.getPublisher(), is("Radiological Society of North America (RSNA)"));
        assertThat(template.getRights(), is("May be used gratis, subject to license agreement"));
        assertThat(template.getLicense(), is("http://www.radreport.org/license.pdf"));
        assertThat(template.getDate(), is("2012-07-19"));
        assertThat(template.getCreator(), is("Kazerooni EA, et al."));
        assertEquals(template.getContributors()
                .size(),
            1);
        assertTrue(template.getContributors()
                .contains("Kahn CE Jr [editor]"));
    }
    
    /**
     * @see MRRTReportTemplateParser#parseTemplateFile(File)
     * @verifies throw exception on missing template file
     */
    @Test(expected = MRRTReportTemplateException.class)
    public void parseTemplateFile_shouldThrowExceptionOnMissingTemplateFile() throws Exception {
        File templateFile = new File("invalid.html");
        
        MRRTReportTemplateParser.parseTemplateFile(templateFile);
    }
}
