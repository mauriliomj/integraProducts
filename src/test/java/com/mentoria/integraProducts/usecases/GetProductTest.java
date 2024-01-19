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

@ExtendWith(MockitoExtension.class)
class GetProductTest {

  @InjectMocks
  private GetProduct getProduct;
  @Mock
  private ProductDataGateway productDataGateway;

  @Test
  public void shouldShowAProduct() {
    Product expectedProduct = new Product();

    Mockito.when(productDataGateway.findBySkuAndSellerId(mockProduct().getSku(),
        mockProduct().getSellerId())).thenReturn(Optional.of(expectedProduct));

    Product actualProduct = getProduct.execute(mockProduct().getSku(), mockProduct().getSellerId());

    Assertions.assertEquals(expectedProduct, actualProduct);

  }

  @Test
  public void shouldThrowAnException() {

    Mockito.when(productDataGateway
            .findBySkuAndSellerId(mockProduct().getSku(), mockProduct().getSellerId()))
        .thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> getProduct
        .execute(mockProduct().getSku(), mockProduct().getSellerId()));

    Mockito.verify(productDataGateway).findBySkuAndSellerId(mockProduct().getSku(),
        mockProduct().getSellerId());

  }

  public Product mockProduct() {

    Product mockProduct = new Product();
    mockProduct.setSku("SkuTest");
    mockProduct.setSellerId("IdTest");
    return mockProduct;

  }
}