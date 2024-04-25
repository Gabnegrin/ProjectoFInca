package proj.finca.crea_tu_finca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropiedadDTO2 {
    private Long id;
    private String nombre;
    private int visitas;
    private int calificacion;
    private int valordia;
    private boolean eliminado;
}
