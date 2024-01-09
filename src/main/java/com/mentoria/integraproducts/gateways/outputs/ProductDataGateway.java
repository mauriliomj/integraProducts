package com.mentoria.integraproducts.gateways.outputs;

import com.mentoria.integraproducts.domains.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public interface ProductDataGateway {

    void save(Product product);

    Optional<Product> findBySkuAndSellerId(String sku, String sellerId);

    Page<Product> findAllByCategory(String sellerId , String brand, String category, PageRequest pageable);

}
