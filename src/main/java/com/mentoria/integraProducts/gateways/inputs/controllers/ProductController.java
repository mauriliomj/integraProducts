package com.mentoria.integraProducts.gateways.inputs.controllers;

import com.mentoria.integraProducts.domains.Product;
import com.mentoria.integraProducts.gateways.inputs.jsons.ProductRequest;
import com.mentoria.integraProducts.usecases.AddProduct;
import com.mentoria.integraProducts.usecases.GetProduct;
import com.mentoria.integraProducts.usecases.GetProductsByFilter;
import com.mentoria.integraProducts.usecases.UpdateProduct;
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
  private GetProductsByFilter getProductsByFilter;

  @PostMapping("/{sku}")
  @ApiOperation("Cadastra os produtos.")
  public void addProduct(@RequestHeader String sellerId, @PathVariable String sku,
      @RequestBody ProductRequest productRequest) {

    addProduct.execute(productRequest.toDomain(sellerId, sku));

  }

  @GetMapping("/{sku}")
  @ApiOperation("Pesquisa e retorna o produto pelo sku e sellerId.")
  public Product getProduct(@RequestHeader String sellerId,@PathVariable String sku) {

    return getProduct.execute(sku, sellerId);

  }

  @PutMapping("/{sku}")
  @ApiOperation("Atualiza os produtos cadastrados.")
  public void updateProduct(@RequestHeader String sellerId, @PathVariable String sku,
      @RequestBody ProductRequest productRequest) {

    updateProduct.execute(productRequest.toDomain(sellerId, sku));

  }

  @GetMapping
  @ApiOperation("Pesquisa e retorna os produtos de acordo com a categoria")
  public Page<Product> getAllProductsByCategory(@RequestParam String brand,
      @RequestParam String category,
      @RequestHeader String sellerId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size){
    return getProductsByFilter.execute(sellerId, brand, category, size, page);

  }
}
