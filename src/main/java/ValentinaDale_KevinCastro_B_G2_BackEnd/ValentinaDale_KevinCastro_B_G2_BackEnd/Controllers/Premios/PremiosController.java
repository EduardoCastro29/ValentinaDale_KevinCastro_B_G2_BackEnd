package ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Controllers.Premios;

import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Models.DTO.PeliculasDTO;
import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Models.DTO.PremiosDTO;
import ValentinaDale_KevinCastro_B_G2_BackEnd.ValentinaDale_KevinCastro_B_G2_BackEnd.Services.Premios.PremiosService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/premios")
public class PremiosController {

    @Autowired
    private PremiosService access;

    @GetMapping("/mostrarPremios")
    public List<PremiosDTO> getPeliculas(){return access.getPremios();}

    @PostMapping("/ingresarPremio")
    public ResponseEntity<Map<String,Object>>registerPremio(@Valid @RequestBody PremiosDTO premiosDTO, HttpServletRequest request){
        try {
            PremiosDTO reply = access.insertPremio(premiosDTO);
            if (reply == null){
                return ResponseEntity.badRequest().body(Map.of("status:","Incersion incorrecta"
                        ,"errorType","Validation_Error",
                        "message","Datos invalidos"));
            }else {
                return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Status:","Succes","data",reply));
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status:","error",
                    "message","Error al registrar",
                    "detail",e.getMessage()));
        }
    }


}
