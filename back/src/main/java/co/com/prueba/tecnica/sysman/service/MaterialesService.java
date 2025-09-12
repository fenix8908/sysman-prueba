package co.com.prueba.tecnica.sysman.service;

import co.com.prueba.tecnica.sysman.dto.MaterialesDto;
import co.com.prueba.tecnica.sysman.entity.Ciudad;
import co.com.prueba.tecnica.sysman.entity.Material;
import co.com.prueba.tecnica.sysman.repository.CiudadRepository;
import co.com.prueba.tecnica.sysman.repository.MaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MaterialesService {

    //private final MaterialsMapper materialsMapper = MaterialsMapper.INSTANCIA_MAPPER;
    private final Logger log = LoggerFactory.getLogger(MaterialesService.class);
    private final MaterialRepository materialRepository;
    private final CiudadRepository ciudadRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public MaterialesService(MaterialRepository materialRepository,
                             CiudadRepository ciudadRepository, ModelMapper modelMapper) {
        this.materialRepository = materialRepository;
        this.ciudadRepository = ciudadRepository;
        this.modelMapper = modelMapper;
    }

    public List<Material> obtenerTodosLosMateriales() {
        return materialRepository.findAll();
    }

    public List<Material> obtenerMaterialesPorTipoYFecha(String tipo, String desde) {
        if( tipo == null || tipo.isEmpty() ) {
            log.error("El tipo de material no puede estar vacío");
            throw new IllegalArgumentException("El tipo de material no puede estar vacío");
        }
        List<Material> materiales = materialRepository.findByTipoAndFechaCompra(tipo, LocalDate.parse(desde));
        if( materiales.isEmpty() ) {
            log.error("No se encontraron materiales con los criterios especificados");
            throw new IllegalArgumentException("No se encontraron materiales con los criterios especificados");
        }else {
            return materiales;
        }
    }

    public List<Material> obtenerMaterialesPorCiudad(Long ciudadId) {
        if( ciudadId == null ) {
            log.error("El ID de la ciudad no puede ser nulo");
            throw new IllegalArgumentException("El ID de la ciudad no puede ser nulo");
        }
        List<Material> materiales = materialRepository.buscarPorCiudad(ciudadId);
        if( materiales.isEmpty() ) {
            log.error("No se encontraron materiales para la ciudad con ID: " + ciudadId);
            throw new IllegalArgumentException("No se encontraron materiales para la ciudad con ID: " + ciudadId);
        }else {
            return materiales;
        }
    }

    @Transactional
    public Material guardarMaterial(MaterialesDto dto) {
        validarDatosEntrada(dto);
        Material materialEntity = modelMapper.map(dto, Material.class);
        Ciudad ciudad= new Ciudad();
        ciudad.setId(dto.getCiudadId());
        materialEntity.setCiudad(ciudad);
        return materialRepository.save(materialEntity);
    }



    @Transactional
    public Material actualizarMaterial(Long id, MaterialesDto materialDto) {
        Material materialExistente = materialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Material no encontrado con ID: " + id));
        validarDatosEntrada(materialDto);
        materialExistente.setNombre(materialDto.getNombre());
        materialExistente.setDescripcion(materialDto.getDescripcion());
        materialExistente.setTipo(materialDto.getTipo());
        materialExistente.setPrecio(materialDto.getPrecio());
        materialExistente.setFechaCompra(materialDto.getFechaCompra());
        materialExistente.setFechaVenta(materialDto.getFechaVenta());
        materialExistente.setEstado(materialDto.getEstado());
        Ciudad ciudad = ciudadRepository.findById(materialDto.getCiudadId())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada con ID: " + materialDto.getCiudadId()));
        materialExistente.setCiudad(ciudad);
        return materialRepository.save(materialExistente);
    }

    public void validarDatosEntrada(MaterialesDto dto) {
        if(dto == null) {
            log.error("El material no puede ser nulo");
            throw new IllegalArgumentException("El material no puede ser nulo");
        }
        if(dto.getNombre() == null || dto.getNombre().isEmpty()) {
            log.error("El nombre del material es obligatorio");
            throw new IllegalArgumentException("El nombre del material es obligatorio");
        }
        if(dto.getFechaCompra() != null && dto.getFechaVenta() != null
                && dto.getFechaCompra().isAfter(dto.getFechaVenta())) {
            log.error("La fecha de compra no puede ser mayor a la fecha de venta");
            throw new IllegalArgumentException("La fecha de compra no puede ser posterior a la fecha de venta");
        }
    }

    public Material obtenerMaterialPorId(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Material no encontrado con ID: " + id));

    }

    public void eliminarMaterial(Long id) {
        if( !materialRepository.existsById(id) ) {
            log.error("Material no encontrado con ID: {}", id);
            throw new IllegalArgumentException("Material no encontrado" );
        }
        materialRepository.deleteById(id);
    }


}
