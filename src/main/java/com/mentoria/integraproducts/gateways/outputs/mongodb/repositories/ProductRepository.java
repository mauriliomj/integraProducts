package com.mentoria.integraproducts.gateways.outputs.mongodb.repositories;

import com.mentoria.integraproducts.gateways.outputs.mongodb.documents.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Optional;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<ProductDocument, String> {

    Optional<ProductDocument> findBySkuAndSellerId(String sku, String sellerId);

    @Query("{'sellerId' : ?0, 'brand': ?1, 'category': ?2}")
    Page<ProductDocument> findBySellerIdAndBrandAndCategory(String brand, String category,
        String sellerId, PageRequest pageable);

}
