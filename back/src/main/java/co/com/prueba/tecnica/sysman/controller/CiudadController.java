package co.com.prueba.tecnica.sysman.controller;

import co.com.prueba.tecnica.sysman.service.CiudadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysman/ciudades")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Ciudades", description = "Operaciones relacionadas con las ciudades")
public class CiudadController {

    private final CiudadService ciudadService;

    @Autowired
    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las ciudades", description = "Obtiene una lista de todas las ciudades disponibles")
    @ApiResponse(responseCode = "200", description = "Ciudades obtenidas exitosamente")
    @ApiResponse(responseCode = "400", description = "Error al obtener las ciudades")
    public ResponseEntity<Object> obtenerCiudades() {
        try {
            return ResponseEntity.ok(ciudadService.obtenerTodasLasCiudades());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener las ciudades: " + e.getMessage());
        }
    }
}
