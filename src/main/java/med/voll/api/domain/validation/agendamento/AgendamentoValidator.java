package med.voll.api.domain.validation.agendamento;

import med.voll.api.domain.dto.consulta.ConsultaDtoAgendamento;

public interface AgendamentoValidator {

    void validate(ConsultaDtoAgendamento dto);
}