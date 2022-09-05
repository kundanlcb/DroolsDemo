package com.bootninza.droolsdemo.service;

import com.bootninza.droolsdemo.model.TopUpModel;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNResult;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.DMNServicesClient;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.springframework.stereotype.Service;

@Service
public class DmnService {
    String serverUrl = "http://localhost:8080/kie-server/services/rest/server";
    String containerId = "dmn-test_1.0.0-SNAPSHOT";
    String modelName = "DmnTest";
    String username = "wbadmin";
    String password = "wbadmin";
    String namespace = "https://kiegroup.org/dmn/_D13ED947-3CD6-466A-829E-7FE5BEB3E4EA";

    public TopUpModel processTopUp(TopUpModel topUpModel) {

        KieServicesConfiguration config =
                KieServicesFactory.newRestConfiguration(serverUrl,
                        username,
                        password);
        config.setMarshallingFormat(MarshallingFormat.JSON);
        KieServicesClient kieServicesClient =
                KieServicesFactory.newKieServicesClient(config);

        DMNServicesClient client = kieServicesClient.getServicesClient(DMNServicesClient.class);
        DMNContext dmnContext = client.newContext();
      /*  dmnContext.set("paymentType", topUpModel.getPaymentType());
        dmnContext.set("mobileNumber", topUpModel.getMobileNumber());*/
        dmnContext.set("TopUpModel", topUpModel);

        ServiceResponse<DMNResult> serviceResponse = client.evaluateAll(containerId,namespace,modelName, dmnContext);
        topUpModel = (TopUpModel) serviceResponse.getResult().getContext().get("topUpModel");
        return topUpModel;
    }
}
