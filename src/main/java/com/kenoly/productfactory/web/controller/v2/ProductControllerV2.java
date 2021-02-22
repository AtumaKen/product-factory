package com.kenoly.productfactory.web.controller.v2;

import com.kenoly.productfactory.services.v2.ProductServiceV2;
import com.kenoly.productfactory.web.model.v2.ProductDtoV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RequestMapping("/api/v2/product")
@RequiredArgsConstructor
@RestController
public class ProductControllerV2 {
    private final ProductServiceV2 productServiceV2;


    @GetMapping({"/{productId}"})
    public ResponseEntity<ProductDtoV2> getProduct(@NotNull @PathVariable("productId") UUID ProductId){

        return new ResponseEntity<>(productServiceV2.getProductById(ProductId), HttpStatus.OK);
    }

    @PostMapping // POST - create new Product
    public ResponseEntity handlePost(@Valid @RequestBody ProductDtoV2 ProductDto){

        var savedDto = productServiceV2.saveNewProduct(ProductDto);

        var headers = new HttpHeaders();
        //todo add hostname to url
        headers.add("Location", "/api/v1/product/" + savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{productId}"})
    public ResponseEntity handleUpdate(@PathVariable("productId") UUID ProductId, @Valid @RequestBody ProductDtoV2 ProductDto){

        productServiceV2.updateProduct(ProductId, ProductDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") UUID ProductId){
        productServiceV2.deleteById(ProductId);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e){
        var errors = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}