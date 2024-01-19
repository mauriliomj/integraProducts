package com.mentoria.integraProducts.gateways.outputs;

import org.springframework.stereotype.Component;

@Component
public interface SellersDataGateway {
  boolean exists(String sellerId);
}
