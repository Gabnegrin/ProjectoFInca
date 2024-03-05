package proj.finca.crea_tu_finca.entidades;

import java.time.LocalDate;

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
public class Solicitud{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Usuario n_cliente; 
    private Usuario n_propietario;
    private Propiedad n_propiedad;
    private int c_cliente; 
    private int c_propietario;
    private int c_propiedad;
    private int preciot;
    private LocalDate entrada;
    private LocalDate salida;
    private int estado;
    
    public Solicitud(Usuario n_cliente, Usuario n_propietario, Propiedad n_propiedad, 
    int c_cliente, int c_propietario, int c_propiedad, LocalDate entreda, LocalDate salida,
    int estado) {
       this.n_cliente = n_cliente;
       this.n_propiedad = n_propiedad;
       this.n_propietario = n_propietario;
       this.c_cliente = c_cliente;
       this.c_propiedad = c_propiedad;
       this.c_propietario = c_propietario;
       this.entrada = entreda;
       this.salida = salida;
       this.estado = estado;
    }
}