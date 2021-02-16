package com.kenoly.productfactory.web.controller.v2;

import com.kenoly.productfactory.services.v2.ProductServiceV2;
import com.kenoly.productfactory.web.model.v2.ProductDtoV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v2/product")
@RestController
public class ProductControllerV2 {
    private final ProductServiceV2 productServiceV2;

    public ProductControllerV2(ProductServiceV2 productServiceV2) {
        this.productServiceV2 = productServiceV2;
    }

    @GetMapping({"/{productId}"})
    public ResponseEntity<ProductDtoV2> getProduct(@PathVariable("productId") UUID ProductId){

        return new ResponseEntity<>(productServiceV2.getProductById(ProductId), HttpStatus.OK);
    }

    @PostMapping // POST - create new Product
    public ResponseEntity handlePost(ProductDtoV2 ProductDto){

        ProductDtoV2 savedDto = productServiceV2.saveNewProduct(ProductDto);

        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to url
        headers.add("Location", "/api/v1/product/" + savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{productId}"})
    public ResponseEntity handleUpdate(@PathVariable("productId") UUID ProductId, ProductDtoV2 ProductDto){

        productServiceV2.updateProduct(ProductId, ProductDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") UUID ProductId){
        productServiceV2.deleteById(ProductId);
    }
}