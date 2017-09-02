package org.openmrs.module.radiology.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.radiology.order.RadiologyOrder;
import org.openmrs.module.radiology.order.RadiologyOrderSearchCriteria;
import org.openmrs.module.radiology.order.RadiologyOrderService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceControllerTest;
import org.springframework.mock.web.MockHttpServletRequest;

public class RadiologyOrderControllerTest extends MainResourceControllerTest {

    private static final String PATIENT_UUID = "1ac98ec8-e9d9-4626-998c-c795c2f0aa9f";
    private static final String ORDERER_UUID = "c2299800-cca9-11e0-9572-0800200c9a66";
    private static final String CONCEPT_UUID = "1565b6e6-df81-11e4-98ec-08002798a7ad";

    private RadiologyOrderService radiologyOrderService;
    private final String RADIOLOGY_ORDER_UUID = "65d68058-c75b-4807-a8ba-1728558c9f8e";

    @Before
    public void setUp() throws Exception {
        executeDataSet("RadiologyOrderControllerTestDataset.xml");
        radiologyOrderService = Context.getService(RadiologyOrderService.class);
    }

    @Override
    public String getURI() {
        return "radiologyorder";
    }

    @Override
    public String getUuid() {
        return RADIOLOGY_ORDER_UUID;
    }

    @Override
    public long getAllCount() {
        RadiologyOrderSearchCriteria criteria = new RadiologyOrderSearchCriteria.Builder().build();
        return this.radiologyOrderService.getRadiologyOrders(criteria).size();
    }

    @Test
    public void shouldCreateRadiologyOrder() throws Exception {
        Patient patient = Context.getPatientService().getPatientByUuid(PATIENT_UUID);
        Assert.assertNotNull(patient);
        /*
        SimpleObject radiologyOrder = new SimpleObject();
        radiologyOrder.add("patient", PATIENT_UUID);
        radiologyOrder.add("concept", CONCEPT_UUID);
        radiologyOrder.add("orderer", ORDERER_UUID);
        radiologyOrder.add("urgency", RadiologyOrder.Urgency.ROUTINE);

        MockHttpServletRequest req = newPostRequest(getURI(), radiologyOrder);
        SimpleObject result = deserialize(handle(req));
        */
    }
}
