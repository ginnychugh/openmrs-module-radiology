/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.report.template;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.BaseOpenmrsObject;

public class MRRTReportTemplate extends BaseOpenmrsObject {
    
    
    private Integer templateId;
    
    private String title;
    
    private String description;
    
    private String charset;
    
    private String identifier;
    
    private String type;
    
    private String language;
    
    private String publisher;
    
    private String rights;
    
    private String license;
    
    private String date;
    
    private String creator;
    
    private List<String> contributors;
    
    private String path;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCharset() {
        return charset;
    }
    
    public void setCharset(String charset) {
        this.charset = charset;
    }
    
    public String getIdentifier() {
        return identifier;
    }
    
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public String getRights() {
        return rights;
    }
    
    public void setRights(String rights) {
        this.rights = rights;
    }
    
    public String getLicense() {
        return license;
    }
    
    public void setLicense(String license) {
        this.license = license;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String content) {
        this.date = content;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public List<String> getContributors() {
        return contributors;
    }
    
    public void setContributors(List<String> contributors) {
        this.contributors = contributors;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public void addContributor(String contributor) {
        if (contributors == null) {
            contributors = new ArrayList<String>();
        }
        contributors.add(contributor);
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public Integer getId() {
        return this.templateId;
    }
    
    @Override
    public void setId(Integer id) {
        this.templateId = id;
    }
}
