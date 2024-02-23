package com.mentoria.integraProducts.gateways.outputs;

import com.mentoria.integraProducts.domains.Seller;
import org.springframework.stereotype.Component;

@Component
public interface SellersDataGateway {
  boolean exists(String sellerId);

  void save(Seller seller);
}
