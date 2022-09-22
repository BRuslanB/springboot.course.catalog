package kz.bitlab.springboot.course.catalog.services.impl;

import kz.bitlab.springboot.course.catalog.model.Role;
import kz.bitlab.springboot.course.catalog.repository.RoleRepository;
import kz.bitlab.springboot.course.catalog.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public Role getRole(Long id){
        return roleRepository.findById(id).orElse(null);
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAllByOrderByIdAsc();
    }
}