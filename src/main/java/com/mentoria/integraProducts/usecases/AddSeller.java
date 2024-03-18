package com.mentoria.integraProducts.usecases;

import com.mentoria.integraProducts.domains.Seller;
import com.mentoria.integraProducts.exceptions.AlreadyRegisteredException;
import com.mentoria.integraProducts.gateways.outputs.CheckSellerId;
import com.mentoria.integraProducts.gateways.outputs.mongodb.documents.SellerDocument;
import com.mentoria.integraProducts.gateways.outputs.mongodb.repositories.SellersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddSeller {
  @Autowired
  private CheckSellerId checkSellerId;

  @Autowired
  private SellersRepository sellersRepository;

  public void execute(Seller seller){
    if(checkSellerId.validate(seller.getSellerId())){
      throw new AlreadyRegisteredException("Seller já registrado!");
    }
    sellersRepository.save(new SellerDocument(seller));
  }
}
