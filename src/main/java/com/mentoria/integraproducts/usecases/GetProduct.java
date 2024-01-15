package com.mentoria.integraproducts.usecases;

import com.mentoria.integraproducts.domains.Product;
import com.mentoria.integraproducts.exceptions.NotFoundException;
import com.mentoria.integraproducts.gateways.outputs.ProductDataGateway;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetProduct {

  @Autowired
  private ProductDataGateway productDataGateway;

  public Product execute(String sku, String sellerId) {
    return productDataGateway.findBySkuAndSellerId(sku, sellerId).orElseThrow(() ->
        new NotFoundException("Sku/sellerId não encontrado!"));

  }
}
