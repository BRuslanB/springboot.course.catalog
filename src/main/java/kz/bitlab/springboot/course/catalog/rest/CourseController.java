package kz.bitlab.springboot.course.catalog.rest;

import kz.bitlab.springboot.course.catalog.services.impl.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import kz.bitlab.springboot.course.catalog.dto.CourseDTO;

import java.util.List;

@RestController
@RequestMapping(value = "/api/courses")
@CrossOrigin
@RequiredArgsConstructor
public class CourseController {

    private final CourseServiceImpl courseService;

    @GetMapping
    public List<CourseDTO> getAllCoursesDTO(){
        return courseService.getAllCoursesDTO();
    }

    @GetMapping(value = "{id}")
    public CourseDTO getCourseDTO(@PathVariable(name="id") Long id){
        return courseService.getCourseDTO(id);
    }
}