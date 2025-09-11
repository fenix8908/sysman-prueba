package co.com.prueba.tecnica.sysman.controller;

import co.com.prueba.tecnica.sysman.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysman/ciudades")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CiudadController {

    private final CiudadService ciudadService;

    @Autowired
    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping
    public ResponseEntity<Object> obtenerCiudades() {
        try {
            return ResponseEntity.ok(ciudadService.obtenerTodasLasCiudades());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener las ciudades: " + e.getMessage());
        }
    }
}
