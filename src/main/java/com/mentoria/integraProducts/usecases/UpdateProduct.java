package com.mentoria.integraProducts.usecases;

import com.mentoria.integraProducts.domains.Product;
import com.mentoria.integraProducts.exceptions.NotFoundException;
import com.mentoria.integraProducts.gateways.outputs.ProductDataGateway;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateProduct {

  @Autowired
  private ProductDataGateway productDataGateway;

  public void execute(Product updateProduct) {

    if (productDataGateway.findBySkuAndSellerId(updateProduct.getSku(), updateProduct.getSellerId())
        .isPresent()) {
      Optional<Product> existingProduct = productDataGateway.findBySkuAndSellerId(
          updateProduct.getSku(), updateProduct.getSellerId());

      updateProduct.setLastModifiedDate(LocalDateTime.now());
      updateProduct.setCreatedDate(existingProduct.get().getCreatedDate());
      productDataGateway.save(updateProduct);

    }else {
      throw new NotFoundException("sellerId/sku não encontrado!");
    }
  }
}
