# Execução local:
1) clonar o repositório https://github.com/PedroLuizQuessada/share-view-stub e executar o comando “mvn install”
2) clonar o repositório https://github.com/PedroLuizQuessada/share-view-model-jpa e executar o comando “mvn install”
3) possuir uma instância do PostgreSQL rodando em sua máquina
4) configurar o arquivo src/main/resources/application-jpa.properties com a url, o username e a password da instância do PostgreSQL
5) configurar o arquivo src/main/resources/application-javamailsender.properties com as propriedades desejadas para o envio de notificações via e-mail
6) Swagger estará disponível em: http://localhost:8081/share-view-notification-service/swagger-ui/index.html

# Alterações para ambientes corporativos:
1) Adição do arquivo src/main/resources/application-jpa.properties no .gitignore
2) Adição dos arquivos da pasta src/main/resources/keys no .gitignore
3) Adição do arquivo src/main/resources/application-javamailsender.properties no .gitignore