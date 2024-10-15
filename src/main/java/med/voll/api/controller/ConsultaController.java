package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.consulta.ConsultaDtoAgendamento;
import med.voll.api.domain.dto.consulta.ConsultaDtoCancelamento;
import med.voll.api.domain.dto.consulta.ConsultaDtoDetalhamento;
import med.voll.api.domain.dto.consulta.ConsultaDtoListagem;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.service.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> agendar(@RequestBody @Valid ConsultaDtoAgendamento dto, UriComponentsBuilder uriBuilder) {
        var consulta = consultaService.agendarConsulta(dto);
        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(uri).body(new ConsultaDtoDetalhamento(consulta));
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaDtoListagem>> listar(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pageable) {
        var page = repository.findAll(pageable).map(ConsultaDtoListagem::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var consulta = repository.getReferenceById(id);
        return ResponseEntity.ok(new ConsultaDtoDetalhamento(consulta));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> cancelar(@RequestBody @Valid ConsultaDtoCancelamento dto) {
        consultaService.cancelarConsulta(dto);
        return ResponseEntity.noContent().build();
    }
}