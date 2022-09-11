package kz.bitlab.springboot.course.catalog.mapper;

import kz.bitlab.springboot.course.catalog.dto.UserDTO;
import kz.bitlab.springboot.course.catalog.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
    List<UserDTO> toDtoList(List<User> users);
}