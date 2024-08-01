package med.voll.api.dto.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.endereco.EnderecoDto;

public record PacienteDtoCadastro(

    @NotBlank
    String nome,

    @NotBlank @Email
    String email,

    @NotBlank
    String telefone,

    @NotBlank
    String cpf,

    @NotNull @Valid
    EnderecoDto endereco) {

}