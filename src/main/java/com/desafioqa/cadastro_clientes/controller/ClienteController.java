package com.desafioqa.cadastro_clientes.controller;

import com.desafioqa.cadastro_clientes.model.Cliente;
import com.desafioqa.cadastro_clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;



import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    // Buscar todos
    @GetMapping
    public List<Cliente> listarTodos() {
        return service.listarTodos();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar cliente
    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Cliente cliente) {
        Cliente salvo = service.salvar(cliente);
        return ResponseEntity.ok(salvo);
    }

    // Atualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return service.buscarPorId(id)
                .map(c -> {
                    c.setCpf(cliente.getCpf());
                    c.setNome(cliente.getNome());
                    c.setDataNascimento(cliente.getDataNascimento());
                    c.setTelefone(cliente.getTelefone());
                    c.setEndereco(cliente.getEndereco());
                    Cliente atualizado = service.salvar(c);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(c -> {
                    service.deletar(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
