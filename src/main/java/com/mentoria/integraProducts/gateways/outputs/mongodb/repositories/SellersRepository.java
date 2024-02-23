package com.mentoria.integraProducts.gateways.outputs.mongodb.repositories;

import com.mentoria.integraProducts.gateways.outputs.mongodb.documents.SellerDocument;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SellersRepository extends PagingAndSortingRepository<SellerDocument, String> {}
