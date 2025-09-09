package co.com.prueba.tecnica.sysman.service;

import co.com.prueba.tecnica.sysman.dto.MaterialesDto;
import co.com.prueba.tecnica.sysman.entity.Ciudad;
import co.com.prueba.tecnica.sysman.entity.Material;
import co.com.prueba.tecnica.sysman.repository.CiudadRepository;
import co.com.prueba.tecnica.sysman.repository.MaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MaterialesServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private CiudadRepository ciudadRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MaterialesService materialesService;

    private Material material;
    private MaterialesDto materialDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        material = new Material();
        material.setId(1L);
        material.setNombre("Cemento");
        material.setTipo("Construcción");
        material.setFechaCompra(LocalDate.of(2023, 1, 1));

        materialDto = new MaterialesDto();
        materialDto.setNombre("Cemento");
        materialDto.setTipo("Construcción");
        materialDto.setFechaCompra(LocalDate.of(2023, 1, 1));
    }

    @Test
    void obtenerTodosLosMateriales_debeRetornarLista() {
        when(materialRepository.findAll()).thenReturn(List.of(material));

        List<Material> result = materialesService.obtenerTodosLosMateriales();

        assertEquals(1, result.size());
        assertEquals("Cemento", result.get(0).getNombre());
        verify(materialRepository, times(1)).findAll();
    }

    @Test
    void obtenerMaterialesPorTipoYFecha_exitoso() {
        when(materialRepository.findByTipoAndFechaCompra(eq("Construcción"), any(LocalDate.class)))
                .thenReturn(List.of(material));

        List<Material> result = materialesService.obtenerMaterialesPorTipoYFecha("Construcción", "2023-01-01");

        assertFalse(result.isEmpty());
        assertEquals("Cemento", result.get(0).getNombre());
    }

    @Test
    void obtenerMaterialesPorTipoYFecha_tipoVacioDebeFallar() {
        assertThrows(IllegalArgumentException.class,
                () -> materialesService.obtenerMaterialesPorTipoYFecha("", "2023-01-01"));
    }

    @Test
    void obtenerMaterialesPorTipoYFecha_sinResultadosDebeFallar() {
        when(materialRepository.findByTipoAndFechaCompra(eq("Construcción"), any(LocalDate.class)))
                .thenReturn(Collections.emptyList());

        assertThrows(IllegalArgumentException.class,
                () -> materialesService.obtenerMaterialesPorTipoYFecha("Construcción", "2023-01-01"));
    }

    @Test
    void obtenerMaterialesPorCiudad_exitoso() {
        when(materialRepository.buscarPorCiudad(1L)).thenReturn(List.of(material));

        List<Material> result = materialesService.obtenerMaterialesPorCiudad(1L);

        assertEquals(1, result.size());
    }

    @Test
    void obtenerMaterialesPorCiudad_ciudadIdNuloDebeFallar() {
        assertThrows(IllegalArgumentException.class,
                () -> materialesService.obtenerMaterialesPorCiudad(null));
    }

    @Test
    void obtenerMaterialesPorCiudad_sinResultadosDebeFallar() {
        when(materialRepository.buscarPorCiudad(1L)).thenReturn(Collections.emptyList());

        assertThrows(IllegalArgumentException.class,
                () -> materialesService.obtenerMaterialesPorCiudad(1L));
    }

    @Test
    void guardarMaterial_exitoso() {
        when(modelMapper.map(materialDto, Material.class)).thenReturn(material);
        when(materialRepository.save(any(Material.class))).thenReturn(material);

        Material result = materialesService.guardarMaterial(materialDto);

        assertNotNull(result);
        assertEquals("Cemento", result.getNombre());
        verify(materialRepository, times(1)).save(any(Material.class));
    }

    @Test
    void guardarMaterial_conDtoNuloDebeFallar() {
        assertThrows(IllegalArgumentException.class, () -> materialesService.guardarMaterial(null));
    }

    @Test
    void actualizarMaterial_exitoso() {
        Ciudad ciudad = new Ciudad();
        ciudad.setId(10L);

        materialDto.setCiudadId(10L);

        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));
        when(ciudadRepository.findById(10L)).thenReturn(Optional.of(ciudad));
        when(materialRepository.save(any(Material.class))).thenReturn(material);

        Material result = materialesService.actualizarMaterial(1L, materialDto);

        assertNotNull(result);
        assertEquals("Cemento", result.getNombre());
    }

    @Test
    void actualizarMaterial_materialNoExisteDebeFallar() {
        when(materialRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> materialesService.actualizarMaterial(99L, materialDto));
    }

    @Test
    void actualizarMaterial_ciudadNoExisteDebeFallar() {
        materialDto.setCiudadId(10L);

        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));
        when(ciudadRepository.findById(10L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> materialesService.actualizarMaterial(1L, materialDto));
    }

    @Test
    void validarDatosEntrada_fechaCompraMayorFechaVentaDebeFallar() {
        materialDto.setFechaCompra(LocalDate.of(2023, 2, 1));
        materialDto.setFechaVenta(LocalDate.of(2023, 1, 1));

        assertThrows(IllegalArgumentException.class,
                () -> materialesService.validarDatosEntrada(materialDto));
    }

}