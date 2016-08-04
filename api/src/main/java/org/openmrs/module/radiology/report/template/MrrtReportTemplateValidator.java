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
import java.io.IOException;

/**
 * Validates {@code MrrtReportTemplate}
 */
public interface MrrtReportTemplateValidator {
    
    
    /**
     * Validate template file and make sure it follows {@code MRRT} standards.
     *
     * @param templateFile the mrrt report template file been validated
     * @throws IOException if one is thrown while reading template file
     * @should pass if template template file follows mrrt standards
     * @should throw api exception if template file does not follow mrrt standards
     */
    public void validate(File templateFile) throws IOException;
}
