package com.desafioqa.cadastro_clientes.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

public class PostClienteStep {

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private ResponseEntity<String> resposta;

    String url = "http://localhost:8080/api/clientes";

    // Step para todos cenários
    @Dado("que a API está funcionando")
    public void apiFuncionando() {
        ResponseEntity<String> healthCheck = restTemplate.getForEntity(
                "http://localhost:8080/health", String.class);

        assertEquals(HttpStatus.OK, healthCheck.getStatusCode());
        assertEquals("API is UP", healthCheck.getBody());
    }

    // Cenário 1 - Cadastrar cliente com sucesso
    @Quando("faço uma requisição POST para cadastrar um cliente válido")
    public void facoRequisicaoCadastro() {


        String payload = """
                {
                    "cpf": "12345678999",
                    "nome": "Bernardo Teste 11",
                    "dataNascimento": "1985-09-25",
                    "telefone": "11987654321",
                    "endereco": "Rua das Caxabas, 111, Rio de Janeiro - RJ"
                }
            """;

        enviarPost(payload);
    }

    @Entao("o cliente é cadastrado com sucesso")
    public void clienteCadastrado() {
        assertTrue(
                resposta.getStatusCode() == HttpStatus.OK ||
                         resposta.getStatusCode() == HttpStatus.CREATED,
                "Esperado 200 OK ou 201 CREATED, mas foi: " + resposta.getStatusCode()
        );
    }

    // Cenário 2 - Tentar cadastrar cliente com dados obrigatórios faltando
    @Quando("faço uma requisição POST com nome vazio")
    public void facoRequisicaoCadastroDadosFaltando() {
        String payload = """
                {
                    "dataNascimento": "1990-01-01",
                    "telefone": "11912345678",
                    "endereco": "Rua das Palmeiras, 222, São Paulo - SP"
                }
                """;

        enviarPost(payload);
    }

    @Entao("recebo uma mensagem de erro")
    public void validaErroDadosFaltando() {
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode(),
                "Esperado 400 BAD_REQUEST, mas foi: " + resposta.getStatusCode());
    }


    // Metodo evitar repetição do envio de post nos cenarios
    private void enviarPost(String payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(payload, headers);
        resposta = restTemplate.postForEntity(url, request, String.class);
    }

}
