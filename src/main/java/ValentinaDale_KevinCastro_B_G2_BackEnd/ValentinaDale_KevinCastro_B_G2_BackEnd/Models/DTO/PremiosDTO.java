package ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter@Setter@ToString@EqualsAndHashCode
public class PremiosDTO {

    private Long idPremio ;

    @NotBlank(message = "El nombre premio es obligatorio")
    private String nombrePremio;

    @NotBlank(message = "La categoria es obligatoria")
    private String categoria;

    @NotNull(message = "El año premio es obligatorio")
    private Long anoPremio;

    @NotBlank(message = "El resultado es obligatorio")
    private String resultado;

    private LocalDate fechaRegistro;

    private Long peliculaId;

    private String nombrePelicula;
}
