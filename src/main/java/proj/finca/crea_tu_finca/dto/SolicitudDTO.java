package proj.finca.crea_tu_finca.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proj.finca.crea_tu_finca.entidades.Cliente;
import proj.finca.crea_tu_finca.entidades.Propiedad;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDTO {
    private Long id;
    private int c_cliente; 
    private int c_propietario;
    private int c_propiedad;
    private int preciot;
    private LocalDate entrada;
    private LocalDate salida;
    private int estado;
    private boolean eliminado;
    private Cliente cliente;
    private Propiedad propiedad2;
}
