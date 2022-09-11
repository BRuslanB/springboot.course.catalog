package kz.bitlab.springboot.course.catalog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String email;
    private String fullName;
}
