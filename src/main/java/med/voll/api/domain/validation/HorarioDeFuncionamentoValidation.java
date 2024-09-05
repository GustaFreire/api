package med.voll.api.domain.validation;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.consulta.ConsultaDtoAgendamento;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class HorarioDeFuncionamentoValidation implements AgendamentoValidator {

    @Override
    public void validate(ConsultaDtoAgendamento dto) {
        var dataConsulta = dto.data();
        var dataEhDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horario = dataConsulta.getHour();
        var dataEhForaDoHorario = horario < 7 || horario > 18;
        
        if (dataEhDomingo || dataEhForaDoHorario) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}