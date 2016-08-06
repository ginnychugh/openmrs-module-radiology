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
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

/**
 * Uses xsd with schema to validate {@code MrrtReportTemplate} files.
 */
@Component
class XsdMrrtReportTemplateValidator implements MrrtReportTemplateValidator {
    
    
    private static final Log log = LogFactory.getLog(XsdMrrtReportTemplateValidator.class);
    
    private static final String MRRT_REPORT_TEMPLATE_SCHEMA_FILE = "MrrtReportTemplateSchema.xsd";
    
    /**
     * @see org.openmrs.module.radiology.report.template.MrrtReportTemplateValidator#validate(File)
     */
    @Override
    public void validate(File templateFile) throws IOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema;
        Validator validator;
        try {
            schema = factory.newSchema(getSchemaFile());
            validator = schema.newValidator();
            validateMetatags(templateFile);
            validator.validate(new StreamSource(templateFile));
        }
        catch (SAXException e) {
            log.error(e.getMessage(), e);
            throw new APIException("radiology.report.templates.validation.error", null, e);
        }
    }
    
    private void validateMetatags(File templateFile) throws IOException {
        Document doc = Jsoup.parse(templateFile, null, "");
        Elements metatagsWithCharsetAttribute = doc.select("meta[charset]");
        
        if (metatagsWithCharsetAttribute.isEmpty() || metatagsWithCharsetAttribute.size() > 1) {
            throw new APIException("radiology.report.templates.validation.error", null,
                    new Throwable("Template file should have exactly one meta element with charset attribute"));
        }
        
        Elements dublinAttributes = doc.select("meta[name]");
        if (dublinAttributes.isEmpty()) {
            throw new APIException("radiology.report.templates.validation.error", null,
                    new Throwable("Template file should have at least one meta tag containing dublin core attributes"));
        }
    }
    
    private File getSchemaFile() {
        return new File(getClass().getClassLoader()
                .getResource(MRRT_REPORT_TEMPLATE_SCHEMA_FILE)
                .getFile());
    }
}
