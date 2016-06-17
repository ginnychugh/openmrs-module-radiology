/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.report.template.web.resource;

import org.openmrs.module.radiology.report.template.MrrtReportTemplate;
import org.openmrs.module.radiology.report.template.MrrtReportTemplateService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs2_0.RestConstants2_0;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
@Resource(name = RestConstants.VERSION_1 + "/mrrtreporttemplate", supportedClass = MrrtReportTemplate.class,
        supportedOpenmrsVersions = { "2.0.*" })
public class MrrtReportTemplateResource extends DelegatingCrudResource<MrrtReportTemplate> {
    
    
    @Autowired
    private MrrtReportTemplateService mrrtReportTemplateService;
    
    /**
     * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource#getRepresentationDescription(org.openmrs.module.webservices.rest.web.representation.Representation)
     * @should return default representation given instance of defaultrepresentation
     * @should return full representation given instance of fullrepresentation
     * @should return null for representation other then default or full
     */
    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        if (rep instanceof DefaultRepresentation) {
            DelegatingResourceDescription description = new DelegatingResourceDescription();
            
            description.addProperty("uuid");
            description.addProperty("display");
            description.addProperty("templateId");
            description.addProperty("dctermsIdentifier");
            description.addProperty("dctermsTitle");
            description.addProperty("dctermsType");
            description.addProperty("dctermsPublisher");
            description.addProperty("dctermsCreator");
            description.addProperty("dctermsRights");
            description.addSelfLink();
            description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
        }
        if (rep instanceof FullRepresentation) {
            DelegatingResourceDescription description = new DelegatingResourceDescription();
            
            description.addProperty("uuid");
            description.addProperty("display");
            description.addProperty("charset");
            description.addProperty("path");
            description.addProperty("templateId");
            description.addProperty("dctermsIdentifier");
            description.addProperty("dctermsTitle");
            description.addProperty("dctermsDescription");
            description.addProperty("dctermsType");
            description.addProperty("dctermsLanguage");
            description.addProperty("dctermsPublisher");
            description.addProperty("dctermsCreator");
            description.addProperty("dctermsRights");
            description.addProperty("dctermsLicense");
            description.addProperty("dctermsDate");
            description.addSelfLink();
            return description;
        } else {
            return null;
        }
    }
    
    /**
     * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#getByUniqueId(java.lang.String)
     * @should return radiology order given its uuid
     */
    @Override
    public MrrtReportTemplate getByUniqueId(String uuid) {
        return mrrtReportTemplateService.getMrrtReportTemplateByUuid(uuid);
    }
    
    /**
     * Display string for {@link MrrtReportTemplate}
     *
     * @param mrrtReportTemplate MrrtReportTemplate of which display string shall be returned
     * @return templateIdentifier/title of given mrrtReportTemplate
     * @should return templateIdentifier/title of given mrrtReportTemplate
     */
    @PropertyGetter("display")
    public String getDisplayString(MrrtReportTemplate mrrtReportTemplate) {
        return mrrtReportTemplate.getDcTermsIdentifier() + "/" + mrrtReportTemplate.getDcTermsTitle();
    }
    
    @Override
    protected void delete(MrrtReportTemplate mrrtReportTemplate, String s, RequestContext requestContext)
            throws ResourceDoesNotSupportOperationException {
        throw new ResourceDoesNotSupportOperationException();
    }
    
    @Override
    public void purge(MrrtReportTemplate mrrtReportTemplate, RequestContext requestContext)
            throws ResourceDoesNotSupportOperationException {
        throw new ResourceDoesNotSupportOperationException();
    }
    
    @Override
    public MrrtReportTemplate newDelegate() throws ResourceDoesNotSupportOperationException {
        throw new ResourceDoesNotSupportOperationException();
    }
    
    @Override
    public MrrtReportTemplate save(MrrtReportTemplate mrrtReportTemplate) {
        throw new ResourceDoesNotSupportOperationException();
    }
    
    /**
     * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#getResourceVersion()
     * @should return supported resource version
     */
    @Override
    public String getResourceVersion() {
        return RestConstants2_0.RESOURCE_VERSION;
    }
}
