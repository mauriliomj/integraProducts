package com.mentoria.integraProducts.usecases;

import com.mentoria.integraProducts.domains.Product;
import com.mentoria.integraProducts.exceptions.AlreadyRegisteredException;
import com.mentoria.integraProducts.exceptions.NotFoundException;
import com.mentoria.integraProducts.gateways.outputs.ProductDataGateway;
import com.mentoria.integraProducts.gateways.outputs.SellersDataGateway;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddProduct {

  @Autowired
  private ProductDataGateway productDataGateway;

  @Autowired
  public SellersDataGateway sellersDataGateway;

  @Autowired
  private CheckSellerId checkSellerId;

  public void execute(Product product) {
    boolean validation = checkSellerId.validation(product.getSellerId());

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
