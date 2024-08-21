package med.voll.api.domain.dto.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dto.endereco.EnderecoDto;

public record MedicoDtoAtualizacao(
    
    @NotNull
    Long id, 
    String telefone, 
    String nome, 
    EnderecoDto endereco) {

}