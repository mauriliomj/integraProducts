package com.mentoria.integraproducts.gateways.inputs.jsons;

import com.mentoria.integraproducts.domains.Product;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

  @NotNull(message = "{not.null}")
  private String productName;
  private String description;
  private String size;
  private String color;
  private String flavor;
  private String brand;
  private String category;
  public Product toDomain(String sellerId, @NotNull(message = "{not.null}") String sku) {

    Product product = new Product();
    product.setSellerId(sellerId);
    product.setSku(sku);
    product.setDescription(this.description);
    product.setSize(this.size);
    product.setColor(this.color);
    product.setFlavor(this.flavor);
    product.setBrand(this.brand);
    product.setCategory(this.category);

    return product;

  }
}
