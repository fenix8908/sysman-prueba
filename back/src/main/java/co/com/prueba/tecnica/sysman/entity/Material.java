package co.com.prueba.tecnica.sysman.entity;

import co.com.prueba.tecnica.sysman.enums.EstadoMaterial;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "material")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;
    private String descripcion;
    @NotBlank
    private String tipo;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal precio;

    @NotNull
    private LocalDate fechaCompra;
    private LocalDate fechaVenta;

    @Enumerated(EnumType.STRING)
    @NotNull private EstadoMaterial estado;

    @ManyToOne(optional = false)
    private Ciudad ciudad;

}
