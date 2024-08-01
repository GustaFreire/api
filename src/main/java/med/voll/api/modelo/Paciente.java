package med.voll.api.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.paciente.PacienteDtoAtualizacao;
import med.voll.api.dto.paciente.PacienteDtoCadastro;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Paciente(PacienteDtoCadastro dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.cpf = dto.cpf();
        this.ativo = true;
        this.endereco = new Endereco(dto.endereco());
    }

    public void atualizarPaciente(PacienteDtoAtualizacao dto) {
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }

        if (dto.telefone() != null) {
            this.nome = dto.telefone();
        }

        if (dto.endereco() != null) {
            this.endereco.atualizarEndereco(dto.endereco());
        }
    }

    public void excluirPaciente() {
        this.ativo = false;
    }
}