package med.voll.api.dto.medico;

import med.voll.api.dto.endereco.EnderecoDto;
import med.voll.api.enums.Especialidade;

public record MedicoDto(
    String nome, 
    String email,
    String crm,
    Especialidade especialidade,
    EnderecoDto endereco) {

}