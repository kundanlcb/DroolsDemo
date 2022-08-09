package com.bootninza.droolsdemo.service;

import com.bootninza.droolsdemo.model.TopUpModel;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopUpService {

    @Autowired
    KieContainer kieContainer;

    public TopUpModel processTopUp(TopUpModel topUpModel) {

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(topUpModel);
        kieSession.fireAllRules();
        kieSession.dispose();
        return topUpModel;

    }
}
