package med.voll.api.domain.validation.cancelamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.consulta.ConsultaDtoCancelamento;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class CancelamentoComAntecedenciaValidation implements CancelamentoValidator {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validate(ConsultaDtoCancelamento dto) {
        var consulta = repository.getReferenceById(dto.idConsulta());
        var dataConsulta = consulta.getData();
        var dataAtual = LocalDateTime.now();
        var diferenca = Duration.between(dataAtual, dataConsulta).toHours();

        if (diferenca < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h");
        }
    }
}