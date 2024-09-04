package med.voll.api.domain.dto.consulta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record ConsultaDtoAgendamento(
    
@JsonAlias({"id_medico", "medico_id"})
    Long idMedico,

    @NotNull
    @JsonAlias({"id_paciente", "paciente_id"})
    Long idPaciente,

    @NotNull @Future
    @JsonAlias({"data_da_consulta", "data_consulta"})
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime data) {
    
}