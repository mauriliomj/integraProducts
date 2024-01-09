package com.mentoria.integraproducts.gateways.outputs.mongodb;

import com.mentoria.integraproducts.domains.Product;
import com.mentoria.integraproducts.gateways.outputs.ProductDataGateway;
import com.mentoria.integraproducts.gateways.outputs.mongodb.repositories.ProductRepository;
import com.mentoria.integraproducts.gateways.outputs.mongodb.documents.ProductDocument;
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
    public Page<Product> findAllByCategory(String sellerId, String brand, String category,
        PageRequest pageable){
        return productRepository.findAllByCategory(sellerId, brand, category, pageable).
            map(ProductDocument::toDomain);
    }
}
