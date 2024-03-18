package com.mentoria.integraProducts.usecases;

import static org.mockito.Mockito.mock;

import com.mentoria.integraProducts.domains.Product;
import com.mentoria.integraProducts.gateways.outputs.ProductDataGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
class GetProductsByFilterTest {
  @InjectMocks
  private GetProductsByFilter getProductsByFilter;
  @Mock
  private ProductDataGateway productDataGateway;

  @Test
  public void sholdReturnAnProductsPageByFilters(){
    int pageNumber = 0;
    int pageSize = 10;
    String sellerId = "testId";
    String brand = "testBrand";
    String category = "testCategory";
    Sort sort = Sort.TypedSort.by(category);
    Page<Product> productPage = ;
    getProductsByFilter.execute(brand, category, sellerId, pageSize, pageNumber);

    Mockito.when(productDataGateway.findBySellerIdAndBrandAndCategory(sellerId, brand, category,
        PageRequest.of(pageNumber, pageSize, sort))).thenReturn(productPage);
  }
}