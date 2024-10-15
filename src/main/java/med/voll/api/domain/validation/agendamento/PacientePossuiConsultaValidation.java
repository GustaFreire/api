package med.voll.api.domain.validation.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.consulta.ConsultaDtoAgendamento;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class PacientePossuiConsultaValidation implements AgendamentoValidator {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validate(ConsultaDtoAgendamento dto) {
        var horarioInicio = dto.data().withHour(7);
        var horarioFim = dto.data().withHour(18);
        var pacientePossuiConsulta = repository.existsByPacienteIdAndDataBetween(dto.idPaciente(), horarioInicio, horarioFim);
        
        if (pacientePossuiConsulta) {
            throw new ValidacaoException("O Paciente já possui uma consulta nesse mesmo horário");
        }
    }
}