package com.moaaz.modernhome.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // add
    public Category addCategory(Category category) {
        category.setCreationDate(LocalDate.now());
        return categoryRepository.save(category);
    }

    // update
    public CategoryResponse updateCategory(Category category, long categoryId) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setName(category.getName());
        existingCategory.setDetails(category.getDetails());
        return convertCategoryToCategoryResponse(categoryRepository.save(existingCategory));
    }

    // delete
    public void deleteCategory(long categoryId) {
        getCategoryById(categoryId);
        categoryRepository.deleteById(categoryId);
    }

    // get all
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository
                .findAll()
                .stream()
                .map(category -> convertCategoryToCategoryResponse(category))
                .toList();
    }

    public List<CategoryResponse> search(String text) {
        return categoryRepository.findAllByNameContainingOrDetailsContaining(text)
                .stream().map(category -> convertCategoryToCategoryResponse(category))
                .toList();

    }


    // convert category to category response
    private CategoryResponse convertCategoryToCategoryResponse(Category category) {
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .details(category.getDetails())
                .creationDate(category.getCreationDate())
                .numberOfProducts(category.getProducts().size())
                .build();
    }

    // get by id
    public Category getCategoryById(long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new NoSuchElementException("There Are No Category With Id = " + categoryId)
        );
    }

}
