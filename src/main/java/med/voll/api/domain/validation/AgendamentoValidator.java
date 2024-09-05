package med.voll.api.domain.validation;

import med.voll.api.domain.dto.consulta.ConsultaDtoAgendamento;

public interface AgendamentoValidator {

    void validate(ConsultaDtoAgendamento dto);
}