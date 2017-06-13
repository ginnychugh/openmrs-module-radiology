/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openmrs.ConceptComplex;
import org.openmrs.Obs;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.radiology.RadiologyProperties;
import org.openmrs.module.radiology.order.RadiologyOrder;
import org.openmrs.module.radiology.report.template.MrrtReportTemplate;
import org.openmrs.obs.ComplexData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
class RadiologyReportServiceImpl extends BaseOpenmrsService implements RadiologyReportService {
    
    
    private static final Log log = LogFactory.getLog(RadiologyReportServiceImpl.class);
    
    private RadiologyReportDAO radiologyReportDAO;
    
    @Autowired
    private RadiologyProperties radiologyProperties;
    
    public void setRadiologyReportDAO(RadiologyReportDAO radiologyReportDAO) {
        this.radiologyReportDAO = radiologyReportDAO;
    }
    
    /**
     * @see RadiologyReportService#createRadiologyReport(RadiologyOrder)
     */
    @Override
    @Transactional
    public synchronized RadiologyReport createRadiologyReport(RadiologyOrder radiologyOrder) {
        
        if (radiologyOrder == null) {
            throw new IllegalArgumentException("radiologyOrder cannot be null");
        }
        if (radiologyOrder.isNotCompleted()) {
            throw new APIException("radiology.RadiologyReport.cannot.create.for.not.completed.order");
        }
        if (radiologyReportDAO.hasRadiologyOrderClaimedRadiologyReport(radiologyOrder)) {
            throw new APIException("radiology.RadiologyReport.cannot.create.already.claimed");
        }
        if (radiologyReportDAO.hasRadiologyOrderCompletedRadiologyReport(radiologyOrder)) {
            throw new APIException("radiology.RadiologyReport.cannot.create.already.completed");
        }
        final RadiologyReport radiologyReport = new RadiologyReport(radiologyOrder);
        return radiologyReportDAO.saveRadiologyReport(radiologyReport);
    }
    
    @Override
    @Transactional
    public RadiologyReport createRadiologyReport(RadiologyOrder order, MrrtReportTemplate template) {
        if (order == null) {
            throw new IllegalArgumentException("radiologyOrder cannot be null");
        }
        if (order.isNotCompleted()) {
            throw new APIException("radiology.RadiologyReport.cannot.create.for.not.completed.order");
        }
        if (radiologyReportDAO.hasRadiologyOrderClaimedRadiologyReport(order)) {
            throw new APIException("radiology.RadiologyReport.cannot.create.already.claimed");
        }
        if (radiologyReportDAO.hasRadiologyOrderCompletedRadiologyReport(order)) {
            throw new APIException("radiology.RadiologyReport.cannot.create.already.completed");
        }
        if (template == null) {
            throw new IllegalArgumentException("reportTemplate cannot be null");
        }
        final RadiologyReport radiologyReport = new RadiologyReport(order);
        radiologyReport.setReportTemplate(template);
        return radiologyReportDAO.saveRadiologyReport(radiologyReport);
    }
    
    /**
     * @see RadiologyReportService#saveRadiologyReportDraft(RadiologyReport)
     */
    @Override
    @Transactional
    public synchronized RadiologyReport saveRadiologyReportDraft(RadiologyReport radiologyReport) {
        
        if (radiologyReport == null) {
            throw new IllegalArgumentException("radiologyReport cannot be null");
        }
        if (radiologyReport.getReportId() == null) {
            throw new IllegalArgumentException("radiologyReport.reportId cannot be null");
        }
        if (radiologyReport.getStatus() == RadiologyReportStatus.COMPLETED) {
            throw new APIException("radiology.RadiologyReport.cannot.saveDraft.already.completed");
        }
        if (radiologyReport.getVoided()) {
            throw new APIException("radiology.RadiologyReport.cannot.saveDraft.already.voided");
        }
        if (radiologyReportDAO.hasRadiologyOrderCompletedRadiologyReport(radiologyReport.getRadiologyOrder())) {
            throw new APIException("radiology.RadiologyReport.cannot.saveDraft.already.reported");
        }
        return radiologyReportDAO.saveRadiologyReport(radiologyReport);
    }
    
    /**
     * @see RadiologyReportService#voidRadiologyReport(RadiologyReport, String)
     */
    @Override
    @Transactional
    public RadiologyReport voidRadiologyReport(RadiologyReport radiologyReport, String voidReason) {
        
        if (radiologyReport == null) {
            throw new IllegalArgumentException("radiologyReport cannot be null");
        }
        if (radiologyReport.getReportId() == null) {
            throw new IllegalArgumentException("radiologyReport.reportId cannot be null");
        }
        if (StringUtils.isBlank(voidReason)) {
            throw new IllegalArgumentException("voidReason cannot be null or empty");
        }
        if (radiologyReport.getStatus() == RadiologyReportStatus.COMPLETED) {
            throw new APIException("radiology.RadiologyReport.cannot.void.completed");
        }
        return radiologyReportDAO.saveRadiologyReport(radiologyReport);
    }
    
