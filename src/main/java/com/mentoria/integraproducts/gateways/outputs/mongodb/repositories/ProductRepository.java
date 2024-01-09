package com.mentoria.integraproducts.gateways.outputs.mongodb.repositories;

import com.mentoria.integraproducts.gateways.outputs.mongodb.documents.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<ProductDocument, String> {

    Optional<ProductDocument> findBySkuAndSellerId(String sku, String sellerId);

    Page<ProductDocument> findAllByCategory(String sellerId, String brand, String category,
        PageRequest pageable);

}
