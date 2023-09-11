package com.moaaz.modernhome.Category;

import com.moaaz.modernhome.Product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String details;
    private LocalDate creationDate;


    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "category")
    private List<Product> products;
}
