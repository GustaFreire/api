package med.voll.api.domain.validation.cancelamento;

import med.voll.api.domain.dto.consulta.ConsultaDtoCancelamento;

public interface CancelamentoValidator {

    void validate(ConsultaDtoCancelamento dto);
}