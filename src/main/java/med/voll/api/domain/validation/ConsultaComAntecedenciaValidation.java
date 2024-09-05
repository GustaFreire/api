package med.voll.api.domain.validation;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.consulta.ConsultaDtoAgendamento;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class ConsultaComAntecedenciaValidation  implements AgendamentoValidator {
 
    @Override
    public void validate(ConsultaDtoAgendamento dto) {
        var dataConsulta = dto.data();
        var dataAtual = LocalDateTime.now();
        var diferenca = Duration.between(dataAtual, dataConsulta).toMinutes();

        if (diferenca < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}