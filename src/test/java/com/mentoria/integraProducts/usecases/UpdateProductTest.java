package com.mentoria.integraProducts.usecases;

import com.mentoria.integraProducts.domains.Product;
import com.mentoria.integraProducts.exceptions.NotFoundException;
import com.mentoria.integraProducts.gateways.outputs.ProductDataGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UpdateProductTest {
  @InjectMocks
  private UpdateProduct updateProduct;
  @Mock
  private ProductDataGateway productDataGateway;
  @Captor
  private ArgumentCaptor<Product> productArgumentCaptor;

  @Test
  public void shouldChangeAProductBySkuAndSellerId() {
    Mockito.when(
            productDataGateway.findBySkuAndSellerId(mockProductUpdated().getSku(),
                mockProductUpdated().getSellerId()))
        .thenReturn(Optional.of(mockProduct()));

    updateProduct.execute(mockProductUpdated());

    Mockito.verify(productDataGateway).save(productArgumentCaptor.capture());
    Product productCaptor = productArgumentCaptor.getValue();

    Assertions.assertEquals(mockProduct().getSku(), productCaptor.getSku());
    Assertions.assertEquals(mockProduct().getSellerId(), productCaptor.getSellerId());
    Assertions.assertNotEquals(mockProduct().getLastModifiedDate(), productCaptor
        .getLastModifiedDate());
    Assertions.assertNotEquals(mockProduct().getProductName(), productCaptor.getProductName());
    Assertions.assertNotEquals(mockProduct().getBrand(), productCaptor.getBrand());
    Assertions.assertNotEquals(mockProduct().getCategory(), productCaptor.getCategory());
    Assertions.assertNotEquals(mockProduct().getSize(), productCaptor.getSize());
    Assertions.assertNotEquals(mockProduct().getColor(), productCaptor.getColor());
    Assertions.assertNotEquals(mockProduct().getDescription(), productCaptor.getDescription());
    Assertions.assertNotEquals(mockProduct().getFlavor(), productCaptor.getFlavor());

  }

  @Test
  public void shouldThrowExceptionForNotFoundException() {
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
    Product mockProduct = new Product();
    mockProduct.setSku("SkuTest");
    mockProduct.setSellerId("IdTest");
    mockProduct.setProductName("name");
    mockProduct.setDescription("description");
    mockProduct.setSize("size");
    mockProduct.setColor("collor");
    mockProduct.setFlavor("flavor");
    mockProduct.setBrand("brand");
    mockProduct.setCategory("category");
    return mockProduct;
  }
}