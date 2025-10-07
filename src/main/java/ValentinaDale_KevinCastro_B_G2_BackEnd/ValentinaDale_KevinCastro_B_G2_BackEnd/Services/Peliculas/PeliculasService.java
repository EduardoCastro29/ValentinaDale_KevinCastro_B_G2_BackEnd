package ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Services.Peliculas;

import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Entities.Peliculas.PeliculasEntity;
import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Models.DTO.PeliculasDTO;
import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Repositories.Peliculas.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculasService {

    @Autowired
    private PeliculasRepository repo;


    public List<PeliculasDTO>getPeliculas(){
        List<PeliculasEntity> peliculas = repo.findAll();
        return peliculas.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private PeliculasDTO convertToDto(PeliculasEntity entity){
        PeliculasDTO peliculasDTO = new PeliculasDTO();
        peliculasDTO.setPeliculaId(entity.getIdPelicula());
        peliculasDTO.setTitulo(entity.getTitulo());
        peliculasDTO.setDirector(entity.getDirector());
        peliculasDTO.setGenero(entity.getGenero());
        peliculasDTO.setAnoEstreno(entity.getAnoEstreno());
        peliculasDTO.setDuracion(entity.getDuracion());
        peliculasDTO.setFechaCreacion(entity.getFechaCreacion());
        return peliculasDTO;
    }
}
