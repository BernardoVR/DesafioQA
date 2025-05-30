#language: pt

Funcionalidade: Remoção de Cliente

  Cenário: Remover cliente existente com sucesso
    Dado que a API está funcionando
    Quando faço uma requisição DELETE para remover um cliente existente
    Então o cliente é removido com sucesso

  Cenário: Tentar remover cliente inexistente
    Dado que a API está funcionando
    Quando faço uma requisição DELETE para remover o cliente com ID 9999
    Então recebo uma resposta indicando que o cliente não foi encontrado

