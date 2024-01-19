package com.mentoria.integraProducts.gateways.outputs;

import com.mentoria.integraProducts.domains.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public interface ProductDataGateway {

    void save(Product product);

    Optional<Product> findBySkuAndSellerId(String sku, String sellerId);

    Page<Product> findBySellerIdAndBrandAndCategory(String brand , String category, String sellerId,
        PageRequest pageable);
}
