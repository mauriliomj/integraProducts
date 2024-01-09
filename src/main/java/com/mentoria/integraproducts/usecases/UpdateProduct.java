package com.mentoria.integraproducts.usecases;

import com.mentoria.integraproducts.domains.Product;
import com.mentoria.integraproducts.exceptions.NotFoundException;
import com.mentoria.integraproducts.gateways.outputs.ProductDataGateway;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateProduct {

  private ProductDataGateway productDataGateway;

  public void execute(Product updateProduct) {

    if (productDataGateway.findBySkuAndSellerId(updateProduct.getSellerId(), updateProduct.getSku())
        .isEmpty()) {

      throw new NotFoundException("sellerId/sku não encontrado!");

    }

    updateProduct.setLastModifiedDate(LocalDateTime.now());
    productDataGateway.save(updateProduct);

  }
}
