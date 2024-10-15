package med.voll.api.domain.dto.consulta;

import java.time.LocalDateTime;

import med.voll.api.domain.enums.Especialidade;
import med.voll.api.domain.modelo.Consulta;

public record ConsultaDtoListagem(
    Long idConsulta, 
    String nomeMedico, 
    Especialidade especialidadeMedico, 
    String crmMedico, 
    String nomePaciente, 
    LocalDateTime data) {

        public ConsultaDtoListagem(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getNome(), consulta.getMedico().getEspecialidade(), 
            consulta.getMedico().getCrm(), consulta.getPaciente().getNome(), consulta.getData());
    }
}