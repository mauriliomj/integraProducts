package com.mentoria.integraproducts.gateways.inputs.controllers;

import com.mentoria.integraproducts.domains.Product;
import com.mentoria.integraproducts.gateways.inputs.jsons.ProductRequest;
import com.mentoria.integraproducts.usecases.AddProduct;
import com.mentoria.integraproducts.usecases.GetProduct;
import com.mentoria.integraproducts.usecases.GetProductsByCategory;
import com.mentoria.integraproducts.usecases.UpdateProduct;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private AddProduct addProduct;
  @Autowired
  private GetProduct getProduct;
  @Autowired
  private UpdateProduct updateProduct;
  @Autowired
  private GetProductsByCategory getProductsByCategory;

  @PostMapping("/{sku}")
  @ApiOperation("Cadastra os preços a um determinado produto.")
  public void addProduct(@RequestHeader String sellerId, @PathVariable String sku,
      @RequestBody ProductRequest productRequest) {

    addProduct.execute(productRequest.toDomain(sellerId, sku));

  }

  @GetMapping("/{sku}")
  @ApiOperation("Pesquisa e retorna o preço pelo sku e sellerId.")
  public Product getProduct(@RequestHeader String sellerId,@PathVariable String sku) {

    return getProduct.execute(sku, sellerId);

  }

  @PutMapping("/{sku}")
  @ApiOperation("Atualiza os preços cadastrados.")
  public void updateProduct(@RequestHeader String sellerId, @PathVariable String sku,
      @RequestBody ProductRequest productRequest) {

    updateProduct.execute(productRequest.toDomain(sellerId, sku));

  }

  @GetMapping
  @ApiOperation("Pesquisa e retorna os produtos de acordo com a categoria")
  public Page<Product> getAllProductsByCategory(@RequestHeader String sellerId,
      @RequestParam String brand, @RequestParam String category,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size){
    return getProductsByCategory.execute(sellerId, brand, category, size, page);

  }
}
