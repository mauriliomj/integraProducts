package com.mentoria.integraProducts.usecases;

import com.mentoria.integraProducts.domains.Product;
import com.mentoria.integraProducts.exceptions.AlreadyRegisteredException;
import com.mentoria.integraProducts.exceptions.NotFoundException;
import com.mentoria.integraProducts.gateways.outputs.ProductDataGateway;
import com.mentoria.integraProducts.gateways.outputs.SellersDataGateway;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddProduct {

  @Autowired
  private ProductDataGateway productDataGateway;

  @Autowired
  public SellersDataGateway sellersDataGateway;

  public void execute(Product product) {
    boolean validation = new CheckSellerId(this.sellersDataGateway)
        .validation(product.getSellerId());

    if(validation) {
      if (productDataGateway.findBySkuAndSellerId(product.getSku(), product.getSellerId())
          .isPresent()) {
        throw new AlreadyRegisteredException("Produto já cadastrado para o sellerId");
      }
      product.setCreatedDate(LocalDateTime.now());
      product.setLastModifiedDate(LocalDateTime.now());
      productDataGateway.save(product);
    } else{
      throw new NotFoundException("SellerId não encontrado!");
    }
  }
}
