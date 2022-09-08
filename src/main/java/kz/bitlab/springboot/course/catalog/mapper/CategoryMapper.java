package kz.bitlab.springboot.course.catalog.mapper;

import org.mapstruct.Mapper;
import kz.bitlab.springboot.course.catalog.dto.CategoryDTO;
import kz.bitlab.springboot.course.catalog.model.Category;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDto(Category category);
    Category toEntity(CategoryDTO categoryDTO);
    List<CategoryDTO> toDtoList(List<Category> categoriesList);
}
