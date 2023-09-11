package com.moaaz.modernhome.Product;

import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {


    private String name;

    private String details;


    private List<String> images;
    private double price;
    private double discount;

    private long categoryId;
}
