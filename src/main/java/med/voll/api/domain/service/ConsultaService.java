package med.voll.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.consulta.ConsultaDtoAgendamento;
import med.voll.api.domain.dto.consulta.ConsultaDtoCancelamento;
import med.voll.api.domain.modelo.Consulta;
import med.voll.api.domain.modelo.Medico;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.domain.validation.agendamento.AgendamentoValidator;
import med.voll.api.domain.validation.cancelamento.CancelamentoValidator;
import med.voll.api.infra.exception.ValidacaoException;

@Service
public class ConsultaService {
 
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<AgendamentoValidator> validadoresAgendamento;

    @Autowired
    private List<CancelamentoValidator> validadoresCancelamento;

    public Consulta agendarConsulta(ConsultaDtoAgendamento dto) {
        if (!pacienteRepository.existsById(dto.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if (dto.idMedico() != null && !medicoRepository.existsById(dto.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadoresAgendamento.forEach(validador -> validador.validate(dto));
        var paciente = pacienteRepository.getReferenceById(dto.idPaciente());
        var medico = getMedico(dto);

        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data.");
        }

        var consulta = new Consulta(null, medico, paciente, dto.data(), null);
        consultaRepository.save(consulta);
        return consulta;
    }

    public void cancelarConsulta(@Valid ConsultaDtoCancelamento dto) {
        if (!consultaRepository.existsById(dto.idConsulta())) {
            throw new ValidacaoException("Id da consulta informada não existe!");
        }

        validadoresCancelamento.forEach(validador -> validador.validate(dto));
        var consulta = consultaRepository.getReferenceById(dto.idConsulta());
        consulta.cancelar(dto.motivoCancelamento());
    }

    private Medico getMedico(ConsultaDtoAgendamento dto) {
        if (dto.idMedico() != null) {
            return medicoRepository.getReferenceById(dto.idMedico());
        }
        
        if (dto.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando o médico for nulo!");
        }

        return medicoRepository.getMedicoAleatorio(dto.especialidade(), dto.data());
    }
}