# Excel ETL System

Este projeto simula um pipeline de ETL distribuído, utilizando Kafka e RabbitMQ para processamento assíncrono de dados oriundos de planilhas Excel.

## Conceito

A ideia é representar um fluxo real de ingestão, validação e despacho de dados em uma arquitetura desacoplada, utilizando mensagens como meio de comunicação entre micro-serviços.

## Fluxo

1. O serviço `excel-maniac` recebe um arquivo `.xlsx` via HTTP e publica os dados no tópico Kafka `raw-data-import`.
2. O serviço `client-validator` consome do tópico, valida cada entrada e decide:
   - Se válida, publica no tópico `validated-client`
   - Se inválida, envia para o tópico `client-validation-dlq`
3. O serviço `client-dispatcher` consome do tópico `validated-client` e envia os dados para a fila `client-persistence` no RabbitMQ, para posterior gravação ou uso.

## Tecnologias

- Java 17
- Spring Boot
- Apache Kafka (modo KRaft, sem Zookeeper)
- RabbitMQ
- Docker Compose

## Estrutura

Cada micro-serviço é uma aplicação isolada com seu próprio `pom.xml`, rodando de forma independente, mas orquestrados juntos via `docker-compose`.

## Objetivo

O projeto visa treinar e demonstrar conhecimento em system design distribuído, mensageria e boas práticas de desacoplamento entre etapas críticas de fluxo de dados.


