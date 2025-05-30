package com.desafioqa.cadastro_clientes.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

public class GetClienteStep {

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private ResponseEntity<String> response;


    // Cenário 1 - Consultar cliente existente pelo ID
    @Quando("faço uma requisição GET para buscar o cliente pelo ID {int}")
    public void buscarClientePorId(int id) {
        String url = "http://localhost:8080/api/clientes/" + id;
        response = restTemplate.getForEntity(url, String.class);
    }

    @Entao("os dados do cliente são retornados corretamente")
    public void clienteEncontrado() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // Cenário 2 - Consultar cliente inexistente pelo ID
    @Quando("faço uma requisição GET para buscar o cliente inexistente pelo ID {int}")
    public void buscarClienteInexistentePorId(int id) {
        String url = "http://localhost:8080/api/clientes/" + id;
        response = restTemplate.getForEntity(url, String.class);
    }

    @Entao("não recebo dados de nenhum cliente")
    public void clienteNaoEncontrado() {
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Cenário 3 - Consultar todos os clientes cadastrados
    @Quando("faço uma requisição GET para listar todos os clientes")
    public void listarTodosClientes() {
        String url = "http://localhost:8080/api/clientes";
        response = restTemplate.getForEntity(url, String.class);
    }

    @Entao("a lista de clientes cadastrados é retornada")
    public void listaClientesRetornada() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
