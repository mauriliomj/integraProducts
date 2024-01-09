package com.mentoria.integraproducts.usecases;

import com.mentoria.integraproducts.domains.Product;
import com.mentoria.integraproducts.exceptions.NotFoundException;
import com.mentoria.integraproducts.gateways.outputs.ProductDataGateway;
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
            productDataGateway.findBySkuAndSellerId(mockProduct().getSku(), mockProduct().getSellerId()))
        .thenReturn(Optional.of(mockProduct()));

    updateProduct.execute(mockProductUpdated());

    Mockito.verify(productDataGateway).save(mockProductUpdated());

  }

  @Test
  public void shouldThrowExceptionForNotFindingId() {

    Mockito.when(productDataGateway.findBySkuAndSellerId(any(), any()))
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
    return mockProductUpdated;

  }
}