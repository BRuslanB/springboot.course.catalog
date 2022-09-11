package kz.bitlab.springboot.course.catalog.services;

import kz.bitlab.springboot.course.catalog.dto.CourseDTO;
import kz.bitlab.springboot.course.catalog.dto.EnrollCardDTO;
import kz.bitlab.springboot.course.catalog.model.Course;

import java.util.List;

public interface EnrollCardService {
    EnrollCardDTO setEnrollCard(EnrollCardDTO enrollDTO);

    List<Course> getAllUserCourses(Long userId);

    List<CourseDTO> getAllUserCoursesDTO(Long userId);

    List<Course> getAllUserCourses(String username);
    List<CourseDTO> getAllUserCoursesDTO(String username);
}