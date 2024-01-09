package com.mentoria.integraproducts.gateways.outputs.mongodb.documents;

import com.mentoria.integraproducts.domains.Product;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Query;

@Data
@NoArgsConstructor
@Document("product")
public class ProductDocument {

    @Id
    private String sku;
    @Indexed
    private String sellerId;
    private String productName;
    private String description;
    private String size;
    private String color;
    private String flavor;
    private String brand;
    private String category;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;


    public ProductDocument(Product product){

        this.sku = product.getSku();
        this.sellerId = product.getSellerId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.size = product.getSize();
        this.color = product.getColor();
        this.flavor = product.getFlavor();
        this.brand = product.getBrand();
        this.category = product.getCategory();
        this.createdDate = product.getCreatedDate();
        this.lastModifiedDate = product.getLastModifiedDate();

    }

    public Product toDomain(){

        Product product = new Product();
        product.setSku(this.sku);
        product.setSellerId(this.sellerId);
        product.setProductName(this.productName);
        product.setDescription(this.description);
        product.setSize(this.size);
        product.setColor(this.color);
        product.setFlavor(this.flavor);
        product.setBrand(this.brand);
        product.setCategory(this.category);
        product.setCreatedDate(this.createdDate);
        product.setLastModifiedDate(this.lastModifiedDate);

        return product;

    }
}
