# Oracle Next Education - Challenge Fórum Hub   

Aplicação para gerenciamento de tópicos de discussão, desenvolvida com Spring Boot e JPA/Hibernate, com foco em manipulação de dados, persistência em banco de dados e implementação de endpoints REST para CRUD de tópicos.

---

## 🚀 Demonstração

O usuário inicia a aplicação e tem acesso a endpoints para gerenciamento de tópicos:

* Listar todos os tópicos (`GET /topicos`)
* Consultar um tópico específico pelo ID (`GET /topicos/{id}`)
* Criar um novo tópico (`POST /topicos`)
* Atualizar um tópico existente (`PUT /topicos/{id}`)
* Excluir um tópico (`DELETE /topicos/{id}`)

Exemplo de retorno de um tópico em JSON:

```json
{
    "titulo": "Dúvida sobre Spring Boot",
    "mensagem": "Como faço para criar endpoints REST?",
    "dataCriacao": "2026-03-05T17:56:41",
    "status": "NAO_RESPONDIDO",
    "autor": "Paulo",
    "curso": "Spring Boot"
}
```

---

## 🧠 Objetivo do Projeto

* Colocar em prática conhecimentos de Spring Boot, JPA/Hibernate e persistência em banco de dados.
* Implementar endpoints REST para criação, leitura, atualização e exclusão de tópicos.
* Trabalhar com DTOs para receber e enviar dados da API.
* Desenvolver regras de negócio como verificação de ID para atualização e exclusão de tópicos.
* Esse desafio faz parte do curso Tech Foundation da Oracle Next Education (ONE).

---

## ⚙️ Funcionalidades

* ✔️ Cadastro de tópicos
* ✔️ Listagem de todos os tópicos
* ✔️ Consulta detalhada de tópico por ID
* ✔️ Atualização de tópicos existentes
* ✔️ Exclusão de tópicos
* ✔️ Persistência em banco PostgreSQL via JPA/Hibernate
* ✔️ Regras de negócio para validação de ID e verificação de duplicidade na criação

---

## 🛠️ Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA / Hibernate
* Maven

---

## 🔌 Como Executar o Projeto

1. Clone o repositório

```bash
git clone https://github.com/paulodiegosantos/Oracle-Next-Education---Challenge-ForumHub.git
```

2. Entre na pasta do projeto

```bash
cd Oracle-Next-Education---Challenge-ForumHub
```

3. Configure o banco de dados no application.properties:

```bash
spring.datasource.url=jdbc:mysql://localhost/forumhub
spring.datasource.username=[USUARIO]
spring.datasource.password=[SENHA]
spring.jpa.hibernate.ddl-auto=none
```

Observação: Caso outra pessoa abra o projeto e não tenha um banco chamado `forumhub`, ela precisará criar o banco manualmente ou alterar a URL para apontar para um banco existente. As tabelas e colunas serão criadas automaticamente pelo Hibernate.

4. Execute a aplicação

```bash
mvn spring-boot:run
```

---

## 📖 Aprendizados

Durante o desenvolvimento do projeto, foram aplicados e reforçados diversos conhecimentos:

* Configuração de Spring Boot e integração com mysql
* Mapeamento de entidades com JPA/Hibernate
* Criação de DTOs e conversão para entidades
* Implementação de CRUD completo via endpoints REST
* Validação de regras de negócio (verificação de ID, tratamento de duplicidade)
* Persistência e manipulação de dados relacionais
* Tratamento de exceções e respostas apropriadas para recursos inexistentes

---

## 👨‍💻 Autor

Paulo Diego Dos Santos

LinkedIn: [https://www.linkedin.com/in/paulodiegosantos/](https://www.linkedin.com/in/paulodiegosantos/)

GitHub: [https://github.com/paulodiegosantos](https://github.com/paulodiegosantos)