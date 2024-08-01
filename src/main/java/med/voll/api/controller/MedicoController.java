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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dto.medico.MedicoDtoAtualizacao;
import med.voll.api.dto.medico.MedicoDtoCadastro;
import med.voll.api.dto.medico.MedicoDtoListagem;
import med.voll.api.modelo.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoDtoCadastro dto) {
        repository.save(new Medico(dto));
    }

    @GetMapping
    public Page<MedicoDtoListagem> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(MedicoDtoListagem::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid MedicoDtoAtualizacao dto) {
        var medico = repository.getReferenceById(dto.id());
        medico.atualizarMedico(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluirMedico();
    }
}