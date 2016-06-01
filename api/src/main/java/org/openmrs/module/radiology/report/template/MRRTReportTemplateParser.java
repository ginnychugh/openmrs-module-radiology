/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.report.template;

import java.io.File;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *  Parser to parse MRRT template file and construct a template object
 */
public class MRRTReportTemplateParser {
    
    
    private static final Log log = LogFactory.getLog(MRRTReportTemplateParser.class);
    
    public static final String DCTERMS_TITLE = "dcterms.title";
    
    public static final String DCTERMS_DESCRIPTION = "dcterms.description";
    
    public static final String DCTERMS_IDENTIFIER = "dcterms.identifier";
    
    public static final String DCTERMS_TYPE = "dcterms.type";
    
    public static final String DCTERMS_LANGUAGE = "dcterms.language";
    
    public static final String DCTERMS_PUBLISHER = "dcterms.publisher";
    
    public static final String DCTERMS_RIGHTS = "dcterms.rights";
    
    public static final String DCTERMS_LICENSE = "dcterms.license";
    
    public static final String DCTERMS_DATE = "dcterms.date";
    
    public static final String DCTERMS_CREATOR = "dcterms.creator";
    
    public static final String DCTERMS_CONTRIBUTOR = "dcterms.contributor";
    
    /**
     *  parse MRRT html template and construct a template object
     *  
     *  @param file that needs to be parsed
     *  
     *  @return returns an object representing the physical template
     *  
     *  @should properly initialize template object from information in template html file
     *  
     *  @should throw exception on missing template file
     */
    public static MRRTReportTemplate parseTemplateFile(File file) throws MRRTReportTemplateException, IOException {
        MRRTReportTemplate template = new MRRTReportTemplate();
        
        if (file.exists()) {
            if (!file.canRead()) {
                throw new MRRTReportTemplateException("Error parsing MRRT file. " + file.getName() + " cannot be read");
            }
            Document doc = Jsoup.parse(file, "UTF-8");
            initializeTemplate(template, doc);
            
        } else {
            throw new MRRTReportTemplateException("Error parsing MRRT file. " + file.getName() + " does not exist");
        }
        return template;
    }
    
    private static void initializeTemplate(MRRTReportTemplate template, Document doc) {
        Elements metaTags = doc.getElementsByTag("meta");
        
        template.setPath(doc.baseUri());
        template.setCharset(metaTags.attr("charset"));
        for (Element metaTag : metaTags) {
            String name = metaTag.attr("name");
            String content = metaTag.attr("content");
            
            switch (name) {
                case DCTERMS_TITLE:
                    template.setTitle(content);
                    break;
                case DCTERMS_DESCRIPTION:
                    template.setDescription(content);
                    break;
                case DCTERMS_IDENTIFIER:
                    template.setIdentifier(content);
                    break;
                case DCTERMS_TYPE:
                    template.setType(content);
                    break;
                case DCTERMS_LANGUAGE:
                    template.setLanguage(content);
                    break;
                case DCTERMS_PUBLISHER:
                    template.setPublisher(content);
                    break;
                case DCTERMS_RIGHTS:
                    template.setRights(content);
                    break;
                case DCTERMS_LICENSE:
                    template.setLicense(content);
                    break;
                case DCTERMS_DATE:
                    template.setDate(content);
                    break;
                case DCTERMS_CREATOR:
                    template.setCreator(content);
                    break;
                case DCTERMS_CONTRIBUTOR:
                    template.addContributor(content);
                    break;
                default:
                    log.debug("Unhandled meta tag " + name);
            }
        }
    }
}
