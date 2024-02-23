package com.mentoria.integraProducts.gateways.outputs.http;

import com.mentoria.integraProducts.domains.Seller;
import com.mentoria.integraProducts.gateways.outputs.SellersDataGateway;
import com.mentoria.integraProducts.gateways.outputs.http.feign.SellersFeignIntegration;
import com.mentoria.integraProducts.gateways.outputs.http.resources.SellerResource;
import com.mentoria.integraProducts.gateways.outputs.mongodb.documents.SellerDocument;
import com.mentoria.integraProducts.gateways.outputs.mongodb.repositories.SellersRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SellersDataGatewayHttpImpl implements SellersDataGateway {

  @Autowired
  public SellersFeignIntegration sellersFeignIntegration;

  @Autowired
  public SellersRepository sellersRepository;

  @Override
  public boolean exists(String sellerId) {
    Optional<SellerResource> sellerResource = sellersFeignIntegration.get(sellerId);
    return sellerResource.isPresent();
  }

  @Override
  public void save(Seller seller) {
    sellersRepository.save(new SellerDocument(seller));
  }
}
