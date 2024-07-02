package med.voll.api.dto.endereco;

public record EnderecoDto(
    String logradouro,
    String bairro,
    String cep,
    String cidade,
    String complemento,
    String uf,
    String numero) {
    
}