package com.mentoria.integraProducts.usecases;

import com.mentoria.integraProducts.domains.Product;
import com.mentoria.integraProducts.exceptions.AlreadyRegisteredException;
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
class AddProductTest {

  @InjectMocks
  private AddProduct addProduct;

  @Mock
  private ProductDataGateway productDataGateway;

  @Test
  public void shouldSaveAProduct() {

    Product productTest = mockProduct();

    Mockito.when(productDataGateway
            .findBySkuAndSellerId(productTest.getSku(), productTest.getSellerId()))
        .thenReturn(Optional.empty());

    addProduct.execute(productTest);

    Mockito.verify(productDataGateway).save(productTest);

  }

  @Test
  public void shouldThrowAnExceptionBySellerId() {

    Mockito.when(productDataGateway.findBySkuAndSellerId(mockProduct().getSku(), mockProduct()
            .getSellerId()))
        .thenThrow(new AlreadyRegisteredException("Este produto já foi cadastrado!"));

    Assertions.assertThrows(AlreadyRegisteredException.class,
        () -> addProduct.execute(mockProduct()));

  }

  @Test
  public void shouldThrowAnExceptionBySku() {

    Mockito.when(productDataGateway.findBySkuAndSellerId(mockProduct().getSku(), mockProduct()
            .getSellerId()))
        .thenThrow(new AlreadyRegisteredException("Este produto já foi cadastrado!"));

    Assertions.assertThrows(AlreadyRegisteredException.class,
        () -> addProduct.execute(mockProduct()));

  }

  public Product mockProduct() {

    Product mockProduct = new Product();
    mockProduct.setSku("SkuTest");
    mockProduct.setSellerId("IdTest");
    return mockProduct;

  }

}