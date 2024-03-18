package com.mentoria.integraProducts.gateways.outputs.mongodb;

import com.mentoria.integraProducts.domains.Seller;
import com.mentoria.integraProducts.gateways.outputs.SellersDataGateway;
import com.mentoria.integraProducts.gateways.outputs.mongodb.documents.SellerDocument;
import com.mentoria.integraProducts.gateways.outputs.mongodb.repositories.SellersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SellersDataGatewayMongoImpl implements SellersDataGateway {

  @Autowired
  private SellersRepository sellersRepository;

  @Override
  public boolean exists(String sellerId) {
    return sellersRepository.existsById(sellerId);
  }

  @Override
  public void save(Seller seller) {
    SellerDocument sellerDocument = new SellerDocument(seller);
    sellersRepository.save(sellerDocument);
  }
}
