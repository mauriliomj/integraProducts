package com.mentoria.integraProducts.usecases;

import com.mentoria.integraProducts.domains.Product;
import com.mentoria.integraProducts.exceptions.NotFoundException;
import com.mentoria.integraProducts.gateways.outputs.ProductDataGateway;
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
