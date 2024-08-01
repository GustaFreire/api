package med.voll.api.dto.paciente;

import med.voll.api.modelo.Paciente;

public record PacienteDtoListagem(Long id, String nome, String email, String cpf) {

    public PacienteDtoListagem(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}   