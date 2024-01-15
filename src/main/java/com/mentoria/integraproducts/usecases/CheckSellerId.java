package com.mentoria.integraproducts.usecases;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CheckSellerId {
    public Boolean validation(String sellerId){
    return true;
  }
}
