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
public class Ppse extends Pago{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String correo;   
    
    public Ppse(String correo) {
       this.correo = correo;
    }
}