# Oracle Next Education - Challenge Fórum Hub

API REST para gerenciamento de tópicos de discussão, desenvolvida com **Spring Boot**, **Spring Security**, **JWT** e **JPA/Hibernate**, com foco em manipulação de dados, persistência em banco de dados e implementação de **controle de acesso seguro utilizando autenticação baseada em token**.

O projeto simula o backend de um fórum, permitindo que usuários autenticados criem, consultem, atualizem e excluam tópicos de discussão.

---

# 🚀 Demonstração

Após iniciar a aplicação, a API disponibiliza endpoints para gerenciamento de tópicos.

Antes de acessar os endpoints protegidos, é necessário realizar **autenticação via login** para obter um **token JWT**.

### 1️⃣ Gerar Token de Autenticação

Endpoint de login:

```
POST /login
```

Exemplo de requisição:

```json
{
  "username": "usuario",
  "password": "senha"
}
```

Resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

Esse token deve ser utilizado nas requisições seguintes.

Header de autenticação:

```
Authorization: Bearer SEU_TOKEN_AQUI
```

---

# 📌 Endpoints Disponíveis

### Listar todos os tópicos

```
GET /topicos
```

### Consultar tópico por ID

```
GET /topicos/{id}
```

### Criar novo tópico

```
POST /topicos
```

### Atualizar tópico

```
PUT /topicos/{id}
```

### Excluir tópico

```
DELETE /topicos/{id}
```

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

Todos os endpoints acima **exigem autenticação via token JWT**.

Caso o token não seja enviado ou seja inválido, a API retorna:

```
HTTP 403 - Forbidden
```

---

# 🧠 Objetivo do Projeto

Este projeto tem como objetivo aplicar conceitos fundamentais do desenvolvimento backend com **Spring Boot**, incluindo:

* Criação de APIs REST
* Persistência de dados com **JPA/Hibernate**
* Implementação de **CRUD completo**
* Uso de **DTOs para transferência de dados**
* Aplicação de **regras de negócio**
* Implementação de **autenticação segura com JWT**
* Configuração de **controle de acesso utilizando Spring Security**

Esse desafio faz parte do programa **Tech Foundation da Oracle Next Education (ONE)**.

---

# ⚙️ Funcionalidades

✔️ Cadastro de tópicos

✔️ Listagem de todos os tópicos

✔️ Consulta detalhada de tópico por ID

✔️ Atualização de tópicos existentes

✔️ Exclusão de tópicos

✔️ Autenticação de usuários via login

✔️ Geração de token **JWT**

✔️ Validação automática de token em cada requisição

✔️ Controle de acesso utilizando **Spring Security**

✔️ Bloqueio de endpoints sem autenticação

✔️ Persistência de dados em banco **MySQL** via **JPA/Hibernate**

✔️ Regras de negócio para validação de duplicidade de tópicos

---

# 🔐 Segurança da Aplicação

Para garantir a segurança da API, foi implementado **controle de acesso baseado em JWT (JSON Web Token)**.

O fluxo de autenticação funciona da seguinte forma:

1️⃣ O usuário envia **login e senha** para o endpoint `/login`.

2️⃣ O sistema valida as credenciais utilizando **Spring Security**.

3️⃣ Se as credenciais forem válidas, a aplicação gera um **token JWT assinado com algoritmo HMAC256**.

4️⃣ O token contém:

* usuário autenticado
* data de emissão
* data de expiração

5️⃣ Esse token deve ser enviado no header das próximas requisições:

```
Authorization: Bearer TOKEN
```

6️⃣ Um **Filter de segurança** intercepta todas as requisições e:

* verifica se o token existe
* valida sua assinatura
* extrai o usuário autenticado

7️⃣ Se o token for válido, o usuário é autenticado e a requisição é processada.

Caso contrário, o acesso é negado.

---

# 🛠️ Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Security
* JWT (Auth0 Java JWT)
* Spring Data JPA
* Hibernate
* MySQL
* Maven

---

# 📂 Estrutura da Segurança Implementada

A segurança da aplicação foi organizada em três componentes principais.

### SecurityConfigurations

Classe responsável por configurar o **Spring Security**, definindo:

* rotas públicas (`/login`)
* rotas protegidas
* política **stateless**
* registro do filtro de autenticação JWT

---

### TokenService

