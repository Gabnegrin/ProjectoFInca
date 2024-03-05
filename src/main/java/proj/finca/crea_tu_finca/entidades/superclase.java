package proj.finca.crea_tu_finca.entidades;

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
public class superclase extends Pago{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Propiedad> propiedades;   
    private ArrayList<Solicitud> solicitudes;      
    
}