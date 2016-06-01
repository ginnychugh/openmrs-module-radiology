/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.util.OpenmrsUtil;

import static org.hamcrest.core.Is.is;

public class RadiologyUtilTest {
    
    
    private String radiologyHome = OpenmrsUtil.getApplicationDataDirectory() + "radiology";
    
    private String templatesHome = radiologyHome + File.separator + "templates";
    
    private String reportsHome = radiologyHome + File.separator + "reports";
    
    /**
    * @see RadiologyUtil#getReportRepository()
    * @verifies should return a valid directory
    */
    @Test
    public void getReportRepository_shouldReturnValidDirectory() throws Exception {
        File repo = RadiologyUtil.getReportRepository();
        
        Assert.assertNotNull(repo);
        boolean repoExists = repo.exists();
        boolean isDirectory = repo.isDirectory();
        
        Assert.assertThat(repoExists, is(true));
        Assert.assertThat(isDirectory, is(true));
        String path = repo.getAbsolutePath();
        
        Assert.assertThat(path, is(reportsHome));
    }
    
    /**
    * @see RadiologyUtil#getTemplateRepository()
    * @verifies should return a valid directory
    */
    @Test
    public void getTemplateRepository_shouldReturnValidDirectory() throws Exception {
        File repo = RadiologyUtil.getTemplateRepository();
        
        Assert.assertNotNull(repo);
        boolean repoExists = repo.exists();
        boolean isDirectory = repo.isDirectory();
        
        Assert.assertThat(repoExists, is(true));
        Assert.assertThat(isDirectory, is(true));
        String path = repo.getAbsolutePath();
        
        Assert.assertThat(path, is(templatesHome));
    }
    
    /**
     * @see RadiologyUtil#getRadiologyHome()
     * @verifies return absolute path to radiology home
     */
    @Test
    public void getRadiologyHome_shouldReturnAbsolutePathToRadiologyHome() throws Exception {
        Assert.assertThat(RadiologyUtil.getRadiologyHome(), is(radiologyHome));
    }
}
