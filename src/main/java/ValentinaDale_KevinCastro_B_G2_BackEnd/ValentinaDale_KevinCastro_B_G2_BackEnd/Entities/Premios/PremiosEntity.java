package ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Entities.Premios;

import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Entities.Peliculas.PeliculasEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter @Setter @EqualsAndHashCode @ToString
@Table(name = "PREMIOS")
public class PremiosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_PREMIOS")
    @SequenceGenerator(name = "SEQ_PREMIOS",sequenceName = "SEQ_PREMIOS",allocationSize = 1)
    @Column(name = "ID_PREMIO")
    private Long idPremio ;

    @Column(name = "NOMBRE_PREMIO")
    private String nombrePremio;

    @Column(name = "CATEGORIA")
    private String categoria;

    @Column(name = "ANO_PREMIO")
    private Long anoPremio;

    @Column(name = "RESULTADO")
    private String resultado;

    @Column(name = "FECHA_REGISTRO")
    private Date fecha_Registro;

    @ManyToOne
    @JoinColumn(name = "idPelicula",referencedColumnName = "ID_PELICULA")
    private PeliculasEntity peliculas;


}
