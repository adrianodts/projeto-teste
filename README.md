# Projeto de Teste

## Visão Geral 

* Criar um projeto utilizando Spring que mapeie informações de clientes e assinaturas.

### Introdução

Mapeie as seguintes informações utilizando Spring:

-	Cliente (nome, documento, login do cliente)
-	Assinatura (cliente assinante, produto assinado)

O sistema deve ser capaz de:

-	Incluir um novo cliente
-	Incluir uma assinatura para um cliente existente
-	Dado um login e senha, encontrar o cliente e exibir os dados do cliente e as assinaturas deste cliente

#### Requitos

- Eclipse
- Maven 
- Tomcat

# Executando o sistema 

- Realizar o download do zip ou clonar repositorio Git.

- Descompactar o arquivo zipado (apenas se fez o download do zip)

- Abra o Eclipse e importe o projeto:	
	- File -> Import -> Existing Maven Project -> Next -> Navegue ao diretório onde você descompactou o arquivo zipado.
	- Selecione o projeto (/pom.xml ...) e clique em finalizar.
	
- Clique no menu Run -> Run As -> 4 Maven Build ou a combinação de teclas (Alt+Shit+X, M)    
	- Base Directory: Selecione o projeto 
	- Goals: digitar -> clean install tomcat7:run
	- Clique Apply e em seguida clique em Run

#####Ou

- Abra o Prompt de Comando e troque para o diretório que contém o arquivo pom.xml
	- Execute: mvn clean install tomcat7:run


## Acessando o sistema

- Abra o browser a acesse o seguinte endereço: http://localhost:9090/
- Para navegar é bem simples, a página inicial contém algumas informações das funcionalidades do sistema tornando fácil o manuseio.

