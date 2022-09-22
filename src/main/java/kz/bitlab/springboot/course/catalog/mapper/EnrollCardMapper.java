package kz.bitlab.springboot.course.catalog.mapper;

import kz.bitlab.springboot.course.catalog.dto.EnrollCardDTO;
import kz.bitlab.springboot.course.catalog.model.EnrollCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollCardMapper {

    EnrollCardDTO toDto(EnrollCard enrollCard);

    EnrollCard toEntity(EnrollCardDTO enrollCardDTO);

    List<EnrollCardDTO> toDtoList(List<EnrollCard> enrollCards);
}