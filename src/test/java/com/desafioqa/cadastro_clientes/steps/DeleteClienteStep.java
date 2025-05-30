package com.desafioqa.cadastro_clientes.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteClienteStep {

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private final String url = "http://localhost:8080/api/clientes";
    private ResponseEntity<String> resposta;
    private static int proximoId = 10; // começa do 10 e incrementa a cada execução



    @Quando("faço uma requisição DELETE para remover um cliente existente")
    public void removerClienteExistente() {
        // Payload para criação do cliente
        String payload = """
        {
            "cpf": "0000000031",
            "nome": "Cliente Delete",
            "dataNascimento": "1990-01-01",
            "telefone": "1190000000",
            "endereco": "Rua de Teste"
        }
        """;

        // Envia POST para criar o cliente
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        ResponseEntity<String> respostaCriacao = restTemplate.postForEntity(url, request, String.class);

        // Extrai o ID do cliente criado da resposta JSON
        int idParaDeletar;
        try {
            JSONObject json = new JSONObject(respostaCriacao.getBody());
            idParaDeletar = json.getInt("id");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao extrair o ID do cliente criado: " + e.getMessage());
        }

        // DELETE
        resposta = restTemplate.exchange(
                url + "/" + idParaDeletar,
                HttpMethod.DELETE,
                null,
                String.class
        );
    }

    @Entao("o cliente é removido com sucesso")
    public void clienteRemovidoComSucesso() {
        assertTrue(
                resposta.getStatusCode() == HttpStatus.OK ||
                        resposta.getStatusCode() == HttpStatus.NO_CONTENT,
                "Esperado 200 OK ou 204 NO_CONTENT, mas foi: " + resposta.getStatusCode()
        );
    }

    @Quando("faço uma requisição DELETE para remover o cliente com ID 9999")
    public void removerClienteInexistente() {
        resposta = restTemplate.exchange(
                url + "/9999",
                HttpMethod.DELETE,
                null,
                String.class
        );
    }

    @Entao("recebo uma resposta indicando que o cliente não foi encontrado")
    public void clienteNaoEncontrado() {
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode(),
                "Esperado 404 NOT_FOUND, mas foi: " + resposta.getStatusCode());
    }
}
