package com.mentoria.integraproducts.usecases;

import com.mentoria.integraproducts.domains.Product;
import com.mentoria.integraproducts.gateways.outputs.ProductDataGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class GetProductsByCategory {
  @Autowired
  private ProductDataGateway productDataGateway;

  public Page<Product> execute(String sellerId, String brand, String category, int pageSize,
      int pageNumber) {
    Sort sort = Sort.TypedSort.by(category);
    PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
    return productDataGateway.findAllByCategory(category, brand, sellerId, pageable);
  }
}
