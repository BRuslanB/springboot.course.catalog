package kz.bitlab.springboot.course.catalog.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import kz.bitlab.springboot.course.catalog.dto.CategoryDTO;
import kz.bitlab.springboot.course.catalog.services.impl.CategoryServiceImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categories")
@CrossOrigin
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @GetMapping
    public List<CategoryDTO> getAllCategoriesDTO(){
        return categoryService.getAllCategoriesDTO();
    }

    @GetMapping(value = "{id}")
    public CategoryDTO getCategoryDTO(@PathVariable(name="id") Long id){
        return categoryService.getCategoryDTO(id);
    }
}