package kz.bitlab.springboot.course.catalog.services.impl;

import kz.bitlab.springboot.course.catalog.dto.CourseDTO;
import kz.bitlab.springboot.course.catalog.dto.EnrollCardDTO;
import kz.bitlab.springboot.course.catalog.mapper.CourseMapper;
import kz.bitlab.springboot.course.catalog.mapper.EnrollCardMapper;
import kz.bitlab.springboot.course.catalog.model.Course;
import kz.bitlab.springboot.course.catalog.model.EnrollCard;
import kz.bitlab.springboot.course.catalog.model.User;
import kz.bitlab.springboot.course.catalog.repository.EnrollCardRepository;
import kz.bitlab.springboot.course.catalog.services.EnrollCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollCardServiceImpl implements EnrollCardService {
    private final CourseServiceImpl courseService;
    private final UserServiceImpl userService;
    private final CourseMapper courseMapper;
    private final EnrollCardRepository enrollCardRepository;
    private final EnrollCardMapper enrollCardMapper;

    @Override
    public List<EnrollCardDTO> getAllEnrollCardsByCourseDTO(Long courseId) {
        return enrollCardMapper.toDtoList(enrollCardRepository.findByIdAllCourses(courseId));
    }

    @Override
    public EnrollCard getEnrollCard(Long id) {
        return enrollCardRepository.findById(id).orElseThrow();
    }

    @Override
    public List<EnrollCard> getAllEnrollCards() {
        return enrollCardRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<EnrollCard> getAllEnrollCardsByCourse(Long courseId) {
        return enrollCardRepository.findByIdAllCourses(courseId);
    }

    @Override
    public EnrollCardDTO getEnrollCardByCourseAndUserDTO(Long courseId, String userName) {
        return enrollCardMapper.toDto(enrollCardRepository.findByAllCourseAndUser(courseId, userName));
    }
    public EnrollCardDTO setEnrollCard(EnrollCardDTO enrollCardDTO) {
        EnrollCard enrollCard = new EnrollCard();
        Course course = courseService.getCourse(enrollCardDTO.getCourse().getId());
        User user = userService.getUser(enrollCardDTO.getUser().getEmail());
        if (course != null && user != null) {
            List<Course> courseList = getAllCoursesByUser(user.getEmail());
            if (courseList.isEmpty() || !courseList.contains(course)) {
                enrollCard.setCourse(course);
                enrollCard.setUser(user);
                enrollCard = enrollCardRepository.save(enrollCard);
                return enrollCardMapper.toDto(enrollCard);
            }
        }
        return null;
    }

    @Override
    public void deleteEnrollCard(Long id) {
        EnrollCard enrollCard = enrollCardRepository.findById(id).orElse(null);
        if (enrollCard != null) {
            Long courseId = enrollCard.getCourse().getId();
            //удаление записи
            enrollCardRepository.delete(enrollCard);
            //обновление Course Rating
            updateCourseRating(courseId);
        }
    }

    public EnrollCardDTO updateEnrollCard(EnrollCardDTO enrollCardDTO) {
        EnrollCard enrollCard = enrollCardRepository.findById(enrollCardDTO.getId()).orElseThrow();
        enrollCard.setComment(enrollCardDTO.getComment());
        enrollCard.setUserRating(enrollCardDTO.getUserRating());
        //обновление EnrollCard
        enrollCard = enrollCardRepository.save(enrollCard);
        Long courseId = enrollCard.getCourse().getId();
        //обновление Course Rating
        updateCourseRating(courseId);

        return enrollCardMapper.toDto(enrollCard);
    }

    public void updateCourseRating(Long courseId) {
        Course course = courseService.getCourse(courseId);
        List<EnrollCard> enrollCardList = getAllEnrollCardsByCourse(courseId);
        if (course != null){
            double sum = 0; int count = 0;
            for (EnrollCard enr : enrollCardList) {
                count++;
                sum += enr.getUserRating();
            }
            if (count > 0) {
                sum = Math.round(sum/count * 100.0) / 100.0;
                course.setRating(sum);
                courseService.saveCourse(course);
            }
        }
    }

    public List<Course> getAllCoursesByUser(String userName) {
        return enrollCardRepository.findByNameAllUserCourses(userName);
    }

    @Override
    public List<CourseDTO> getAllCoursesByUserDTO(String userName) {
        return courseMapper.toDtoList(enrollCardRepository.findByNameAllUserCourses(userName));
    }

    public EnrollCard saveEnrollCard(EnrollCard enrollCard){
        return enrollCardRepository.save(enrollCard);
    }
}