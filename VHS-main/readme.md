# VHS Manager

Sistema simples para gestão de fitas VHS e suas categorias, desenvolvido em Java com Spring Boot, Thymeleaf e MySQL.

## Funcionalidades

- Cadastro, edição e exclusão de fitas VHS
- Cadastro, edição e exclusão de categorias
- Associação de categoria à fita
- Controle de status da fita (Disponível, Emprestada, Indisponível)
- Autenticação de usuário

## Como acessar

Após rodar o projeto, acesse [http://localhost:8080/vhs](http://localhost:8080/vhs) no navegador.

**Usuário padrão para login:**

```
Usuário: admin
Senha: admin
```

## Mensagem de erro na tela de login

Se você tentar acessar o sistema com usuário ou senha incorretos, será redirecionado para a página de login com a URL:

```
/login?error
```

Quando isso acontecer, uma mensagem de erro será exibida informando que o login ou senha estão inválidos.  
A mensagem **não aparece** quando você acessa `/login` normalmente, apenas após uma tentativa de login inválida.

## Requisitos

- Java 21+
- MySQL
- Maven

## Configuração do banco

O banco de dados padrão é `locadora_vhs` (veja `src/main/resources/application.properties`).  
Altere o usuário e senha conforme sua instalação do MySQL.