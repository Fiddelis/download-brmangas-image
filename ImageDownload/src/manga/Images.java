package manga;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Images {
    public boolean downloadChapter(String imageUrl, String destinationFile, int chapter) throws IOException {
        InputStream inputStream;

        // Criando conexão com o link .jpg/png
        try {
            try {
                URL url = new URL(imageUrl);
                URLConnection connection = url.openConnection();
                inputStream = connection.getInputStream();
            } catch (FileNotFoundException e) {   // caso não consiga conexão com o link .jpg
                imageUrl = imageUrl.substring(0, imageUrl.length() - 4) + ".png";    // altera o formato da imagem
                URL url = new URL(imageUrl);
                URLConnection connection = url.openConnection();
                inputStream = connection.getInputStream();
            }

            // Download da Imagem
            try (FileOutputStream outputStream = new FileOutputStream(destinationFile)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                System.out.println(imageUrl);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Capitulo " + chapter + " baixado com sucesso!");
            return true;
        }
        return false;
    }
}
