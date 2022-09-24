package kz.bitlab.springboot.course.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kz.bitlab.springboot.course.catalog.model.Category;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByOrderByIdAsc();
}