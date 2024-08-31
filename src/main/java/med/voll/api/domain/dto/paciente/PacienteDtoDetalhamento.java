package med.voll.api.domain.dto.paciente;

import med.voll.api.domain.modelo.Endereco;
import med.voll.api.domain.modelo.Paciente;

public record PacienteDtoDetalhamento(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {
    
    public PacienteDtoDetalhamento(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}