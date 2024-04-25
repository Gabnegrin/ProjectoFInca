package proj.finca.crea_tu_finca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropietarioDTO2 {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String usuario;
    private int edad;
    private int calificacion;
    private boolean eliminado;   
}
