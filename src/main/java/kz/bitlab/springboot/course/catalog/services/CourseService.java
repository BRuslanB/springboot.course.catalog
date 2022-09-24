package kz.bitlab.springboot.course.catalog.services;

import kz.bitlab.springboot.course.catalog.dto.CourseDTO;
import kz.bitlab.springboot.course.catalog.model.Category;
import kz.bitlab.springboot.course.catalog.model.Course;

import java.util.List;

public interface CourseService {

    Course addCourse(Course course);
    Course saveCourse(Course course);
    void deleteCourse(Course course);
    Course getCourse(Long id);
    CourseDTO getCourseDTO(Long id);
    List<CourseDTO> getAllCoursesDTO();
    List<Course> getAllCourses();
    List<Course> getAllCoursesByAuthor(Long authorId);
    //List<Category> getAllCategoriesByAuthor(Long authorId);
}