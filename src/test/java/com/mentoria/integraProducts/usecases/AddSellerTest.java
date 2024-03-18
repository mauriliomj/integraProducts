package com.mentoria.integraProducts.usecases;

import com.mentoria.integraProducts.domains.Address;
import com.mentoria.integraProducts.domains.Contact;
import com.mentoria.integraProducts.domains.Seller;
import com.mentoria.integraProducts.exceptions.AlreadyRegisteredException;
import com.mentoria.integraProducts.gateways.outputs.CheckSellerId;
import com.mentoria.integraProducts.gateways.outputs.mongodb.documents.SellerDocument;
import com.mentoria.integraProducts.gateways.outputs.mongodb.repositories.SellersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddSellerTest {
  @InjectMocks
  private AddSeller addSeller;
  @Mock
  private SellersRepository sellersRepository;
  @Mock
  private CheckSellerId checkSellerId;
  @Captor
  private ArgumentCaptor<SellerDocument> sellerArgumentCaptor;

  @Test
  public void shouldSaveASeller(){
    Mockito.when(checkSellerId.validate(mockSeller().getSellerId())).thenReturn(false);

    addSeller.execute(mockSeller());

    Mockito.verify(checkSellerId).validate(mockSeller().getSellerId());
    Mockito.verify(sellersRepository).save(sellerArgumentCaptor.capture());
  }
  @Test
  public void shouldThrowAlreadyRegisteredException(){
    Mockito.when(checkSellerId.validate(mockSeller().getSellerId())).thenReturn(true);

    Assertions.assertThrows(AlreadyRegisteredException.class, () -> addSeller.execute(mockSeller()));
  }

  public Seller mockSeller() {
    Seller mockSeller = new Seller();
    mockSeller.setSellerId("1693535770652_1");
    mockSeller.setName("MCM Comercial Eletrica ME");
    mockSeller.setAddress(mockAddress());
    mockSeller.setContact(mockContact());
    mockSeller.setRegistrationCode("17.562.451/0001-15");
    mockSeller.setCreatedDate("Created date");
    mockSeller.setLastModifiedDate("Last modified date");

    return mockSeller;
  }

  public Address mockAddress(){
    return new Address("street", "number", "zipcode",
        "city", "state", "country");
  }

  public Contact mockContact(){
    return new Contact("email@test", "email@test");
  }
}