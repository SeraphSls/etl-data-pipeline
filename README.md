# Excel ETL Pipeline

Este projeto simula um pipeline de ETL distribuído para arquivos Excel, utilizando RabbitMQ como backbone de mensageria assíncrona entre micro-serviços. Cada etapa do fluxo representa uma transformação ou decisão crítica, desacoplada por mensagens.

## Conceito

A ideia é simular um fluxo real de ingestão, validação e despacho de dados de planilhas Excel, com desacoplamento entre responsabilidades, tolerância a falhas e capacidade de escala horizontal. A comunicação entre serviços ocorre exclusivamente via mensageria.

## Fluxo de dados

Excel (.xlsx) → excel-maniac → sheet-alchemist → data-herald → MongoDB

1. excel-maniac  
   Serviço responsável por receber o Excel via HTTP, extrair os dados e enviá-los para o próximo estágio via RabbitMQ (fila decorator).

2. sheet-alchemist  
   Realiza o enriquecimento e transformação dos dados recebidos. Adiciona campos, formatações e executa lógicas de negócio intermediárias.

3. data-herald  
   Roteia e despacha os dados finais para persistência, notificações ou outros destinos como MongoDB.

Cada serviço consome uma fila, processa a mensagem e publica em outra, formando uma cadeia desacoplada e resiliente.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring AMQP (RabbitMQ)
- Docker e Docker Compose
- RabbitMQ Management UI (porta 15672)
- Swagger (Springdoc OpenAPI) para testes REST

Cada micro-serviço é isolado, com seu próprio build.gradle, ponto de entrada e responsabilidades bem definidas.

## Objetivo

- Praticar system design distribuído
- Demonstrar uso real de mensageria com RabbitMQ
- Aplicar boas práticas de acoplamento fraco
- Simular falhas, DLQs e reprocessamentos
- Mostrar um exemplo realista de pipeline de dados assíncrono
