package kz.bitlab.springboot.course.catalog.rest;

import kz.bitlab.springboot.course.catalog.dto.CourseDTO;
import kz.bitlab.springboot.course.catalog.dto.EnrollCardDTO;
import kz.bitlab.springboot.course.catalog.services.impl.EnrollCardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/enroll")
@CrossOrigin
@RequiredArgsConstructor
public class EnrollCardController {

    private final EnrollCardServiceImpl enrollCardService;

    @GetMapping(value = "{username}")
    public ResponseEntity<List<CourseDTO>> getAllUserCoursesDTO(@PathVariable(name="username") String userName){
        return new ResponseEntity<>(enrollCardService.getAllUserCoursesDTO(userName), HttpStatus.OK);
    }

//    @GetMapping(value = "{id}")
//    public ResponseEntity<List<CourseDTO>> getAllUserCoursesDTO(@PathVariable(name="id") Long id){
//        return new ResponseEntity<>(enrollCardService.getAllUserCoursesDTO(id), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<EnrollCardDTO> setEnrollCard(@RequestBody EnrollCardDTO enrollCardDTO){
        return new ResponseEntity<>(enrollCardService.setEnrollCard(enrollCardDTO), HttpStatus.CREATED);
    }
}