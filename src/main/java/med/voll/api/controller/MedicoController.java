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
import med.voll.api.domain.dto.medico.MedicoDtoAtualizacao;
import med.voll.api.domain.dto.medico.MedicoDtoCadastro;
import med.voll.api.domain.dto.medico.MedicoDtoDetalhamento;
import med.voll.api.domain.dto.medico.MedicoDtoListagem;
import med.voll.api.domain.modelo.Medico;
import med.voll.api.domain.repository.MedicoRepository;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid MedicoDtoCadastro dto, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dto);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicoDtoDetalhamento(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoDtoListagem>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable) {
        var page = repository.findAllByAtivoTrue(pageable).map(MedicoDtoListagem::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoDtoDetalhamento(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid MedicoDtoAtualizacao dto) {
        var medico = repository.getReferenceById(dto.id());
        medico.atualizarMedico(dto);
        return ResponseEntity.ok(new MedicoDtoDetalhamento(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluirMedico();
        return ResponseEntity.noContent().build();
    }
}