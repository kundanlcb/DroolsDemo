package com.bootninza.droolsdemo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopUpModel {

    private String mobileNumber;
    private String paymentType;
    private Integer topUpAmount;
    private Integer discount;

}
