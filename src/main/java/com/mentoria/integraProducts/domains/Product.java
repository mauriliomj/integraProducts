package com.mentoria.integraProducts.domains;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  private String sellerId;
  private String sku;
  private String productName;
  private String description;
  private String size;
  private String color;
  private String flavor;
  private String brand;
  private String category;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;

}
