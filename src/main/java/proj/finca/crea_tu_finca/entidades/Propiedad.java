package proj.finca.crea_tu_finca.entidades;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROPIEDADES")
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private int visitas;
    private int calificacion;
    private int valordia;
    private boolean eliminado;
    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Propietario propietario;
    @OneToMany(mappedBy = "propiedad2")
    private List<Solicitud> solicitudes;
}
