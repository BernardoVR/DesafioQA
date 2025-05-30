#language: pt

Funcionalidade: Consulta de Cliente

  Cenário: Consultar cliente existente pelo ID
    Dado que a API está funcionando
    Quando faço uma requisição GET para buscar o cliente pelo ID 1
    Então os dados do cliente são retornados corretamente

  Cenário: Consultar cliente inexistente pelo ID
    Dado que a API está funcionando
    Quando faço uma requisição GET para buscar o cliente inexistente pelo ID 9999
    Então não recebo dados de nenhum cliente

  Cenário: Consultar todos os clientes cadastrados
    Dado que a API está funcionando
    Quando faço uma requisição GET para listar todos os clientes
    Então a lista de clientes cadastrados é retornada

