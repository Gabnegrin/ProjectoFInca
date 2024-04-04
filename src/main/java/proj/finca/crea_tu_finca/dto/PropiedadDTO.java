package proj.finca.crea_tu_finca.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proj.finca.crea_tu_finca.entidades.Propietario;
import proj.finca.crea_tu_finca.entidades.Solicitud;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropiedadDTO {
    private Long id;
    private String nombre;
    private int visitas;
    private int calificacion;
    private int valordia;
    private boolean eliminado;
    private Propietario propietario;
    private List<Solicitud> solicitudes;
}
