package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.consulta.ConsultaDtoAgendamento;
import med.voll.api.domain.dto.consulta.ConsultaDtoDetalhamento;
import med.voll.api.domain.dto.paciente.PacienteDtoDetalhamento;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.service.AgendamentoService;

@RestController
@RequestMapping("/consultas")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> agendarConsulta(@RequestBody @Valid ConsultaDtoAgendamento dto, UriComponentsBuilder uriBuilder) {
        var consulta = agendamentoService.agendarConsulta(dto);
        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(uri).body(new ConsultaDtoDetalhamento(consulta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var consulta = repository.getReferenceById(id);
        return ResponseEntity.ok(new ConsultaDtoDetalhamento(consulta));
    }
}