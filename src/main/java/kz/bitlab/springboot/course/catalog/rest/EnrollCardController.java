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

    @GetMapping(value = "/courses/{username}")
    public ResponseEntity<List<CourseDTO>> getAllUserCoursesDTO(@PathVariable(name="username") String userName){
        return new ResponseEntity<>(enrollCardService.getAllUserCoursesDTO(userName), HttpStatus.OK);
    }

    @GetMapping(value = "/comments/{course_id}")
    public ResponseEntity<List<EnrollCardDTO>> getAllCommentsDTO(@PathVariable(name="course_id") Long courseId){
        return new ResponseEntity<>(enrollCardService.getAllCommentsDTO(courseId), HttpStatus.OK);
    }

    @GetMapping(value = "{course_id}/{user_id}")
    public ResponseEntity<EnrollCardDTO> getEnrollCardDTO(@PathVariable(name="course_id") Long courseId,
                                                          @PathVariable(name="user_id") Long userId){
        return new ResponseEntity<>(enrollCardService.getEnrollCardDTO(courseId, userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnrollCardDTO> setEnrollCard(@RequestBody EnrollCardDTO enrollCardDTO){
        return new ResponseEntity<>(enrollCardService.setEnrollCard(enrollCardDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EnrollCardDTO> updateEnrollCard(@RequestBody EnrollCardDTO enrollCardDTO){
        return new ResponseEntity<>(enrollCardService.updateEnrollCard(enrollCardDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "{id}")
    public void deleteEnrollCard(@PathVariable(name="id") Long Id){
        enrollCardService.deleteEnrollCard(Id);
    }
}