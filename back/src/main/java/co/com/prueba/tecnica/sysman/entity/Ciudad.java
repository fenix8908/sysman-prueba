package co.com.prueba.tecnica.sysman.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ciudad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private Long codigo;
    private String nombre;
    @ManyToOne(optional = false)
    private Departamento departamento;

}
