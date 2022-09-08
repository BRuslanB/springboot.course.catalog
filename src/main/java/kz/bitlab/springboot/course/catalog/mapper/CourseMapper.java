package kz.bitlab.springboot.course.catalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import kz.bitlab.springboot.course.catalog.dto.CourseDTO;
import kz.bitlab.springboot.course.catalog.model.Course;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mappings({
            @Mapping(target = "priceValue", source = "price"),
            @Mapping(target = "ratingValue", source = "rating"),
            @Mapping(target = "content", source = "contentUrl")
    })
    CourseDTO toDto(Course course);

    @Mappings({
            @Mapping(target = "price", source = "priceValue"),
            @Mapping(target = "rating", source = "ratingValue"),
            @Mapping(target = "contentUrl", source = "content")
    })
    Course toEntity(CourseDTO courseDTO);

    List<CourseDTO> toDtoList(List<Course> coursesList);
}
