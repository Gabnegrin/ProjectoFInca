package proj.finca.crea_tu_finca.entidades;

import proj.finca.crea_tu_finca.entidades.Propiedad;

import java.util.ArrayList;

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
public class Propietario extends Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private ArrayList<Propiedad> propiedades;
     
    public Propietario(String nombre, String apellido, String correo, 
    int edad, int calificacion, ArrayList<Propiedad> propiedades) {
        super(nombre, apellido, correo, edad, calificacion);
        this.propiedades = propiedades;
    }
}
