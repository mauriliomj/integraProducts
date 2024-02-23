package com.mentoria.integraProducts.usecases;

import com.mentoria.integraProducts.domains.Seller;
import com.mentoria.integraProducts.gateways.outputs.mongodb.documents.SellerDocument;
import com.mentoria.integraProducts.gateways.outputs.mongodb.repositories.SellersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddSeller {
  @Autowired
  private SellersRepository sellersRepository;

  public void saveNewSeller(Seller seller){
    sellersRepository.save(new SellerDocument(seller));
  }
}
