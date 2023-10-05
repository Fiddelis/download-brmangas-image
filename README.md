# Download Mangas Image 🕮
**Desenvolvido apenas para estudo!!!!**

Este código foi criado para permitir o download de mangás para leitura offline de alguns capítulos de mangás. O programa cria uma pasta dedicada para cada mangá, organizando os capítulos em subpastas e as páginas em seus respectivos diretórios.


# Possiveis implementações:
### ```java.nio``` para copiar streams
* Usar as classes java.nio.file.Files para copiar o InputStream diretamente para o arquivo de destino, tornando o código mais eficiente e conciso.

### Alteração na Classe WebChapterRequest.java
* Criar um método que recebe um StringBuilder e a extensão da imagem, pra evitar repetir código: método que recebe o padrão da regex e retorna o link encontrado.
