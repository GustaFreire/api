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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.paciente.PacienteDtoAtualizacao;
import med.voll.api.domain.dto.paciente.PacienteDtoCadastro;
import med.voll.api.domain.dto.paciente.PacienteDtoDetalhamento;
import med.voll.api.domain.dto.paciente.PacienteDtoListagem;
import med.voll.api.domain.modelo.Paciente;
import med.voll.api.domain.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PacienteDtoCadastro dto, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dto);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteDtoDetalhamento(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteDtoListagem>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable) {
        var page = repository.findAllByAtivoTrue(pageable).map(PacienteDtoListagem::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteDtoDetalhamento(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid PacienteDtoAtualizacao dto) {
        var paciente = repository.getReferenceById(dto.id());
        paciente.atualizarPaciente(dto);
        return ResponseEntity.ok(new PacienteDtoDetalhamento(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluirPaciente();
        return ResponseEntity.noContent().build();
    }
}