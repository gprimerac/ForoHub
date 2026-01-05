package ForoHub.Challenge.Controlador;

import ForoHub.Challenge.Repositorio.DatosAutenticacionUsuario;
import ForoHub.Challenge.Repositorio.DatosJWTToken; // Importamos tu nuevo record
import ForoHub.Challenge.Repositorio.Usuario; // Importamos tu entidad Usuario
import ForoHub.Challenge.infraestructura.TokenService; // Importamos el servicio del token
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos) {
             Authentication authToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.clave());

             var usuarioAutenticado = authenticationManager.authenticate(authToken);


        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        // 4. Devolvemos el token envuelto en el DTO que creaste
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
