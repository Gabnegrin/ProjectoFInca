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
    private long id;
    private long id_propiedad;
    private int cali_cliente; 
    private int cali_propietario;
    private int cali_propiedad;
    private int preciot;
    private LocalDate entrada;
    private LocalDate salida;
    private int estado;
    private boolean eliminado;
    private Cliente cliente;
    private Propiedad propiedad2;
}
