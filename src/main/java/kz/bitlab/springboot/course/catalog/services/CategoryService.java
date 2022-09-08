package kz.bitlab.springboot.course.catalog.services;

import kz.bitlab.springboot.course.catalog.dto.CategoryDTO;
import kz.bitlab.springboot.course.catalog.model.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    Category saveCategory(Category category);
    void deleteCategory(Category category);
    Category getCategory(Long id);
    CategoryDTO getCategoryDTO(Long id);
    List<Category> getAllCategories();
    List<CategoryDTO> getAllCategoriesDTO();
}