package com.kenoly.productfactory.web.model.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDtoV2 {
    private UUID id;
    private String ProductName;
    private ProductStyleEnum ProductStyle;
    private Long upc;
}
