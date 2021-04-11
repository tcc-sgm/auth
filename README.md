Este é o repositorio criada para a solução de um microservice de autenticação e autorização utilizando as tecnologias JAVA, Spring Security e JWT, que possa ser utilizada por qualquer aplicação REST.

Membros:
 - [Jonathan Cabral](mailto:dev.jonathancabral@gmail.com)
 - [André Graciano](mailto:dev.jonathancabral@gmail.com)

## Build sem Docker
 
    gradlew clean build

## build com docker

    docker build -t sgm/auth -f .\Dockerfile .

    docker run -d -p 9090:9090 sgm/auth

## Documentação da API SWAGGER
	http://localhost:9090/swagger-ui.html#/

## Autorização e Autenticação

### Login

`OPEN`

```http
POST /users/signin
```
	curl -X POST "http://localhost:9090/users/signin?password=1234&username=1234" -H "accept: */*"
			
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `username` | `string` | **Required**. User username |
| `password` | `string` | **Required**. password |

### Cadastrar

`OPEN`

```http
POST /users/signup
```
	curl -X POST "http://localhost:9090/users/signup" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"username\": \"string\", \"email\": \"string\", \"password\": \"string\", \"roles\": [ \"ROLE_ADMIN\" ]}"
			
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `Name` | `string` | **Required**. Usar fullnome |
| `CPF` | `string` | **Required**. User CPF |
| `Phone` | `string` | **Required**. User telephone |
| `username` | `string` | **Required**. username |
| `password` | `string` | **Required**. senha |
| `email` | `string` | **Required**. email  |
| `roles` | `List` | **Required**. Lista das roles|

## Autorização e Autenticação

### Refresh

`OPEN`

```http
GET /users/refresh
```
	curl -X GET "http://localhost:9090/users/refresh" -H "accept: */*"
			
### Deletar usuário 

`ROLE_ADMIN`

```http
DELETE /users/{username}
```
	curl -X DELETE "http://localhost:9090/users/citizen" -H "accept: */*"
			
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `username` | `string` | **Required**. User username |

### Recupera Usuario por Login

`ROLE_ADMIN`

```http
POST /users/{userName}
```
	curl -X GET "http://localhost:9090/users/citizen" -H "accept: */*"
			
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `username` | `string` | **Required**. User username |

## Hierarquia de dependencias

Execute gradle `htmlDependecyReport` para gerar um relatório HTML mostrando a hierarquia de dependencias de cada subprojeto
