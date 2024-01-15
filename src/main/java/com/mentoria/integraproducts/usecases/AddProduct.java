package com.mentoria.integraproducts.usecases;

import com.mentoria.integraproducts.domains.Product;
import com.mentoria.integraproducts.exceptions.AlreadyRegisteredException;
import com.mentoria.integraproducts.gateways.outputs.ProductDataGateway;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddProduct {

  @Autowired
  private ProductDataGateway productDataGateway;

  public void execute(Product product) {

    boolean validation = new CheckSellerId().validation(product.getSellerId());

    if(validation) {
      if (productDataGateway.findBySkuAndSellerId(product.getSku(), product.getSellerId())
          .isPresent()) {
        throw new AlreadyRegisteredException("Produto já cadastrado para o sellerId");
      }
      product.setCreatedDate(LocalDateTime.now());
      product.setLastModifiedDate(LocalDateTime.now());
      productDataGateway.save(product);
    }
  }
}
