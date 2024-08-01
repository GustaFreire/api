package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import med.voll.api.dto.paciente.PacienteDtoAtualizacao;
import med.voll.api.dto.paciente.PacienteDtoCadastro;
import med.voll.api.dto.paciente.PacienteDtoListagem;
import med.voll.api.modelo.Paciente;
import med.voll.api.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PacienteDtoCadastro dto) {
        repository.save(new Paciente(dto));
    }

    @GetMapping
    public Page<PacienteDtoListagem> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(PacienteDtoListagem::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid PacienteDtoAtualizacao dto) {
        var paciente = repository.getReferenceById(dto.id());
        paciente.atualizarPaciente(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluirPaciente();
    }
}