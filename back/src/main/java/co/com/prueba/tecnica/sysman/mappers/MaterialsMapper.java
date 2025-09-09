package co.com.prueba.tecnica.sysman.mappers;

import co.com.prueba.tecnica.sysman.dto.MaterialDTO;
import co.com.prueba.tecnica.sysman.dto.MaterialesDto;
import co.com.prueba.tecnica.sysman.entity.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface MaterialsMapper {

    //MaterialsMapper INSTANCIA_MAPPER = Mappers.getMapper(MaterialsMapper.class);

    @Mapping(target = "ciudadId", source = "ciudad.id")
    MaterialesDto convertirEntityToDto(Material material);

    @Mapping(target = "ciudad", ignore = true) // La ciudad se maneja por separado
    Material convertirDtoToEntity(MaterialesDto dto);
}
