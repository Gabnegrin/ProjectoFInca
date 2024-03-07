package proj.finca.crea_tu_finca.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENTES")
public class Cliente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String apellido;
    private String correo;
    private int edad;
    private int calificacion;
    private String no_tarjeta;
    private String nombre_tarjeta;
    private String fecha;
    private String codseguridad;
    private boolean mostrar;
    @OneToMany(mappedBy = "cliente")
    private List<Solicitud> solicitudes;
    
 
      
}

