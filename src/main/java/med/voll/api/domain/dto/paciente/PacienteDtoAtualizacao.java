package med.voll.api.domain.dto.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dto.endereco.EnderecoDto;

public record PacienteDtoAtualizacao(
    
    @NotNull
    Long id, 
    String telefone, 
    String nome, 
    EnderecoDto endereco) {

}