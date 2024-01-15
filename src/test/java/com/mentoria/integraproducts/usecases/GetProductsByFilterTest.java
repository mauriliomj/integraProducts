package com.mentoria.integraproducts.usecases;

import static org.mockito.Mockito.mock;

import com.mentoria.integraproducts.gateways.outputs.ProductDataGateway;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetProductsByFilterTest {
  @InjectMocks
  private GetProductsByFilter getProductsByFilter;

  @Mock
  private ProductDataGateway productDataGateway;
/*
  @Test
  public void sholdReturnAProductsPage{
    int pageNumber = 0;
    int pageSize = 10;
    String productId = "testId";
    String brand = "testBrand";
    String category = "testCategory";
    Sort sort = Sort.TypedSort.by(category);
    Page<Product> productPage = mock(Page.class);

    Mockito.when(productDataGateway.findAllByCategory(productId, brand, category, PageRequest
        .of(pageNumber, pageSize, sort))).thenReturn(productPage);

  }*/
}