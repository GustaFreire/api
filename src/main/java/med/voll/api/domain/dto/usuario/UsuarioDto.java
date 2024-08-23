package med.voll.api.domain.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(

    @NotBlank
    String usuario, 
    
    @NotBlank
    String senha) {

}