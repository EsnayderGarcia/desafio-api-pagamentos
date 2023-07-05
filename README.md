# Desafio API de Pagamentos

Neste desafio foi desenvolvida uma API Rest que fornece alguns recursos necessários para o recebimento de pagamentos de débitos de pessoas físicas e jurídicas.

## Features
- Receber Pagamentos
- Atualizar o status de um pagamento, que pode ser PENDENTE, PROCESSADO COM SUCESSO ou PROCESSADO COM FALHA
- Remover um pagamento, desde que este ainda esteja como PENDENTE na base de dados
- Filtragem de um pagamento pelo seu código do débito
- Filtragem de pagamentos pelo CPF/CNPJ e/ou status do pagamento 

## Tecnologias utilizadas
- Java 17
- Spring Boot 2.7.13
- Banco de Dados H2
- Swagger 3.0.0

Após iniciar o projeto localmente, acesse a interface do swagger para melhor visualização da estrutura de endpoinst do projeto: <a href="http://localhost:8080/swagger-ui/index.html">SWAGGER 🚀</a>




