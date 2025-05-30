#language: pt

Funcionalidade: Atualização de Cliente

  Cenário: Atualizar cliente com sucesso
    Dado que a API está funcionando
    Quando faço uma requisição PUT para atualizar o cliente com ID 2
    Então os dados do cliente são atualizados com sucesso

  Cenário: Tentar atualizar cliente inexistente
    Dado que a API está funcionando
    Quando faço uma requisição PUT para atualizar o cliente inexistente com ID 9999
    Então não ira ser atualizado dados de nenhum cliente

  Cenário: Tentar atualizar cliente com dados obrigatórios faltando
    Dado que a API está funcionando
    Quando faço uma requisição PUT com nome vazio para o cliente com ID 2
    Então recebo uma mensagem de erro indicando que não é possível a pesquisa com dados ausentes
