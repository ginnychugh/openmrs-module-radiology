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

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openmrs.api.APIException;
import org.xml.sax.SAXException;

/**
 * Uses xsd with schema to validate {@code MrrtReportTemplate} files.
 */
public class XsdMrrtReportTemplateValidator{
    
    
    private static final Log log = LogFactory.getLog(XsdMrrtReportTemplateValidator.class);
    
    private static final String MRRT_REPORT_TEMPLATE_SCHEMA_FILE = "MrrtReportTemplateSchema.xsd";
    
    /**
     * Validate template file and make sure it follows {@code MRRT} standards.
     *
     * @param templateFile the mrrt report template file been validated
     * @throws IOException if one is thrown while reading template file
     * @throws APIException if template file fails validation
     * @should pass if template template file follows mrrt standards
     * @should throw api exception if template does not have an html element
     * @should throw api exception if template has more than one html element
     * @should throw api exception if html element does not have a head element
     * @should throw api exception if html element has more than one head element
     * @should throw api exception if head element does not have a title element
     * @should throw api exception if head element has more than one title element
     * @should throw api exception if head element does not have a meta element with charset attribute
     * @should throw api exception if head element has more than one meta element with charset attribute
     * @should throw api exception if head element does not have one or more meta elements denoting dublin core attributes
     * @should throw api exception if head element does not have script element
     * @should throw api exception if head element has more than one script element
     * @should throw api exception if script element does not have a template attributes element
     * @should throw api exception if script element has more than one template attributes element
     * @should throw api exception if coding schemes element does not have at least one coding scheme element
     * @should throw api exception if term element does not have a code element
     * @should throw api exception if term element has more than one code element
     * @should throw api exception if code element lacks one of meaning scheme or value attribute
     * @should throw api exception if template attributes element does not have a coded content element
     * @should throw api exception if template attributes element has more than one coded content element
     * @should throw api exception if html element does not have a body element
     * @should throw api exception if html element has more than one body element 
     */
    public void validate(File templateFile) throws IOException {
        final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        final Schema schema;
        final Validator validator;
        try {
            schema = factory.newSchema(getSchemaFile());
            validator = schema.newValidator();
            validateMetatags(templateFile);
            validator.validate(new StreamSource(templateFile));
        }
        catch (SAXException e) {
            log.error(e.getMessage(), e);
            throw new APIException("radiology.report.template.validation.error", null, e);
        }
    }
    
    private void validateMetatags(File templateFile) throws IOException {
        final Document doc = Jsoup.parse(templateFile, null, "");
        final Elements metatagsWithCharsetAttribute = doc.select("meta[charset]");
        
        if (metatagsWithCharsetAttribute.isEmpty() || metatagsWithCharsetAttribute.size() > 1) {
            throw new APIException("radiology.report.template.validation.error.meta.charset", null, null);
        }
        
        final Elements dublinAttributes = doc.select("meta[name]");
        if (dublinAttributes.isEmpty()) {
            throw new APIException("radiology.report.template.validation.error.meta.dublinCore", null, null);
        }
    }
    
    private File getSchemaFile() {
        return new File(getClass().getClassLoader()
                .getResource(MRRT_REPORT_TEMPLATE_SCHEMA_FILE)
                .getFile());
    }
}
