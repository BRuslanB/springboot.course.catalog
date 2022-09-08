package kz.bitlab.springboot.course.catalog.services.impl;

import kz.bitlab.springboot.course.catalog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import kz.bitlab.springboot.course.catalog.dto.CategoryDTO;
import kz.bitlab.springboot.course.catalog.mapper.CategoryMapper;
import kz.bitlab.springboot.course.catalog.model.Category;
import kz.bitlab.springboot.course.catalog.repository.CategoryRepository;
import kz.bitlab.springboot.course.catalog.repository.CourseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CourseRepository courseRepository;

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory(Category category){
        courseRepository.deleteByCategory(category);
        categoryRepository.delete(category);
    }

    public Category getCategory(Long id){
        return categoryRepository.findById(id).orElseThrow();
    }
    public CategoryDTO getCategoryDTO(Long id){
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow());
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public List<CategoryDTO> getAllCategoriesDTO(){
        return categoryMapper.toDtoList(categoryRepository.findAll());
    }
}