package com.mentoria.integraProducts.usecases;

import com.mentoria.integraProducts.domains.Product;
import com.mentoria.integraProducts.gateways.outputs.ProductDataGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class GetProductsByFilter {
  @Autowired
  private ProductDataGateway productDataGateway;

  public Page<Product> execute(String brand, String category, String sellerId, int pageSize,
      int pageNumber) {
    PageRequest pageable = PageRequest.of(pageNumber, pageSize);
    return productDataGateway.findBySellerIdAndBrandAndCategory(brand, category, sellerId, pageable);
  }
}
