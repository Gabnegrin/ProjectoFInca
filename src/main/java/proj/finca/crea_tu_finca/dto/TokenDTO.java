package proj.finca.crea_tu_finca.dto;

import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {
    private String token;
    private ClienteDTO2 usuario;
    
    public String getType(){
        return "Bearer ";
    }
    public Date getDate(){
        return Calendar.getInstance().getTime();
    }
}
