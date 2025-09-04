package co.com.prueba.tecnica.sysman.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponseDto <T> {
    private String mensaje;
    private boolean exito;
    T response;
}
