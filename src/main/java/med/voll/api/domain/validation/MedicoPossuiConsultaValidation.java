package med.voll.api.domain.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.consulta.ConsultaDtoAgendamento;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class MedicoPossuiConsultaValidation implements AgendamentoValidator {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validate(ConsultaDtoAgendamento dto) {
        var medicoPossuiConsulta = repository.existsByMedicoIdAndData(dto.idMedico(), dto.data());
        
        if (medicoPossuiConsulta) {
            throw new ValidacaoException("O Médico escolhido já possui uma consulta nesse mesmo horário");
        }
    }
}