package com.mentoria.integraProducts.gateways.outputs.mongodb;

import com.mentoria.integraProducts.domains.Product;
import com.mentoria.integraProducts.gateways.outputs.ProductDataGateway;
import com.mentoria.integraProducts.gateways.outputs.mongodb.repositories.ProductRepository;
import com.mentoria.integraProducts.gateways.outputs.mongodb.documents.ProductDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ProductDataGatewayMongoImpl implements ProductDataGateway {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(Product product) {

        productRepository.save(new ProductDocument(product));

    }

    @Override
    public Optional<Product> findBySkuAndSellerId(String sku, String sellerId) {

        return productRepository.findBySkuAndSellerId(sku, sellerId)
                .map(ProductDocument::toDomain);

    }

    @Override
    public Page<Product> findBySellerIdAndBrandAndCategory(String brand, String category,
        String sellerId, PageRequest pageable){
        return productRepository.findBySellerIdAndBrandAndCategory(brand, category, sellerId,
                pageable).map(ProductDocument::toDomain);
    }
}
