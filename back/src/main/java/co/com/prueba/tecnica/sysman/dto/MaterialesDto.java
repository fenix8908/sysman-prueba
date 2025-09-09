package co.com.prueba.tecnica.sysman.dto;

import co.com.prueba.tecnica.sysman.enums.EstadoMaterial;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialesDto {

    private Long id;

    @NotNull
    @NotBlank(message = "Este campo es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull
    @NotBlank(message = "El tipo es obligatorio")
    private String tipo;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin("0.0")
    private BigDecimal precio;

    @NotNull(message = "La fecha de compra no puede ser nula")
    private LocalDate fechaCompra;

    @NotNull(message = "La fecha de venta no puede ser nula")
    private LocalDate fechaVenta;

    @NotNull
    private EstadoMaterial estado;

    @NotNull
    private Long ciudadId;
}
