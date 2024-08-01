package med.voll.api.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.medico.MedicoDtoAtualizacao;
import med.voll.api.dto.medico.MedicoDtoCadastro;
import med.voll.api.enums.Especialidade;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(MedicoDtoCadastro dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.crm = dto.crm();
        this.ativo = true;
        this.especialidade = dto.especialidade();
        this.endereco = new Endereco(dto.endereco());
    }

    public void atualizarMedico(MedicoDtoAtualizacao dto) {
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

    public void excluirMedico() {
        this.ativo = false;
    }
}