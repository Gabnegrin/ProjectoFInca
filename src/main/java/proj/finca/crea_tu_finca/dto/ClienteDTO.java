package proj.finca.crea_tu_finca.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proj.finca.crea_tu_finca.entidades.Solicitud;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private long id;
    private String nombre;
    private String apellido;
    private String correo;
    private int edad;
    private int calificacion;
    private boolean eliminado;
    private List<Solicitud> solicitudes;  
}
