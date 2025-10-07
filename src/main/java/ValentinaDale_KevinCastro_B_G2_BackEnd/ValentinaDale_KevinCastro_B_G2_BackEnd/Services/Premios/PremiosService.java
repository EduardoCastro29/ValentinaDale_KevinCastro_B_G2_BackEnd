package ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Services.Premios;
import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Entities.Peliculas.PeliculasEntity;
import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Entities.Premios.PremiosEntity;
import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Exceptions.ExceptionUnregisteredPremio;
import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Models.DTO.PremiosDTO;
import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Repositories.Peliculas.PeliculasRepository;
import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Repositories.Premios.PremiosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PremiosService {

    @Autowired
    private PremiosRepository repo;

    @Autowired
    private PeliculasRepository repoPeliculas;

    // Metodo GET

    public List<PremiosDTO> getPremios(){
        List<PremiosEntity> premios = repo.findAll();
        return premios.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Metodo POST

    public PremiosDTO insertPremio(PremiosDTO premiosDTO){

        if (premiosDTO == null){
            throw new  IllegalArgumentException("No puede haber ningun campo vacio");
        }

        try{
            // Primero se pasan los datos DTO del frontEnd a datos Entity para guardar en la base de datos
            PremiosEntity premiosConvert = convertToEntity(premiosDTO);
            // Ahora que ya estan en entity se guardan en la base de datos
            PremiosEntity premiosSave = repo.save(premiosConvert);
            // Luego que se guardan se convierten en DTO para mostrar en el frontEd
            return convertToDto(premiosSave);

        }catch (Exception e){
            log.error("No se pudo ingresar el premio");
            throw new ExceptionUnregisteredPremio("Premio no registrado");
        }
    }

    // Metodo PUT

    public PremiosDTO updatePremio(Long id,PremiosDTO premiosDTO){
        PremiosEntity premiosEntity = repo.findById(id).orElseThrow(()-> new IllegalArgumentException("No se encontro ID"));

        if (premiosDTO.getNombrePremio()!=null){
            premiosEntity.setNombrePremio(premiosDTO.getNombrePremio());
        }
        premiosEntity.setCategoria(premiosDTO.getCategoria());
        premiosEntity.setAnoPremio(premiosDTO.getAnoPremio());
        premiosEntity.setResultado(premiosDTO.getResultado());
        premiosEntity.setFecha_Registro(premiosDTO.getFechaRegistro());

        // Obtener el Id
        // Obtener el ID
        if (premiosEntity.getPeliculas()!= null){
            PeliculasEntity peliculas = repoPeliculas.findById(premiosDTO.getPeliculaId()).orElseThrow(()-> new IllegalArgumentException("No se encontro el ID"));
            premiosEntity.setPeliculas(peliculas);
        }
        return convertToDto(premiosEntity);
    }

    // Metodo DELETE

    public Boolean deletePremios(Long id){
       PremiosEntity premios = repo.findById(id).orElse(null);

       try {
           if (premios!= null){
               repo.deleteById(id);
               return true;
           }else {
               System.out.println("No se encontro el ID");
               return false;
           }
       } catch (EmptyResultDataAccessException e){
           throw new EmptyResultDataAccessException("No se encontro los datos",1);
       }
    }

    private PremiosDTO convertToDto(PremiosEntity entity){
        PremiosDTO premiosDTO = new PremiosDTO();
        premiosDTO.setIdPremio(entity.getIdPremio());
        premiosDTO.setNombrePremio(entity.getNombrePremio());
        premiosDTO.setCategoria(entity.getCategoria());
        premiosDTO.setAnoPremio(entity.getAnoPremio());
        premiosDTO.setResultado(entity.getResultado());
        premiosDTO.setFechaRegistro(entity.getFecha_Registro());
        // Obtener el ID
        if (entity.getIdPremio()!= null){
            premiosDTO.setNombrePelicula(entity.getPeliculas().getTitulo());
            premiosDTO.setPeliculaId(entity.getPeliculas().getIdPelicula());
        }else {
            premiosDTO.setNombrePremio("Pelicula no asignada");
            premiosDTO.setPeliculaId(null);
        }
        return premiosDTO;
    }

    private PremiosEntity convertToEntity(PremiosDTO premiosDTO){
        System.out.println("entrando a la conversion del entity");

        PremiosEntity premiosEntity= new PremiosEntity();
        premiosEntity.setNombrePremio(premiosDTO.getNombrePremio());
        premiosEntity.setCategoria(premiosDTO.getCategoria());
        premiosEntity.setAnoPremio(premiosDTO.getAnoPremio());
        premiosEntity.setResultado(premiosDTO.getResultado());
        premiosEntity.setFecha_Registro(premiosDTO.getFechaRegistro());
        // Obtener el ID
        if (premiosDTO.getPeliculaId()!= null){
            PeliculasEntity peliculas = repoPeliculas.findById(premiosDTO.getPeliculaId()).orElseThrow(()-> new IllegalArgumentException("No se encontro el ID"));
            premiosEntity.setPeliculas(peliculas);
        }
        System.out.println("Saliendo de la conversion del entity");
        return premiosEntity;
    }
}
