package com.mentoria.integraProducts.gateways.outputs;

import com.mentoria.integraProducts.domains.Seller;
import org.ff4j.aop.Flip;
import org.springframework.stereotype.Component;

@Component
@Flip(name = "find-seller-on-http", alterBean = "sellersDataGatewayHttpImpl")
public interface SellersDataGateway {
  boolean exists(String sellerId);

  void save(Seller seller);
}
