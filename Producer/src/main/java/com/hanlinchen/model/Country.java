package com.hanlinchen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Country {
    private String name;
    private Double population;
    private String capital;
}
