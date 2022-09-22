package kz.bitlab.springboot.course.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kz.bitlab.springboot.course.catalog.model.Role;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByOrderByIdAsc();

    Role findByRole(String role_user);
}