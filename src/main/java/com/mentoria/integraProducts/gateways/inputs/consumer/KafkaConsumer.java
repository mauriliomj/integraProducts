package com.mentoria.integraProducts.gateways.inputs.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentoria.integraProducts.gateways.inputs.consumer.resources.SellerResource;
import com.mentoria.integraProducts.usecases.AddSeller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

  @Autowired
  private AddSeller addSeller;

  @KafkaListener(
      topics = "${integra-sellers.seller.broadcast}", groupId = "integra-product")
  public void consumer(String message){

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      SellerResource sellerResource = objectMapper.readValue(message, SellerResource.class);
      addSeller.execute(sellerResource.toDomain());

    } catch (JsonProcessingException e) {
      log.error("Erro ao consumir mensagem.", e);
    }
  }
}
