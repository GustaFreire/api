package med.voll.api.domain.dto.consulta;

import java.time.LocalDateTime;

import med.voll.api.domain.modelo.Consulta;

public record ConsultaDtoDetalhamento(Long idConsulta, Long idMedico, Long idPaciente, LocalDateTime data) {

    public ConsultaDtoDetalhamento(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}