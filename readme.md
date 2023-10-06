# Sistema de cadastro de exemplo utilizando React e Java 

Sistema de cadastro utilizando React para front e Java com SpringBoot como exemplo, para backend.

O sistema permite listar, cadastrar, editar e remover o registro do banco de dados.

O Cadastro dispara um email para o cliente quando ele se cadastra, e permite upload de uma foto.

Modo de utilização

- Baixar este repositório
- Em uma aba do terminal, executar os seguintes comandos na sequência (considerando que você está na raiz do diretório):
  - cd ./server/FEBackEnd
  - mvn clean package
  - java -jar ./target/febackend-api.jar 
- Em outra aba do terminal, executar os seguintes comandos na sequência (também considerando que você está na raiz do diretório) 
  - cd ./client
  - npm install
  - npm start

O site a ser acessado está em http://localhost:3000/
