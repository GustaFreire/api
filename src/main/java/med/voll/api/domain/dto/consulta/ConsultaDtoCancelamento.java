package med.voll.api.domain.dto.consulta;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.enums.MotivoCancelamentoConsulta;

public record ConsultaDtoCancelamento(

    @NotNull
    Long idConsulta,

    @NotNull
    MotivoCancelamentoConsulta motivoCancelamento) {

}