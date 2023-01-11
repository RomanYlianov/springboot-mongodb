package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("Products")
public class Product {

    @Id
    private String id;

    //@Field(name = "id")
    private  String name;

   // @Field("count")
    private Integer count;

  //  @Field("current_count")
    private Integer current_count;

  //  @Field("price")
    private Double price;

 //   @Field("date")
    private Date date;


 //   @DocumentReference(lazy = true)
    private String category;

    @Transient
    private Category data;

  //  @Field("comment")
    private String comment;



}
