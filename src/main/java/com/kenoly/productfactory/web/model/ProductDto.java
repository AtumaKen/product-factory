package com.kenoly.productfactory.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {


    private UUID id;
    private String productName;
    private String productStyle;
    private Long upc;
}