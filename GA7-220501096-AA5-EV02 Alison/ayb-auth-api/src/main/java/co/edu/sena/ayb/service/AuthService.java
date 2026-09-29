package co.edu.sena.ayb.service;

import co.edu.sena.ayb.dto.AuthRequest;
import co.edu.sena.ayb.model.Usuario;
import co.edu.sena.ayb.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /** @return false si el usuario ya existe. */
    public boolean registrar(AuthRequest request) {
        if (usuarioRepository.existsByUsuario(request.getUsuario())) {
            return false;
        }
        String hash = passwordEncoder.encode(request.getPassword());
        usuarioRepository.save(new Usuario(request.getUsuario(), hash));
        return true;
    }

    /** Mismo resultado para usuario inexistente o contraseña incorrecta. */
    public boolean autenticar(AuthRequest request) {
        return usuarioRepository.findByUsuario(request.getUsuario())
                .map(u -> passwordEncoder.matches(request.getPassword(), u.getPassword()))
                .orElse(false);
    }
}
