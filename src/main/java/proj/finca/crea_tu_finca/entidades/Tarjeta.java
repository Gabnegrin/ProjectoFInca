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
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String numero;
    private String nombre;
    private String fecha;
    private String nseguridad;
    
    public Tarjeta(String numero, String nombre, String fecha, String nseguridad) {
        this.numero = numero;
        this.nombre = nombre;
        this.fecha = fecha;
        this.nseguridad = nseguridad;
    
    }
}
