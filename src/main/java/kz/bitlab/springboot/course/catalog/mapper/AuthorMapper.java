package kz.bitlab.springboot.course.catalog.mapper;

import kz.bitlab.springboot.course.catalog.dto.AuthorDTO;
import kz.bitlab.springboot.course.catalog.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDto(User user);
    User toEntity(AuthorDTO authorDTO);
    List<AuthorDTO> toDtoList(List<User> usersList);
}