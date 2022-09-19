package kz.bitlab.springboot.course.catalog.services;

import kz.bitlab.springboot.course.catalog.dto.CourseDTO;
import kz.bitlab.springboot.course.catalog.dto.EnrollCardDTO;
import kz.bitlab.springboot.course.catalog.model.Course;

import java.util.List;

public interface EnrollCardService {
    EnrollCardDTO getEnrollCardDTO(Long courseId, Long userId);
    EnrollCardDTO setEnrollCard(EnrollCardDTO enrollDTO);

    EnrollCardDTO updateEnrollCard(EnrollCardDTO enrollCardDTO);

    List<EnrollCardDTO> getAllCommentsDTO(Long courseId);
    List<Course> getAllUserCourses(Long userId);

    List<CourseDTO> getAllUserCoursesDTO(Long userId);

    List<Course> getAllUserCourses(String username);
    List<CourseDTO> getAllUserCoursesDTO(String username);

    void deleteEnrollCard(Long id);
}