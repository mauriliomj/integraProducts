package com.mentoria.integraProducts.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {

  private String type;
  private String value;

}
