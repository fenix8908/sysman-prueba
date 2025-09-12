package co.com.prueba.tecnica.sysman.dto;

import co.com.prueba.tecnica.sysman.enums.EstadoMaterial;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidad que representa un material")
public class MaterialesDto {

    private Long id;

    @NotNull
    @NotBlank(message = "Este campo es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull
    @NotBlank(message = "El tipo es obligatorio")
    @Schema(description = "Tipo de material", example = "Electrónico, Mueble, Vehículo, etc.")
    private String tipo;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin("0.0")
    @Schema(description = "Precio del material", example = "1500.00")
    private BigDecimal precio;

    @NotNull(message = "La fecha de compra no puede ser nula")
    @Schema(description = "Fecha de compra del material", example = "2023-10-15")
    private LocalDate fechaCompra;

    @NotNull(message = "La fecha de venta no puede ser nula")
    @Schema(description = "Fecha de venta del material", example = "2024-05-20")
    private LocalDate fechaVenta;

    @NotNull
    @Schema(description = "Estado del material", example = "ACTIVO, DISPONIBLE, ASIGNADO")
    private EstadoMaterial estado;

    @NotNull
    @Schema(description = "ID de la ciudad asociada al material", example = "1")
    private Long ciudadId;
}
