package ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Controllers.Peliculas;

import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Models.DTO.PeliculasDTO;
import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Services.Peliculas.PeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculasController {

    @Autowired
    private PeliculasService access;

    @GetMapping("/mostrarPeliculas")
    public List<PeliculasDTO>getPeliculas(){return access.getPeliculas();}
}
