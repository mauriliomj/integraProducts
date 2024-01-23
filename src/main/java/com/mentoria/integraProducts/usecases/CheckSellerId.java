package com.mentoria.integraProducts.usecases;

import com.mentoria.integraProducts.gateways.outputs.SellersDataGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckSellerId {

  @Autowired
  private final SellersDataGateway sellersDataGateway;

  @Autowired
  public CheckSellerId(SellersDataGateway sellersDataGateway) {
    this.sellersDataGateway = sellersDataGateway;
  }

  public Boolean validation(String sellerId) {
    return sellersDataGateway.exists(sellerId);
  }
}
