package med.voll.api.dto.medico;

import med.voll.api.enums.Especialidade;
import med.voll.api.modelo.Medico;

public record MedicoDtoListagem(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public MedicoDtoListagem(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}   