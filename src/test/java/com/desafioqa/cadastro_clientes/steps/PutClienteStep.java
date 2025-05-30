package com.desafioqa.cadastro_clientes.steps;

import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

public class PutClienteStep {

    private final TestRestTemplate client = new TestRestTemplate();
    private final String endpoint = "http://localhost:8080/api/clientes";
    private ResponseEntity<String> resultado;

    // Cenário 1 - Atualizar cliente com sucesso
    @Quando("faço uma requisição PUT para atualizar o cliente com ID {int}")
    public void atualizarClienteValido(int id) {
        String jsonPayload = """
                {
                    "cpf": "98765432100",
                    "nome": "Carlos Atualizado",
                    "dataNascimento": "1992-03-10",
                    "telefone": "11999998888",
                    "endereco": "Avenida Brasil, 1500, Belo Horizonte - MG"
                }
                """;

        enviarPut(id, jsonPayload);
    }

    @Entao("os dados do cliente são atualizados com sucesso")
    public void validarAtualizacaoComSucesso() {
        assertTrue(
                resultado.getStatusCode() == HttpStatus.OK ||
                        resultado.getStatusCode() == HttpStatus.NO_CONTENT,
                "Esperado 200 OK ou 204 NO_CONTENT, mas foi: " + resultado.getStatusCode()
        );
    }

    // Cenário 2 - Cliente inexistente
    @Quando("faço uma requisição PUT para atualizar o cliente inexistente com ID {int}")
    public void atualizarClienteInexistente(int id) {
        String jsonPayload = """
                {
                    "cpf": "00011122233",
                    "nome": "Usuário Inexistente",
                    "dataNascimento": "1995-06-20",
                    "telefone": "11888887777",
                    "endereco": "Rua Fictícia, 9999, Narnia - SP"
                }
                """;

        enviarPut(id, jsonPayload);
    }

    @Entao("não ira ser atualizado dados de nenhum cliente")
    public void validarNaoAtualizar() {
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode(),
                "Esperado 404 NOT_FOUND, mas foi: " + resultado.getStatusCode());
    }

    // Cenário 3 - Dados obrigatórios ausentes
    @Quando("faço uma requisição PUT com nome vazio para o cliente com ID {int}")
    public void atualizarClienteComNomeVazio(int id) {
        String jsonPayload = """
                {
                    
                    "dataNascimento": "1998-12-15",
                    "telefone": "11777776666",
                    "endereco": "Travessa do Norte, 321, Recife - PE"
                }
                """;

        enviarPut(id, jsonPayload);
    }

    @Entao("recebo uma mensagem de erro indicando que não é possível a pesquisa com dados ausentes")
    public void validarErroDeCampos() {
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resultado.getStatusCode());
    }

    // Metodo repetir put
    private void enviarPut(int id, String payload) {
        HttpHeaders cabecalhos = new HttpHeaders();
        cabecalhos.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> corpo = new HttpEntity<>(payload, cabecalhos);
        resultado = client.exchange(endpoint + "/" + id, HttpMethod.PUT, corpo, String.class);
    }
}
