#language: pt

Funcionalidade: Cadastro de Cliente

  Cenário: Cadastrar cliente com sucesso
    Dado que a API está funcionando
    Quando faço uma requisição POST para cadastrar um cliente válido
    Então o cliente é cadastrado com sucesso


  Cenário: Tentar cadastrar cliente com dados obrigatórios faltando
    Dado que a API está funcionando
    Quando faço uma requisição POST com nome vazio
    Então recebo uma mensagem de erro


