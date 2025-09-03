package co.com.prueba.tecnica.sysman.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ciudad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ciudad {

    private Long codigo;
    private String nombre;
    private String departamento;

}
