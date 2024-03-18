package com.mentoria.integraProducts.gateways.outputs;

import com.mentoria.integraProducts.gateways.outputs.mongodb.repositories.SellersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckSellerId {
  @Autowired
  private SellersRepository sellersRepository;

  public Boolean validate(String sellerId){
    return sellersRepository.existsById(sellerId);
  }
}
