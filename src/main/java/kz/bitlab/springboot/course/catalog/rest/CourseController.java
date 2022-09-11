package kz.bitlab.springboot.course.catalog.rest;

import kz.bitlab.springboot.course.catalog.services.impl.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CourseDTO>> getAllCoursesDTO(){
        return new ResponseEntity<>(courseService.getAllCoursesDTO(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CourseDTO> getCourseDTO(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(courseService.getCourseDTO(id), HttpStatus.OK);
    }
}