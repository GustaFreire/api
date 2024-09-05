package med.voll.api.domain.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.consulta.ConsultaDtoAgendamento;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class MedicoInativoValidation implements AgendamentoValidator {

    @Autowired
    private MedicoRepository repository;

    @Override
    public void validate(ConsultaDtoAgendamento dto) {
        Long id = dto.idMedico();
        if (id == null) {
            return;
        }

        var medicoEhAtivo = repository.findAtivoById(id);
        if (!medicoEhAtivo) {
            throw new ValidacaoException("O Médico escolhido se encontra inativo no sistema");
        }
    }
}