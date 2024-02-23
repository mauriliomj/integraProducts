package com.mentoria.integraProducts.usecases;

import com.mentoria.integraProducts.domains.Product;
import com.mentoria.integraProducts.exceptions.NotFoundException;
import com.mentoria.integraProducts.gateways.outputs.ProductDataGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UpdateProductTest {

  @InjectMocks
  private UpdateProduct updateProduct;

  @Mock
  private ProductDataGateway productDataGateway;

  @Test
  public void shouldChangeAPriceBySkuAndSellerId() {
    Mockito.when(
            productDataGateway.findBySkuAndSellerId(mockProductUpdated().getSku(),
                mockProductUpdated().getSellerId()))
        .thenReturn(Optional.of(mockProduct()));

    updateProduct.execute(mockProductUpdated());

    Mockito.verify(productDataGateway).save(mockProductUpdated());
  }

  @Test
  public void shouldThrowExceptionForNotFindingId() {
    Mockito.when(productDataGateway.findBySkuAndSellerId(mockProductUpdated().getSku(),
            mockProductUpdated().getSellerId()))
        .thenThrow(new NotFoundException("Id não encontrado."));

    Assertions.assertThrows(NotFoundException.class, () -> updateProduct
        .execute(mockProductUpdated()));
  }

  public Product mockProduct() {

    Product mockProduct = new Product();
    mockProduct.setSku("SkuTest");
    mockProduct.setSellerId("IdTest");
    return mockProduct;

  }

  public Product mockProductUpdated() {
    Product mockProductUpdated = new Product();
    mockProductUpdated.setSku("SkuTest");
    mockProductUpdated.setSellerId("IdTest");
    return mockProductUpdated;
  }
}