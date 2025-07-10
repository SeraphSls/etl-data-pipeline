#  ETL data pipeline

Este projeto simula um pipeline de ETL distribuído, utilizando Kafka e RabbitMQ para processamento assíncrono de dados oriundos de planilhas Excel.

## Conceito

A ideia é representar um fluxo real de ingestão, validação e despacho de dados em uma arquitetura desacoplada, utilizando mensagens como meio de comunicação entre micro-serviços.

## Fluxo

1. O serviço `excel-maniac` recebe um arquivo `.xlsx` via HTTP e publica os dados no tópico Kafka `raw-data-import`.
4. o serviço `client-enrichment` consome do tópico `validated-client` e realiza devidos tratamentos de dados e publica no tópico `data-prepared`
3. O serviço `client-dispatcher` consome do tópico `data-prepared` e envia os dados para a fila `client-persistence` no RabbitMQ, para posterior gravação ou uso.

## Tecnologias

- Java 21
- Spring Boot
- RabbitMQ
- Docker Compose

## Estrutura

Cada micro-serviço é uma aplicação isolada com seu próprio `build.gradle`, rodando de forma independente, mas orquestrados juntos via `docker-compose`.



