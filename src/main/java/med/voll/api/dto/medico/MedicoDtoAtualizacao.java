package med.voll.api.dto.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.endereco.EnderecoDto;

public record MedicoDtoAtualizacao(
    
    @NotNull
    Long id, 
    String telefone, 
    String nome, 
    EnderecoDto endereco) {

}