package com.moaaz.modernhome.Product;

import com.moaaz.modernhome.Category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String details;

    private List<String>images;
    private double price;
    private double discount;

    private double total;

    private LocalDate creationDate;

    @ManyToOne
    private Category category;

}
