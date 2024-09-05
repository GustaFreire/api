package med.voll.api.domain.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.consulta.ConsultaDtoAgendamento;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class PacienteInativoValidation implements AgendamentoValidator {

    @Autowired
    private PacienteRepository repository;

    @Override
    public void validate(ConsultaDtoAgendamento dto) {
        var pacienteEhAtivo = repository.findAtivoById(dto.idPaciente());
        
        if (!pacienteEhAtivo) {
            throw new ValidacaoException("O Paciente escolhido se encontra inativo no sistema");
        }
    }
}