Classe responsável por:

* gerar o token JWT
* validar o token
* extrair o usuário autenticado

O token é assinado utilizando:

```
Algorithm.HMAC256
```

E utiliza as propriedades definidas no `application.properties`:

```
jwt.secret
jwt.expiration
```

---

### JwtAuthenticationFilter

Filtro responsável por:

* interceptar todas as requisições da API
* extrair o token do header
* validar o token
* autenticar o usuário no contexto do Spring Security

Esse filtro evita a repetição de código de validação nos controllers.

---

# 🔌 Como Executar o Projeto

## 1️⃣ Clonar o repositório

```bash
git clone https://github.com/paulodiegosantos/Oracle-Next-Education---Challenge-ForumHub.git
```

---

## 2️⃣ Entrar na pasta do projeto

```bash
cd Oracle-Next-Education---Challenge-ForumHub
```

---

## 3️⃣ Pré-requisitos para rodar o projeto

Antes de executar o projeto, é necessário ter instalado:

* Java 21
* Maven
* MySQL
* Git

Opcionalmente, recomenda-se utilizar:

* IntelliJ IDEA
* VS Code
* Insomnia ou Postman para testes da API

---

## 4️⃣ Criar banco de dados

Crie um banco chamado:

```
forumhub
```

Exemplo no MySQL:

```sql
CREATE DATABASE forumhub;
```

---

## 5️⃣ Configurar o arquivo `application.properties`

Configure as credenciais do banco:

```properties
spring.datasource.url=jdbc:mysql://localhost/forumhub
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Chave secreta do JWT
jwt.secret=MinhaSenhaSuperSecreta123!

# Tempo de expiração do token
jwt.expiration=86400000
```

Caso o banco **forumhub** não exista, será necessário criá-lo manualmente ou alterar a URL para um banco existente.

---

## 6️⃣ Usuário padrão para testes

Para facilitar os testes da aplicação, o projeto possui uma **criação automática de usuário de teste** dentro da classe `ForumhubApplication`.

Sempre que a aplicação iniciar, será verificado se existe um usuário chamado **admin**. Caso não exista, ele será criado automaticamente com uma senha criptografada.

Usuário padrão criado:

```
username: admin
password: 123456
```

Implementação utilizada:

```java
package br.com.challenge.forumhub;

import br.com.challenge.forumhub.model.User;
import br.com.challenge.forumhub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ForumhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumhubApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				User user = new User();
				user.setUsername("admin");
				user.setPassword(passwordEncoder.encode("123456"));
				userRepository.save(user);
				System.out.println("Usuário de teste criado: admin / 123456");
			}
		};
	}
}
```

Isso garante que qualquer pessoa que baixar o projeto consiga **testar a autenticação imediatamente**, sem precisar criar usuários manualmente no banco.

---

## 7️⃣ Executar a aplicação

Execute o projeto com Maven:

```bash
mvn spring-boot:run
```

A aplicação será iniciada em:

```
http://localhost:8080
```

---

# 🧪 Testando a API

Ferramentas recomendadas:

* Postman
* Insomnia

Fluxo de teste:

1️⃣ Fazer login no endpoint:

```
POST /login
```

2️⃣ Usar as credenciais de teste:

```
admin
123456
```

3️⃣ Copiar o token retornado

4️⃣ Enviar o token no header das requisições:

```
Authorization: Bearer TOKEN
```

5️⃣ Acessar os endpoints protegidos.

---

# 📖 Aprendizados

Durante o desenvolvimento deste projeto foram aplicados diversos conceitos importantes de backend:

* Configuração de APIs REST com Spring Boot
* Integração com banco de dados utilizando JPA/Hibernate
* Criação de DTOs para comunicação entre camadas
* Implementação de CRUD completo
* Aplicação de regras de negócio
* Implementação de autenticação com JWT
* Configuração de segurança com Spring Security
* Criação de filtros de autenticação
* Validação de tokens em requisições HTTP
* Controle de acesso a endpoints protegidos

---

# 👨‍💻 Autor

**Paulo Diego Dos Santos Lima**

LinkedIn:
[https://www.linkedin.com/in/paulodiegosantos/](https://www.linkedin.com/in/paulodiegosantos/)

GitHub:
[https://github.com/paulodiegosantos](https://github.com/paulodiegosantos)