package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.token.JwtTokenDto;
import med.voll.api.domain.dto.usuario.UsuarioDto;
import med.voll.api.domain.modelo.Usuario;
import med.voll.api.domain.service.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid UsuarioDto dto) {
        try {
            var user = new UsernamePasswordAuthenticationToken(dto.usuario(), dto.senha());
            var authenticate = manager.authenticate(user);
            var token = tokenService.generateToken((Usuario) authenticate.getPrincipal());
            return ResponseEntity.ok(new JwtTokenDto(token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}