package kz.bitlab.springboot.course.catalog.mapper;

import kz.bitlab.springboot.course.catalog.dto.CourseDTO;
import kz.bitlab.springboot.course.catalog.dto.EnrollCardDTO;
import kz.bitlab.springboot.course.catalog.model.Course;
import kz.bitlab.springboot.course.catalog.model.EnrollCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollCardMapper {

    @Mappings({
            @Mapping(target = "courseId", source = "course.id"),
            @Mapping(target = "userId", source = "user.id")
    })
    EnrollCardDTO toDto(EnrollCard enrollCard);

    @Mappings({
            @Mapping(target = "course.id", source = "courseId"),
            @Mapping(target = "user.id", source = "userId")
    })
    EnrollCard toEntity(EnrollCardDTO enrollCardDTO);

    List<EnrollCardDTO> toDtoList(List<EnrollCard> enrollCards);

    //List<CourseDTO> toDtoCourseList(List<Course> coursesList);
}