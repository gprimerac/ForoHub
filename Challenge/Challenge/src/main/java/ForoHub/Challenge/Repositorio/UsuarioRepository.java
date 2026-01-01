package ForoHub.Challenge.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

// IMPORTANTE: Asegúrate de que aquí diga <Usuario, Long> y NO <Object, Long>
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}