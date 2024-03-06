# Dicionario cliente-servidor
Sistema de dicionário utilizando arquitetura cliente-servidor feito em Java
## Instruções de execução
- Clone o repositório
- Em um terminal, execute o arquivo Server.java
- Em outro terminal, execute o arquivo DicionarioClient.java

## Explicação do código
Existem dois pacotes: client e server
No server, estão os arquivos:
- Dicionario.java - interface que define o comportamento de um dicionário
- DicionarioException - Exeções que podem ser lançadas por um dicionário
- DicionarioServant - Classe que implementa a interface Dicionario e sobrescreve seus métodos
- Server.java - Servidor iniciado em uma porta determinada

No client, está o arquivo:
- DicionarioClient.java - Possui uma interface gráfica e permite que o usuário pesquise pelo significado de uma palavra, adicione palavras e remova palavras do dicionário

As palavras do dicionário estão salvas no arquivo dicionario.json
