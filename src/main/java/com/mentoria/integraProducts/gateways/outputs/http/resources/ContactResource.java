package com.mentoria.integraProducts.gateways.outputs.http.resources;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactResource {

  private String type;
  private String value;

}
