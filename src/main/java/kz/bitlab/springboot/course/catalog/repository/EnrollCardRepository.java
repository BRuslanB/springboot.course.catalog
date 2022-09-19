package kz.bitlab.springboot.course.catalog.repository;

import kz.bitlab.springboot.course.catalog.model.Course;
import kz.bitlab.springboot.course.catalog.model.EnrollCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface EnrollCardRepository extends JpaRepository<EnrollCard, Long> {

    @Query("SELECT c.course FROM EnrollCard c WHERE c.user.id = :user_id ORDER BY c.id DESC")
    List<Course> findByIdAllUserCourses(Long user_id);

    @Query("SELECT c.course FROM EnrollCard c WHERE c.user.email = :user_name ORDER BY c.id DESC")
    List<Course> findByNameAllUserCourses(String user_name);

    @Query("SELECT c FROM EnrollCard c WHERE c.course.id = :course_id ORDER BY c.id")
    List<EnrollCard> findByIdAllCourses(Long course_id);

    @Query("SELECT c FROM EnrollCard c WHERE c.course.id = :course_id AND c.user.id = :user_id")
    EnrollCard findByIdCourseAndUser(Long course_id, Long user_id);

}