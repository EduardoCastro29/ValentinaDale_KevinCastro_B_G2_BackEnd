package ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Entities.Peliculas;

import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Entities.Premios.PremiosEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @ToString @EqualsAndHashCode
@Table(name = "PELICULAS")
public class PeliculasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_PELICULAS")
    @SequenceGenerator(name = "SEQ_PELICULAS",sequenceName = "SEQ_PELICULAS",allocationSize = 1)
    @Column(name = "ID_PELICULA")
    private Long idPelicula ;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "ANO_ESTRENO")
    private Long anoEstreno;

    @Column(name = "DURACION_MIN")
    private Long duracion;

    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;

    @OneToMany(mappedBy = "peliculas",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<PremiosEntity>premios = new ArrayList<>();


}
