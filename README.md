# Projeto de Arquitetura Pub/Sub com RabbitMQ e Banco de Dados PostgreSQL

Este projeto implementa uma arquitetura de publicação/assinatura (Pub/Sub) utilizando o RabbitMQ. A arquitetura é composta por 4 microserviços, divididos em 2 pares para redundância, além de um serviço adicional que atua como um controlador para publicar itens na fila do RabbitMQ. Esses itens, do tipo "Product", são consumidos pelos serviços e salvos em um banco de dados PostgreSQL.

## Componentes do Projeto

1. **Microserviço Consumer-Price (Par 1):**
   - Responsável por consumir mensagens na fila Price.

2. **Microserviço Consumer-Price-Second (Par 1):**
   - Igual ao primeiro micro-serviço, aumentando a resiliência do software.

3. **Microserviço Consumer-Stock (Par 2):**
   - Responsável por consumir mensagens na fila Stock.

4. **Microserviço D (Par 2):**
   - Igual ao primeiro micro-serviço do par 2, aumentando a resiliência do software.

5. **Controlador (Publisher):**
   - Publica itens Mensagens nas duas filas do RabbiqMQ, Price e Stock para serem consumidos pelos microserviços.

## Tecnologias Utilizadas

- **RabbitMQ:**
  - Utilizado como o middleware de mensagens para facilitar a comunicação assíncrona entre os microserviços.

- **PostgreSQL:**
  - Banco de dados utilizado para armazenar os itens "Product" consumidos pelos microserviços.

- **Docker:**
  - Arquivos Docker fornecidos para facilitar a criação e execução dos ambientes RabbitMQ e PostgreSQL.

- **JUnit e Mockito:**
  - Utilizados para realizar testes unitários nos microserviços.