    /**
     * @see RadiologyReportService#saveRadiologyReport(RadiologyReport)
     */
    @Override
    @Transactional
    public synchronized RadiologyReport saveRadiologyReport(RadiologyReport radiologyReport) {
        
        if (radiologyReport == null) {
            throw new IllegalArgumentException("radiologyReport cannot be null");
        }
        if (radiologyReport.getReportId() == null) {
            throw new IllegalArgumentException("radiologyReport.reportId cannot be null");
        }
        if (radiologyReport.getStatus() == null) {
            throw new IllegalArgumentException("radiologyReport.status cannot be null");
        }
        if (radiologyReport.getStatus() == RadiologyReportStatus.COMPLETED) {
            throw new APIException("radiology.RadiologyReport.cannot.complete.completed");
        }
        if (radiologyReport.getVoided()) {
            throw new APIException("radiology.RadiologyReport.cannot.complete.voided");
        }
        radiologyReport.setDate(new Date());
        radiologyReport.setStatus(RadiologyReportStatus.COMPLETED);
        saveRadiologyReportBodyAsComplexObs(radiologyReport);
        return radiologyReportDAO.saveRadiologyReport(radiologyReport);
    }
    
    private void saveRadiologyReportBodyAsComplexObs(RadiologyReport report) {
        final Obs obs = new Obs();
        final ConceptComplex concept = radiologyProperties.getConceptForReport();
        obs.setConcept(concept);
        obs.setPerson(report.getRadiologyOrder()
                .getPatient());
        obs.setObsDatetime(new Date());
        File tmpFile = null;
        InputStream complexDataInputStream = null;
        try {
            tmpFile = File.createTempFile("report", ".html");
            FileUtils.writeStringToFile(tmpFile, getReportContent(report));
            complexDataInputStream = new FileInputStream(tmpFile);
        }
        catch (IOException e) {
            throw new APIException(e.getMessage(), e);
        }
        final ComplexData complexData = new ComplexData(java.util.UUID.randomUUID()
                .toString(), complexDataInputStream);
        obs.setComplexData(complexData);
        report.setObs(obs);
        Context.getObsService()
                .saveObs(obs, "");
    }
    
    private String getReportContent(RadiologyReport report) {
        final String header =
                "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<title>Diagnosis</title>\n" + "</head>\n" + "<body>\n";
        final String footer = "\n</body>\n" + "</html>";
        return header + report.getBody() + footer;
    }
    
    private String getHeaderFromReportTemplate(MrrtReportTemplate reportTemplate) {
        if (reportTemplate == null) {
            throw new IllegalArgumentException("reportTemplate cannot be null");
        }
        final File templateFile = new File(reportTemplate.getPath());
        final Document doc;
        try {
            doc = Jsoup.parse(templateFile, null);
        }
        catch (IOException e) {
            throw new APIException(e.getMessage(), e);
        }
        
        return "<head>" + doc.select("head")
                .html() + "</head>";
    }
    
    /**
     * @see RadiologyReportService#getRadiologyReport(Integer)
     */
    @Override
    public RadiologyReport getRadiologyReport(Integer reportId) {
        
        if (reportId == null) {
            throw new IllegalArgumentException("reportId cannot be null");
        }
        return radiologyReportDAO.getRadiologyReport(reportId);
    }
    
    /**
     * @see RadiologyReportService#getRadiologyReportByUuid(String)
     */
    @Override
    public RadiologyReport getRadiologyReportByUuid(String radiologyReportUuid) {
        
        if (radiologyReportUuid == null) {
            throw new IllegalArgumentException("radiologyReportUuid cannot be null");
        }
        return radiologyReportDAO.getRadiologyReportByUuid(radiologyReportUuid);
    }
    
    /**
     * @see RadiologyReportService#hasRadiologyOrderClaimedRadiologyReport(RadiologyOrder)
     */
    @Override
    public boolean hasRadiologyOrderClaimedRadiologyReport(RadiologyOrder radiologyOrder) {
        
        if (radiologyOrder == null) {
            throw new IllegalArgumentException("radiologyOrder cannot be null");
        }
        return radiologyReportDAO.hasRadiologyOrderClaimedRadiologyReport(radiologyOrder);
    }
    
    /**
     * @see RadiologyReportService#hasRadiologyOrderCompletedRadiologyReport(RadiologyOrder)
     */
    @Override
    public boolean hasRadiologyOrderCompletedRadiologyReport(RadiologyOrder radiologyOrder) {
        
        if (radiologyOrder == null) {
            throw new IllegalArgumentException("radiologyOrder cannot be null");
        }
        return radiologyReportDAO.hasRadiologyOrderCompletedRadiologyReport(radiologyOrder);
    }
    
    /**
     * @see RadiologyReportService#getActiveRadiologyReportByRadiologyOrder(RadiologyOrder)
     */
    @Override
    public RadiologyReport getActiveRadiologyReportByRadiologyOrder(RadiologyOrder radiologyOrder) {
        
        if (radiologyOrder == null) {
            throw new IllegalArgumentException("radiologyOrder cannot be null");
        }
        if (hasRadiologyOrderCompletedRadiologyReport(radiologyOrder)) {
            return radiologyReportDAO.getActiveRadiologyReportByRadiologyOrder(radiologyOrder);
        }
        if (hasRadiologyOrderClaimedRadiologyReport(radiologyOrder)) {
            return radiologyReportDAO.getActiveRadiologyReportByRadiologyOrder(radiologyOrder);
        }
        return null;
    }
    
    /**
     * @see RadiologyReportService#getRadiologyReports(RadiologyReportSearchCriteria)
     */
    @Override
    public List<RadiologyReport> getRadiologyReports(RadiologyReportSearchCriteria radiologyReportSearchCriteria) {
        
        if (radiologyReportSearchCriteria == null) {
            throw new IllegalArgumentException("radiologyReportSearchCriteria cannot be null");
        }
        return radiologyReportDAO.getRadiologyReports(radiologyReportSearchCriteria);
    }
}
