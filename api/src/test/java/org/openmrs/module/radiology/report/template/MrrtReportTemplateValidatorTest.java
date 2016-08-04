/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.report.template;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openmrs.api.APIException;

/**
 * Tests {@code MrrtReportTemplateValidator}
 */
public class MrrtReportTemplateValidatorTest {
    
    
    MrrtReportTemplateValidator validator = new XsdMrrtReportTemplateValidator();
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    /**
     * @see MrrtReportTemplateValidator#validate(File)
     * @verifies pass if template template file follows mrrt standards
     */
    @Test
    public void validate_shouldPassIfTemplateTemplateFileFollowsMrrtStandards() throws Exception {
        File validTemplate = new File(getClass().getClassLoader()
                .getResource("mrrttemplates/radreport/ValidTemplate1.html")
                .getFile());
        validator.validate(validTemplate);
    }
    
    /**
     * @see MrrtReportTemplateValidator#validate(File)
     * @verifies throw api exception if template file does not follow mrrt standards
     */
    @Test
    public void validate_shouldThrowApiExceptionIfTemplateFileDoesNotFollowMrrtStandards() throws Exception {
        expectedException.expect(APIException.class);
        File invalidTemplate = new File(getClass().getClassLoader()
                .getResource("mrrttemplates/radreport/0000049.html")
                .getFile());
        validator.validate(invalidTemplate);
    }
}
