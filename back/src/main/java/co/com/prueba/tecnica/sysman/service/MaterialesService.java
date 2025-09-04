package co.com.prueba.tecnica.sysman.service;

import co.com.prueba.tecnica.sysman.entity.Material;
import co.com.prueba.tecnica.sysman.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MaterialesService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialesService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> obtenerTodosLosMateriales() {
        return materialRepository.findAll();
    }

    public List<Material> obtenerMaterialesPorTipoYFecha(String tipo, String desde) {
        if( tipo == null || tipo.isEmpty() ) {
            throw new IllegalArgumentException("El tipo de material no puede estar vacío");
        }
        List<Material> materiales = materialRepository.findByTipoAndFechaCompra(tipo, LocalDate.parse(desde));
        if( materiales.isEmpty() ) {
            throw new IllegalArgumentException("No se encontraron materiales con los criterios especificados");
        }else {
            return materiales;
        }
    }

    public List<Material> obtenerMaterialesPorCiudad(Long ciudadId) {
        if( ciudadId == null ) {
            throw new IllegalArgumentException("El ID de la ciudad no puede ser nulo");
        }
        List<Material> materiales = materialRepository.buscarPorCiudad(ciudadId);
        if( materiales.isEmpty() ) {
            throw new IllegalArgumentException("No se encontraron materiales para la ciudad con ID: " + ciudadId);
        }else {
            return materiales;
        }
    }


}
