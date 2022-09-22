package kz.bitlab.springboot.course.catalog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollCardDTO {
    private Long id;
    private String comment;
    private int userRating;
    private int point;
    private CourseDTO course;
//    private Long courseId;
    private UserDTO user;
//    private Long userId;
}