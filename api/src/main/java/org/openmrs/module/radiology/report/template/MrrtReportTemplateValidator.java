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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.annotation.Handler;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validates {@code MrrtReportTemplate}.
 * 
 * @see MrrtReportTemplate
 */
@Component
@Handler(supports = { MrrtReportTemplate.class })
public class MrrtReportTemplateValidator implements Validator {
	
	protected final Log log = LogFactory.getLog(MrrtReportTemplateValidator.class);

	/**
     * Determines if the command object being submitted is a valid type
     *
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     * @should return true for mrrt report template objects
     * @should return false for other object types
     */
	@Override
	public boolean supports(Class<?> clazz) {
		return MrrtReportTemplate.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		final MrrtReportTemplate mrrtReportTemplate = (MrrtReportTemplate) obj;
		if (mrrtReportTemplate == null) {
			errors.reject("error.general");
		}
	}
    
    
}
