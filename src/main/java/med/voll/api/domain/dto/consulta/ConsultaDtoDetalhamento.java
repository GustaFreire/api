package med.voll.api.domain.dto.consulta;

import java.time.LocalDateTime;

public record ConsultaDtoDetalhamento(Long idConsulta, Long idMedico, Long idPaciente, LocalDateTime data) {
    
}