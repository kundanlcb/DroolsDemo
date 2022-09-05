package com.bootninza.droolsdemo.service;

import com.bootninza.droolsdemo.model.TopUpModel;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.KieServices;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieServerInfo;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TopUpService {


    private static final String containerName = "topup_gdt_1.0.0 ";

    public TopUpModel processTopUp(TopUpModel topUpModel) {
        Set<Class<?>> allClasses = new HashSet<>();
        allClasses.add(TopUpModel.class);

        String serverUrl = "http://localhost:8080/kie-server/services/rest/server";
        String username = "wbadmin";
        String password = "wbadmin";
        KieServicesConfiguration config =
                KieServicesFactory.newRestConfiguration(serverUrl,
                        username,
                        password);
        config.setMarshallingFormat(MarshallingFormat.JAXB);
        config.addExtraClasses(allClasses);
        KieServicesClient kieServicesClient =
                KieServicesFactory.newKieServicesClient(config);

        KieCommands kieCommands = KieServices.Factory.get().getCommands();
        List<Command> commandList = new ArrayList<>();
        commandList.add(kieCommands.newInsert(topUpModel, "topUpModel"));

        // Fire all rules:
        commandList.add(kieCommands.newFireAllRules("numberOfFiredRules"));
        BatchExecutionCommand batch = kieCommands.newBatchExecution(commandList);

        // Use rule services client to send request:
        RuleServicesClient ruleClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
        DMNServicesClient client = kieServicesClient.getServicesClient(DMNServicesClient.class);

        List<KieContainerResource> kieContainers = kieServicesClient.listContainers().getResult().getContainers();
        ServiceResponse<KieServerInfo> serviceResponse = kieServicesClient.getServerInfo();
        ServiceResponse<ExecutionResults> executeResponse = ruleClient.executeCommandsWithResults(containerName, batch);
        System.out.println("number of fired rules:" + executeResponse.getResult().getValue("numberOfFiredRules"));
        topUpModel = (TopUpModel) executeResponse.getResult().getValue("topUpModel");
        //555555
     /*   KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(topUpModel);
        kieSession.fireAllRules();
        kieSession.dispose();*/
        return topUpModel;

    }
}
