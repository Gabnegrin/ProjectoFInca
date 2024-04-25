package proj.finca.crea_tu_finca.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDTO2 {
    private Long id;
    private int c_cliente; 
    private int c_propietario;
    private int c_propiedad;
    private int preciot;
    private LocalDate entrada;
    private LocalDate salida;
    private int estado;
    private boolean eliminado;
}
