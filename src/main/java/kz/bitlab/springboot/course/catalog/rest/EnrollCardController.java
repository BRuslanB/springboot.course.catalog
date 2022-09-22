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
    public List<CourseDTO> getAllCoursesByUserDTO(@PathVariable(name="username") String userName){
        return enrollCardService.getAllCoursesByUserDTO(userName);
    }

    @GetMapping(value = "/comments/{course_id}")
    public List<EnrollCardDTO> getAllEnrollCardsByCourseDTO(@PathVariable(name="course_id") Long courseId){
        return enrollCardService.getAllEnrollCardsByCourseDTO(courseId);
    }

    @GetMapping(value = "{course_id}/{user_name}")
    public EnrollCardDTO getEnrollCardByCourseAndUserDTO(@PathVariable(name="course_id") Long courseId,
                                          @PathVariable(name="user_name") String userName){
        return enrollCardService.getEnrollCardByCourseAndUserDTO(courseId, userName);
    }

    @PostMapping
    public ResponseEntity<EnrollCardDTO> setEnrollCard(@RequestBody EnrollCardDTO enrollCardDTO){
        EnrollCardDTO resEnrollCardDTO = enrollCardService.setEnrollCard(enrollCardDTO);
        if (resEnrollCardDTO != null) {
            return new ResponseEntity<>(resEnrollCardDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PutMapping
    public EnrollCardDTO updateEnrollCard(@RequestBody EnrollCardDTO enrollCardDTO){
        return enrollCardService.updateEnrollCard(enrollCardDTO);
    }

    @DeleteMapping(value = "{id}")
    public void deleteEnrollCard(@PathVariable(name="id") Long Id){
        enrollCardService.deleteEnrollCard(Id);
    }
}