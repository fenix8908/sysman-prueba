package co.com.prueba.tecnica.sysman.mappers;

import co.com.prueba.tecnica.sysman.dto.MaterialDTO;
import co.com.prueba.tecnica.sysman.entity.Material;


public class MaterialMapper {

    public static MaterialDTO convertirEntityToDto(Material m) {
        return new MaterialDTO(m.getId(), m.getNombre(), m.getDescripcion(),
                m.getTipo(), m.getPrecio(),m.getFechaCompra(),
                m.getFechaVenta(), m.getEstado(), m.getCiudad().getId());
    }
}
