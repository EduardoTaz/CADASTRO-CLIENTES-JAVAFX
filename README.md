# Sistema de Cadastro de Usuários
  Este projeto Java é uma aplicação de console que permite a inserção de informações de usuários, como nome, e-mail, telefone, sexo e estado civil (casado ou não), e armazena esses dados em um banco de dados SQL.

# Tecnologias Utilizadas
  * Linguagem: Java
  * Banco de Dados: MySQL
  * Driver JDBC: MySQL Connector/J

# Estrutura do Banco de Dados
  1. Criar Banco de Dados
    * CREATE DATABASE usuario_db;
  2. Criar Tabela
    * USE javadb;

      CREATE TABLE usuarios (
          id INT AUTO_INCREMENT PRIMARY KEY,
          nome VARCHAR(100) NOT NULL,
          email VARCHAR(100) NOT NULL,
          telefone VARCHAR(15) NOT NULL,
          sexo ENUM('Masculino', 'Feminino', 'Outro') NOT NULL,
          casado BOOLEAN NOT NULL
      );
     
# Execução do Projeto
  1. Certifique-se de que o MySQL está em execução e o banco de dados foi criado conforme especificado.
  2. Compile e execute o projeto.

# 
Este projeto fornece uma base simples para cadastro de usuários em um banco de dados SQL usando Java. A partir daqui, é possível expandir a funcionalidade, como adicionar validações, interface gráfica ou operações adicionais (como listagem e remoção de usuários).
