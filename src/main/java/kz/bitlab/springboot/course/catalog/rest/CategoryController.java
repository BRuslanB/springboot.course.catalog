package kz.bitlab.springboot.course.catalog.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kz.bitlab.springboot.course.catalog.dto.CategoryDTO;
import kz.bitlab.springboot.course.catalog.services.impl.CategoryServiceImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categories")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategoriesDTO(){
        return new ResponseEntity<>(categoryService.getAllCategoriesDTO(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CategoryDTO> getCategoryDTO(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(categoryService.getCategoryDTO(id), HttpStatus.OK);
    }
}