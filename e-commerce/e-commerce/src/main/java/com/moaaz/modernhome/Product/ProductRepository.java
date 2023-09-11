package com.moaaz.modernhome.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {

    public List<Product> findAllByNameContaining(String text);

    public List<Product> findAllByDetailsContaining(String text);


}
