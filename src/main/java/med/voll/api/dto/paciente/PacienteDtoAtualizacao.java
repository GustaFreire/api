package med.voll.api.dto.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.endereco.EnderecoDto;

public record PacienteDtoAtualizacao(
    
    @NotNull
    Long id, 
    String telefone, 
    String nome, 
    EnderecoDto endereco) {

}