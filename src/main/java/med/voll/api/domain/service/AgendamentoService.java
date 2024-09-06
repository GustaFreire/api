package med.voll.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.dto.consulta.ConsultaDtoAgendamento;
import med.voll.api.domain.modelo.Consulta;
import med.voll.api.domain.modelo.Medico;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.domain.validation.AgendamentoValidator;
import med.voll.api.infra.exception.ValidacaoException;

@Service
public class AgendamentoService {
 
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<AgendamentoValidator> validadores;

    public Consulta agendarConsulta(ConsultaDtoAgendamento dto) {
        if (!pacienteRepository.existsById(dto.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if (dto.idMedico() != null && !medicoRepository.existsById(dto.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.forEach(validador -> validador.validate(dto));

        var paciente = pacienteRepository.getReferenceById(dto.idPaciente());
        var medico = getMedico(dto);

        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data.");
        }

        var consulta = new Consulta(null, medico, paciente, dto.data());

        consultaRepository.save(consulta);
        return consulta;
    }

    private Medico getMedico(ConsultaDtoAgendamento dto) {
        if (dto.idMedico() != null) {
            return medicoRepository.getReferenceById(dto.idMedico());
        }
        
        if (dto.Especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando o médico for nulo!");
        }

        return medicoRepository.getMedicoAleatorio(dto.Especialidade(), dto.data());
    }
}