package kz.bitlab.springboot.course.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import kz.bitlab.springboot.course.catalog.model.Category;
import kz.bitlab.springboot.course.catalog.model.Course;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Long> {
    Long deleteByCategory(Category category);

    List<Course> findAllByOrderByIdAsc();

    @Query("SELECT c FROM Course c WHERE c.author.id = :author_id ORDER BY c.id DESC")
    List<Course> findByAuthorIdAllCourses(Long author_id);
}
