package proj.finca.crea_tu_finca.entidades;

import org.hibernate.annotations.SQLDelete;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private int visitas;
    private int calificacion;
    private int valordia;
    //private bit imagen;
    
    
    public Propiedad(String nombre, int visitas, int calificacion, int valordia) {
        this.nombre = nombre;
        this.visitas = visitas;
        this.calificacion = calificacion;
        this.valordia = valordia;
        //this.imagen =;
    }
}
