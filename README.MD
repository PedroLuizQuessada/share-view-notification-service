# Execução local:
1) Abrir CMD
2) Acessar o diretório onde consta o jar "h2-2.3.232.jar"
3) Executar o comando "java -cp h2-2.3.232.jar org.h2.tools.Server -tcp -tcpAllowOthers -ifNotExists"
4) Swagger estará disponível em: http://localhost:8081/share-view-notification-service/swagger-ui/index.html

# Alterações para ambientes corporativos:
1) Adição do arquivo src/main/resources/application-jpa.properties no .gitignore
2) Adição dos arquivos da pasta src/main/resources/keys no .gitignore
3) Adição do arquivo src/main/resources/application-javamailsender.properties no .gitignore