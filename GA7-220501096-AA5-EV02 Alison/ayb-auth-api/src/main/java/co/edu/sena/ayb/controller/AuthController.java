package co.edu.sena.ayb.controller;

import co.edu.sena.ayb.dto.ApiResponse;
import co.edu.sena.ayb.dto.AuthRequest;
import co.edu.sena.ayb.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registro")
    public ResponseEntity<ApiResponse> registro(@Valid @RequestBody AuthRequest request) {
        if (!authService.registrar(request)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse(false, "El usuario ya está registrado"));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Registro realizado correctamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody AuthRequest request) {
        if (authService.autenticar(request)) {
            return ResponseEntity.ok(new ApiResponse(true, "Autenticación satisfactoria"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse(false, "Error en la autenticación"));
    }
}
