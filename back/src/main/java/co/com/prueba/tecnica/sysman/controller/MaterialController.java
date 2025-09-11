package co.com.prueba.tecnica.sysman.controller;

import co.com.prueba.tecnica.sysman.dto.GeneralResponseDto;
import co.com.prueba.tecnica.sysman.dto.MaterialDTO;
import co.com.prueba.tecnica.sysman.dto.MaterialesDto;
import co.com.prueba.tecnica.sysman.entity.Material;
import co.com.prueba.tecnica.sysman.service.MaterialesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sysman/materiales")
@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping("/filtrar/{tipo}/{desde}")
    public ResponseEntity<GeneralResponseDto<Object>> obtenerMaterialesPorTipoYFecha(@PathVariable("tipo") String tipoMaterial,
                                                                                     @PathVariable("desde") String fechaDesde) {
        try {
            return ResponseEntity.ok(new GeneralResponseDto<>(
                    "Materiales obtenidos exitosamente",
                    true,
                    materialesService.obtenerMaterialesPorTipoYFecha(tipoMaterial, fechaDesde)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GeneralResponseDto<>(
                    "Error al obtener los materiales: " + e.getMessage(),
                    false,
                    null
            ));
        }
    }
    @GetMapping("/ciudad/{ciudadId}")
    public ResponseEntity<GeneralResponseDto<Object>> obtenerMaterialesPorCiudad(@PathVariable("ciudadId") Long ciudadId) {
        try {
            return ResponseEntity.ok(new GeneralResponseDto<>(
                    "Materiales obtenidos exitosamente",
                    true,
                    materialesService.obtenerMaterialesPorCiudad(ciudadId)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GeneralResponseDto<>(
                    "Error al obtener los materiales: " + e.getMessage(),
                    false,
                    null
            ));
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<GeneralResponseDto<Object>> guardarMaterial(@RequestBody @Valid MaterialesDto dto) {
        Material materialGuardado = materialesService.guardarMaterial(dto);
        if( materialGuardado != null ) {
            return ResponseEntity.ok(new GeneralResponseDto<>(
                    "Material guardado exitosamente",
                    true,
                    materialGuardado
            ));
        }else{
            return ResponseEntity.badRequest().body(new GeneralResponseDto<>(
                    "Error al guardar el material",
                    false,
                    null
            ));
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<GeneralResponseDto<Object>> actualizarMaterial( @PathVariable("id") long id ,
                                                                          @Valid @RequestBody MaterialesDto dto) {
        Material materialActualizado = materialesService.actualizarMaterial(id,dto);
        if( materialActualizado != null ) {
            return ResponseEntity.ok(new GeneralResponseDto<>(
                    "Material actualizado exitosamente",
                    true,
                    materialActualizado
            ));
        }else{
            return ResponseEntity.badRequest().body(new GeneralResponseDto<>(
                    "Error al actualizar el material",
                    false,
                    null
            ));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponseDto<Object>> obtenerMaterialPorId(@PathVariable("id") Long id) {
        try {
            Material material = materialesService.obtenerMaterialPorId(id);
            return ResponseEntity.ok(new GeneralResponseDto<>(
                    "Material obtenido exitosamente",
                    true,
                    material
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GeneralResponseDto<>(
                    "Error al obtener el material: " + e.getMessage(),
                    false,
                    null
            ));
        }
    }

}
