package kz.bitlab.springboot.course.catalog.services;

import kz.bitlab.springboot.course.catalog.model.Role;

import java.util.List;

public interface RoleService {
    Role getRole(Long id);
    List<Role> getAllRoles();
}