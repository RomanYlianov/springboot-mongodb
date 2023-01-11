package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDto {

    private String id;

    private String name;

    private Double price;

    private Integer count;

    private Integer currentCount;

    private Date date;

    private CategoryDto category;

    private String description;
}
