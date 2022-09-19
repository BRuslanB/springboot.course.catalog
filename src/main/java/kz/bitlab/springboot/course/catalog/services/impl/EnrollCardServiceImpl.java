package kz.bitlab.springboot.course.catalog.services.impl;

import kz.bitlab.springboot.course.catalog.dto.CourseDTO;
import kz.bitlab.springboot.course.catalog.dto.EnrollCardDTO;
import kz.bitlab.springboot.course.catalog.dto.UserDTO;
import kz.bitlab.springboot.course.catalog.mapper.CourseMapper;
import kz.bitlab.springboot.course.catalog.mapper.EnrollCardMapper;
import kz.bitlab.springboot.course.catalog.mapper.UserMapper;
import kz.bitlab.springboot.course.catalog.model.Course;
import kz.bitlab.springboot.course.catalog.model.EnrollCard;
import kz.bitlab.springboot.course.catalog.model.User;
import kz.bitlab.springboot.course.catalog.repository.EnrollCardRepository;
import kz.bitlab.springboot.course.catalog.repository.UserRepository;
import kz.bitlab.springboot.course.catalog.services.EnrollCardService;
import kz.bitlab.springboot.course.catalog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollCardServiceImpl implements EnrollCardService {

    private final CourseServiceImpl courseService;
    private final CourseMapper courseMapper;
    private final EnrollCardRepository enrollCardRepository;
    private final EnrollCardMapper enrollCardMapper;

    @Override
    public List<EnrollCardDTO> getAllCommentsDTO(Long courseId) {
        return enrollCardMapper.toDtoList(enrollCardRepository.findByIdAllCourses(courseId));
    }

//    public EnrollCard getEnrollCard(Long courseId, Long userId){
//        return enrollCardRepository.findByIdCourseAndUser(courseId, userId);
//    }

    @Override
    public EnrollCardDTO getEnrollCardDTO(Long courseId, Long userId) {
        return enrollCardMapper.toDto(enrollCardRepository.findByIdCourseAndUser(courseId, userId));
    }
    public EnrollCardDTO setEnrollCard(EnrollCardDTO enrollCardDTO) {

        EnrollCard enrollCard = enrollCardMapper.toEntity(enrollCardDTO);
        Course course = courseService.getCourse(enrollCardDTO.getCourseId());
        List<Course> courseList = getAllUserCourses(enrollCardDTO.getUserId());

        if (course != null) {
            if (courseList.isEmpty() || !courseList.contains(course)) {
                enrollCard = enrollCardRepository.save(enrollCard);
            }
        }
        return enrollCardMapper.toDto(enrollCard);
    }

    @Override
    public void deleteEnrollCard(Long id) {
        EnrollCard enrollCard = enrollCardRepository.findById(id).orElseThrow();
        if (enrollCard != null) {
            enrollCardRepository.delete(enrollCard);
        }
    }

    public EnrollCardDTO updateEnrollCard(EnrollCardDTO enrollCardDTO) {

        EnrollCard enrollCard = enrollCardRepository.findById(enrollCardDTO.getId()).orElseThrow();
        enrollCard.setComment(enrollCardDTO.getComment());
        enrollCard.setUserRating(enrollCardDTO.getUserRating());
        enrollCard = enrollCardRepository.save(enrollCard);

        return enrollCardMapper.toDto(enrollCard);
    }

    public List<Course> getAllUserCourses(Long userId){
        return enrollCardRepository.findByIdAllUserCourses(userId);
    }

    public List<Course> getAllUserCourses(String userName) {
        return enrollCardRepository.findByNameAllUserCourses(userName);
    }

    public List<CourseDTO> getAllUserCoursesDTO(Long userId) {
        return courseMapper.toDtoList(enrollCardRepository.findByIdAllUserCourses(userId));
    }

    public List<CourseDTO> getAllUserCoursesDTO(String userName) {
        return courseMapper.toDtoList(enrollCardRepository.findByNameAllUserCourses(userName));
    }
}