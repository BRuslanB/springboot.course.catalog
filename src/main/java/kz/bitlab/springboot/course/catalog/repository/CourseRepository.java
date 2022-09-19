package kz.bitlab.springboot.course.catalog.repository;

import kz.bitlab.springboot.course.catalog.dto.CourseDTO;
import kz.bitlab.springboot.course.catalog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
