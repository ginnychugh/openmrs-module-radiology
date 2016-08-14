/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Obs;
import org.openmrs.api.ObsService;
import org.openmrs.api.context.Context;
import org.springframework.util.StringUtils;

/**
 * Allows for serializing/deserializing a Obs object to a string so that Spring knows how to pass
 * a Obs back and forth through an html form or other medium
 * <br/>
 * 
 * @see Obs
 */
public class ObsEditor extends PropertyEditorSupport {
    
    
    private final Log log = LogFactory.getLog(this.getClass());
    
    /**
     * @should return empty string if value does not contain an obs
     * @should return obs id if value does contain an obs
     * 
     * @see java.beans.PropertyEditorSupport#getAsText()
     */
    @Override
    public String getAsText() {
        final Obs obs = (Obs) getValue();
        if (obs == null) {
            return "";
        } else {
            return obs.getObsId()
                    .toString();
        }
    }
    
    /**
     * @should set value to obs whos id matches given text
     * @should set value to obs whos uuid matches given text
     * @should throw illegal argument exception for obs not found
     * @should return null for empty text
     * 
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        final ObsService obsService = Context.getObsService();
        if (StringUtils.hasText(text)) {
            try {
                setValue(obsService.getObs(Integer.valueOf(text)));
            }
            catch (Exception ex) {
                final Obs obs = obsService.getObsByUuid(text);
                setValue(obs);
                if (obs == null) {
                    log.error("Error setting text: " + text, ex);
                    throw new IllegalArgumentException("Obs not found: " + ex.getMessage());
                }
            }
        } else {
            setValue(null);
        }
    }
    
}
