package com.mentoria.integraProducts.usecases;

import com.mentoria.integraProducts.domains.Product;
import com.mentoria.integraProducts.exceptions.AlreadyRegisteredException;
import com.mentoria.integraProducts.gateways.outputs.CheckSellerId;
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
class AddProductTest {
  @InjectMocks
  private AddProduct addProduct;
  @Mock
  private ProductDataGateway productDataGateway;
  @Mock
  private CheckSellerId checkSellerId;
  @Captor
  private ArgumentCaptor<Product> productArgumentCaptor;

  @Test
  public void shouldSaveAProduct() {
    Mockito.when(productDataGateway
            .findBySkuAndSellerId(mockProduct().getSku(), mockProduct().getSellerId()))
        .thenReturn(Optional.empty());
    Mockito.when(checkSellerId.validate(mockProduct().getSellerId())).thenReturn(true);

    addProduct.execute(mockProduct());

    Mockito.verify(productDataGateway).save(productArgumentCaptor.capture());

    Product capturedProduct = productArgumentCaptor.getValue();

    Assertions.assertEquals(mockProduct().getSellerId(), capturedProduct.getSellerId());
    Assertions.assertEquals(mockProduct().getSku(), capturedProduct.getSku());
    Assertions.assertEquals(mockProduct().getProductName(), capturedProduct.getProductName());
    Assertions.assertEquals(mockProduct().getDescription(), capturedProduct.getDescription());
    Assertions.assertEquals(mockProduct().getSize(), capturedProduct.getSize());
    Assertions.assertEquals(mockProduct().getColor(), capturedProduct.getColor());
    Assertions.assertEquals(mockProduct().getFlavor(), capturedProduct.getFlavor());
    Assertions.assertEquals(mockProduct().getBrand(), capturedProduct.getBrand());
    Assertions.assertEquals(mockProduct().getCategory(), capturedProduct.getCategory());
    Assertions.assertNotEquals(mockProduct().getCreatedDate(), capturedProduct.getCreatedDate());
    Assertions.assertNotEquals(mockProduct().getLastModifiedDate(), capturedProduct.
        getLastModifiedDate());
  }

  @Test
  public void shouldThrowAlreadRegisteredException() {
    Mockito.when(productDataGateway.findBySkuAndSellerId(mockProduct().getSku(), mockProduct()
            .getSellerId()))
        .thenThrow(new AlreadyRegisteredException("Este produto já foi cadastrado!"));
    Mockito.when(checkSellerId.validate(mockProduct().getSellerId())).thenReturn(true);

    Assertions.assertThrows(AlreadyRegisteredException.class,
        () -> addProduct.execute(mockProduct()));
  }

  public Product mockProduct() {
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