package co.com.prueba.tecnica.sysman.dto;

import co.com.prueba.tecnica.sysman.enums.EstadoMaterial;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MaterialDTO(Long id,
                          @NotBlank(message = "Este campo es obligario") String nombre,
                          String descripcion,
                          @NotBlank(message = "El tipo es obligatorio") String tipo,
                          @NotNull @DecimalMin("0.0") BigDecimal precio,
                          @NotNull @NotNull LocalDate fechaCompra,
                          @NotNull LocalDate fechaVenta,
                          @NotNull EstadoMaterial estado,
                          @NotNull Long ciudadId)
{}
