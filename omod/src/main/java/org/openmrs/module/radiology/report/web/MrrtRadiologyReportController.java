package org.openmrs.module.radiology.report.web;

import org.openmrs.api.APIException;
import org.openmrs.module.radiology.report.RadiologyReport;
import org.openmrs.module.radiology.report.RadiologyReportService;
import org.openmrs.module.radiology.report.RadiologyReportStatus;
import org.openmrs.module.radiology.report.template.MrrtReportTemplateService;
import org.openmrs.web.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping(value = MrrtRadiologyReportController.MRRT_RADIOLOGY_REPORT_FORM_REQUEST_MAPPING)
public class MrrtRadiologyReportController {
    
    
    protected static final String MRRT_RADIOLOGY_REPORT_FORM_REQUEST_MAPPING = "/module/radiology/mrrtReport.form";
    
    private String MRRT_RADIOLOGY_REPORT_FORM_VIEW = "/module/radiology/reports/mrrtReportForm";
    
    @Autowired
    private MrrtReportTemplateService mrrtReportTemplateService;
    
    @Autowired
    private RadiologyReportService radiologyReportService;
    
    @RequestMapping(method = RequestMethod.GET, params = { "reportId" })
    protected ModelAndView getRadiologyReportWithExistingReport(@RequestParam("reportId") RadiologyReport radiologyReport)
            throws IOException {
        final ModelAndView modelAndView = new ModelAndView(MRRT_RADIOLOGY_REPORT_FORM_VIEW);
        modelAndView.addObject("radiologyReport", radiologyReport);
        modelAndView.addObject("radiologyOrder", radiologyReport.getRadiologyOrder());
        if (radiologyReport.getStatus() != RadiologyReportStatus.COMPLETED) {
            modelAndView.addObject("mrrtReportTemplateBody",
                mrrtReportTemplateService.getMrrtReportTemplateHtmlBody(radiologyReport.getReportTemplate()));
        }
        modelAndView.addObject(new VoidRadiologyReportRequest());
        return modelAndView;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView completeMrrtRadiologyReport(HttpServletRequest request,
            @Valid @ModelAttribute RadiologyReport radiologyReport, BindingResult bindingResult) {
        
        final ModelAndView modelAndView = new ModelAndView(MRRT_RADIOLOGY_REPORT_FORM_VIEW);
        
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("radiologyReport", radiologyReport);
            modelAndView.addObject("radiologyOrder", radiologyReport.getRadiologyOrder());
            modelAndView.addObject(new VoidRadiologyReportRequest());
            return modelAndView;
        }
        
        try {
            radiologyReportService.saveRadiologyReport(radiologyReport);
            request.getSession()
                    .setAttribute(WebConstants.OPENMRS_MSG_ATTR, "radiology.RadiologyReport.completed");
            modelAndView.setViewName(
                "redirect:" + MRRT_RADIOLOGY_REPORT_FORM_REQUEST_MAPPING + "?reportId=" + radiologyReport.getReportId());
            return modelAndView;
        }
        catch (APIException apiException) {
            request.getSession()
                    .setAttribute(WebConstants.OPENMRS_ERROR_ATTR, apiException.getMessage());
        }
        modelAndView.addObject("radiologyReport", radiologyReport);
        modelAndView.addObject("radiologyOrder", radiologyReport.getRadiologyOrder());
        modelAndView.addObject(new VoidRadiologyReportRequest());
        return modelAndView;
    }
}
