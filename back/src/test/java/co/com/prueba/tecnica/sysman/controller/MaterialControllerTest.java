package co.com.prueba.tecnica.sysman.controller;

import co.com.prueba.tecnica.sysman.dto.GeneralResponseDto;
import co.com.prueba.tecnica.sysman.dto.MaterialesDto;
import co.com.prueba.tecnica.sysman.entity.Material;
import co.com.prueba.tecnica.sysman.service.MaterialesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MaterialControllerTest {

    @Mock
    private MaterialesService materialesService;

    @InjectMocks
    private MaterialController materialController;

    private Material material;
    private MaterialesDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        material = new Material();
        material.setId(1L);
        material.setNombre("Cemento");
        material.setTipo("Construcción");
        material.setFechaCompra(LocalDate.of(2023, 1, 1));

        dto = new MaterialesDto();
        dto.setNombre("Cemento");
        dto.setTipo("Construcción");
        dto.setFechaCompra(LocalDate.of(2023, 1, 1));
    }

    @Test
    void obtenerMateriales_exitoso() {
        when(materialesService.obtenerTodosLosMateriales()).thenReturn(List.of(material));

        ResponseEntity<GeneralResponseDto<Object>> response = materialController.obtenerMateriales();

        assertTrue(response.getBody().isExito());
        verify(materialesService, times(1)).obtenerTodosLosMateriales();
    }

    @Test
    void obtenerMateriales_conError() {
        when(materialesService.obtenerTodosLosMateriales()).thenThrow(new RuntimeException("DB error"));

        ResponseEntity<GeneralResponseDto<Object>> response = materialController.obtenerMateriales();

        assertEquals(400, response.getStatusCodeValue());
        assertFalse(response.getBody().isExito());
    }

    @Test
    void obtenerMaterialesPorTipoYFecha_exitoso() {
        when(materialesService.obtenerMaterialesPorTipoYFecha("Construcción", "2023-01-01"))
                .thenReturn(List.of(material));

        ResponseEntity<GeneralResponseDto<Object>> response =
                materialController.obtenerMaterialesPorTipoYFecha("Construcción", "2023-01-01");

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().isExito());

    }

    @Test
    void obtenerMaterialesPorTipoYFecha_conError() {
        when(materialesService.obtenerMaterialesPorTipoYFecha("Construcción", "2023-01-01"))
                .thenThrow(new IllegalArgumentException("sin resultados"));

        ResponseEntity<GeneralResponseDto<Object>> response =
                materialController.obtenerMaterialesPorTipoYFecha("Construcción", "2023-01-01");

        assertEquals(400, response.getStatusCode().value());
        assertFalse(response.getBody().isExito());
    }

    @Test
    void obtenerMaterialesPorCiudad_exitoso() {
        when(materialesService.obtenerMaterialesPorCiudad(1L)).thenReturn(List.of(material));

        ResponseEntity<GeneralResponseDto<Object>> response = materialController.obtenerMaterialesPorCiudad(1L);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().isExito());
    }

    @Test
    void obtenerMaterialesPorCiudad_conError() {
        when(materialesService.obtenerMaterialesPorCiudad(1L))
                .thenThrow(new IllegalArgumentException("no existe ciudad"));

        ResponseEntity<GeneralResponseDto<Object>> response = materialController.obtenerMaterialesPorCiudad(1L);

        assertEquals(400, response.getStatusCode().value());
        assertFalse(response.getBody().isExito());
    }

    @Test
    void guardarMaterial_exitoso() {
        when(materialesService.guardarMaterial(dto)).thenReturn(material);

        ResponseEntity<GeneralResponseDto<Object>> response = materialController.guardarMaterial(dto);

        assertEquals(200,response.getStatusCode().value());
        assertTrue(response.getBody().isExito());
    }

    @Test
    void guardarMaterial_falla() {
        when(materialesService.guardarMaterial(dto)).thenReturn(null);

        ResponseEntity<GeneralResponseDto<Object>> response = materialController.guardarMaterial(dto);

        assertEquals(400, response.getStatusCode().value());
        assertFalse(response.getBody().isExito());
    }

    @Test
    void actualizarMaterial_exitoso() {
        when(materialesService.actualizarMaterial(1L, dto)).thenReturn(material);

        ResponseEntity<GeneralResponseDto<Object>> response = materialController.actualizarMaterial(1L, dto);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().isExito());
    }

    @Test
    void actualizarMaterial_falla() {
        when(materialesService.actualizarMaterial(1L, dto)).thenReturn(null);

        ResponseEntity<GeneralResponseDto<Object>> response = materialController.actualizarMaterial(1L, dto);

        assertEquals(400,response.getStatusCode().value());
        assertFalse(response.getBody().isExito());
    }

}