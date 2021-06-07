# Desafio Spring BootCamp Meli
## Instruções para testes de todos os endpoints

### As etapas devem ser executadas em sequência

### 1. Criação de Usuários

#### endpoint
```
POST: localhost:8080/users/newuser
```
#### request body
```
{
    "userName":"Usuário 1",
    "seller":false
}
```
```
{
    "userName":"Usuário 2",
    "seller":false
}
```
```
{
    "userName":"Vendedor 1",
    "seller":true
}
```
```
{
    "userName":"Vendedor 2",
    "seller":true
}
```
```
{
    "userName":"Vendedor 3",
    "seller":true
}
```

###2. Requisitos Funcionais

[US 0001 - Seguir vendedor](doc/us0001.md)

[US 0002 - Número de seguidores](doc/us0002.md)

[US 0003 - Lista de seguidores](doc/us0003.md)

[US 0004 - Lista de vendedores que um usuário segue](doc/us0004.md)

[US 0005 - Nova publicação](doc/us0005.md)

[US 0006 - Lista de publicações dos vendedores que um usuário segue](doc/us0006.md)

[US 0007 - Deixar de seguir vendedor](doc/us0007.md)

[US 0008 - Listas de seguidores e seguidos ordenadas por nome](doc/us0008.md)

[US 0009 - Listas de publicações ordenadas por data](doc/us0009.md)

[US 0010 - Nova publicação promocional](doc/us0010.md)

[US 0011 - Quantidade de publicações promocionais de um vendedor](doc/us0011.md)

[US 0012 - Lista de publicações promocionais de um vendedor](doc/us0012.md) 

