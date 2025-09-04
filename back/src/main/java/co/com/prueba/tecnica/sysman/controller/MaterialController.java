package co.com.prueba.tecnica.sysman.controller;

import co.com.prueba.tecnica.sysman.dto.GeneralResponseDto;
import co.com.prueba.tecnica.sysman.service.MaterialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysman/materiales")
public class MaterialController {

    private final MaterialesService materialesService;

    @Autowired
    public MaterialController(MaterialesService materialesService) {
        this.materialesService = materialesService;
    }

    @GetMapping
    public ResponseEntity<GeneralResponseDto<Object>> obtenerMateriales() {
        try {
            return ResponseEntity.ok(new GeneralResponseDto<>(
                    "Materiales obtenidos exitosamente",
                    true,
                    materialesService.obtenerTodosLosMateriales()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GeneralResponseDto<>(
                    "Error al obtener los materiales: " + e.getMessage(),
                    false,
                    null
            ));
        }
    }
}
