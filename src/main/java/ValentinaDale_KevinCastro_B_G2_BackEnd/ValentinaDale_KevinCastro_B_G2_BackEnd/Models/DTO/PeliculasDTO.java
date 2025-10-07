package ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString @EqualsAndHashCode
public class PeliculasDTO {

    private Long PeliculaId ;

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @NotBlank(message = "El director es obligatorio")
    private String director;

    @NotBlank(message = "El genero es obligatorio")
    private String genero;

    @NotNull(message = "El año de estreno es obligatorio")
    private Long anoEstreno;

    @NotNull(message = "La duración es obligatoria")
    private Long duracion;

    private Date fechaCreacion;
}
