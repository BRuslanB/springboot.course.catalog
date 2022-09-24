package kz.bitlab.springboot.course.catalog.services;

import kz.bitlab.springboot.course.catalog.dto.CourseDTO;
import kz.bitlab.springboot.course.catalog.dto.EnrollCardDTO;
import kz.bitlab.springboot.course.catalog.model.Course;
import kz.bitlab.springboot.course.catalog.model.EnrollCard;

import java.util.List;

public interface EnrollCardService {
    EnrollCardDTO getEnrollCardByCourseAndUserDTO(Long courseId, String userName);
    EnrollCardDTO setEnrollCard(EnrollCardDTO enrollDTO);

    EnrollCardDTO updateEnrollCard(EnrollCardDTO enrollCardDTO);

    EnrollCard getEnrollCard(Long id);
    List<EnrollCard> getAllEnrollCards();
    List<EnrollCardDTO> getAllEnrollCardsByCourseDTO(Long courseId);

    List<EnrollCard> getAllEnrollCardsByCourse(Long courseId);

    List<Course> getAllCoursesByUser(String userName);
    List<CourseDTO> getAllCoursesByUserDTO(String userName);

    EnrollCard saveEnrollCard(EnrollCard enrollCard);

    void deleteEnrollCard(Long id);

    void updateCourseRating(Long courseId);
}