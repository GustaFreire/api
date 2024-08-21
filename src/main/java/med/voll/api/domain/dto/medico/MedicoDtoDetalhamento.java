package med.voll.api.domain.dto.medico;

import med.voll.api.domain.enums.Especialidade;
import med.voll.api.domain.modelo.Endereco;
import med.voll.api.domain.modelo.Medico;

public record MedicoDtoDetalhamento(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {
 
    public MedicoDtoDetalhamento(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}