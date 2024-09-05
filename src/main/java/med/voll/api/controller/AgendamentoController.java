package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.consulta.ConsultaDtoAgendamento;
import med.voll.api.domain.dto.consulta.ConsultaDtoDetalhamento;
import med.voll.api.domain.service.AgendamentoService;

@RestController
@RequestMapping("/consultas")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> agendarConsulta(@RequestBody @Valid ConsultaDtoAgendamento dto) {
        agendamentoService.agendarConsulta(dto);
        return ResponseEntity.ok(new ConsultaDtoDetalhamento(null, null, null, null));
    }
}