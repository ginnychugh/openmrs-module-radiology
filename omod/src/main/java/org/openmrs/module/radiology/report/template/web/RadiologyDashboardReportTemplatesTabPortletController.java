package org.openmrs.module.radiology.report.template.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.web.controller.PortletController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("**/reportTemplatesTab.portlet")
public class RadiologyDashboardReportTemplatesTabPortletController extends PortletController {
    
    // declaration of service should go here
    
    @Override
    protected void populateModel(HttpServletRequest request, Map<String, Object> model) {
        model.put("username", "ivange94");
    }
    
}
