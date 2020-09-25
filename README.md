# Java Web - Trabalho Final

#### Sistema de gerenciamento de pedidos utilizando Java Web

#### Importação do projeto
###### Via arquivo .zip
1. Acessar o NetBeans, ir no menu *File / Import Project / From ZIP...*

#### Dependências
###### JDK 1.8
Se possuir mais de uma versão instalada, setar a versão 1.8 como a JDK utilizada para o projeto.

##### Driver MySQL Connector Java
###### Versão utilizada 8.0.17

#### Banco de de Dados
###### MySQL 8.0.21 (MySQL Community Server - GPL)

###### Criação do banco de dados

1. Acessar o MySQL Workbench, ir no menu *File / Run SQL Script...*
2. Selecionar o script disponível neste repositório no caminho *resources/db/schema.sql*
3. Clicar em *Run*, pois a definição do conjunto de carateres é indicada no script

#### Run
##### No NetBeans
Executar a classe *Aplication.java* do package *com.empresa.application*
##### .jar executável
Após a fase de Build, este é um projeto Java Ant, executar o .jar executável gerado com caminho *dist/lpoo-trabalho-final.jar*
