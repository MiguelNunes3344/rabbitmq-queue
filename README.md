# Projeto de Arquitetura Pub/Sub com RabbitMQ e Banco de Dados PostgreSQL

Este projeto implementa uma arquitetura de publicação/assinatura (Pub/Sub) utilizando o RabbitMQ. A arquitetura é composta por 4 microserviços, divididos em 2 pares para redundância, além de um serviço adicional que atua como um controlador para publicar itens na fila do RabbitMQ. Esses itens, do tipo "Product", são consumidos pelos serviços e salvos em um banco de dados PostgreSQL.

## Componentes do Projeto

1. **Microserviço Consumer-Price (Par 1):**
   - Responsável por consumir mensagens na fila Price.

2. **Microserviço Consumer-Price-Second (Par 1):**
   - Igual ao primeiro micro-serviço, aumentando a resiliência do software.

3. **Microserviço Consumer-Stock (Par 2):**
   - Responsável por consumir mensagens na fila Stock.

4. **Microserviço Consumer-Stock-Second (Par 2):**
   - Igual ao primeiro micro-serviço do par 2, aumentando a resiliência do software.

5. **Stock (Publisher):**
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

# Executando o Projeto RabbitMQ Queue

Para executar o projeto, siga os passos abaixo:

1. **Clone o Repositório:**
   ```bash
   git clone https://github.com/MiguelNunes3344/rabbitmq-queue.git

2. **Acesse o diretório do Projeto:**
   ```bash
   cd rabbitmq-queue
3. **Execute o Docker Compose para Configuração do Ambiente:**
   ```bash
   docker-compose up -d

4. **Execute os Projetos Spring:**
   ```bash
   cd stock
   mvn spring-boot:run

   
   cd consumer-stock
   mvn spring-boot:run

   cd consumer-stock-second
   mvn spring-boot:run
   
   cd consumer-price
   mvn spring-boot:run
   
   cd consumer-price-second
   mvn spring-boot:run

5. **Acesso a aplicação:**
  Após a inicialização bem-sucedida, você pode acessar a aplicação em http://localhost:8080 ou no endereço especificado na saída do console.
6. **Endpoints Disponíveis:**

   http://localhost:8080/stock: Endpoint para enviar JSON com informações de estoque.
   ```json
   
     {
       "productCode": "10",
       "quantity": 10
      }
   
   ```
   http://localhost:8080/price: Endpoint para enviar JSON com informações de preço.

   ```json
   
     {
       "productCode":4,
       "price": 10
      }
   
   ```

   
