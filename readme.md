# Teste Full Stack

Este teste foi desenvolvido utilizando Java 8 e Spring Boot 2.2

## Recursos utilizados

Jackson json para a leitura dos arquivos Json, JPA banco de dados Derby e Streams para o processamento dos dados.

Os dados estão sendo todos lidos e armazenados muito rapidamente e só na primeira vez que a aplicação é executada, após a primeira excecução os dados estarão no banco de dados da aplicação.

## Como rodar

Para rodar o projeto é necessário utilizar o Maven. Basta clonar o projeto em um diretorio de sua preferência e executar os comandos MVN Clean Install.


## Usando a apalicação
Para utilizar a solução proposta no teste acessar o seguinte endpoint:
"transacoes/vender/{produto}/{numeroDelojistas}"
