package fileutils;

import webrequest.WebPageRequest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageDownload {
    public boolean downloadPage(String imageUrl, String destinationFile, int chapter) throws IOException {
        WebPageRequest webPageRequest = new WebPageRequest();
        InputStream inputStream;

        try (FileOutputStream outputStream = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            inputStream = webPageRequest.inputRequest(imageUrl);
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println(imageUrl);
        } catch (FileNotFoundException e) {
            System.out.println("Capitulo " + chapter + " baixado com sucesso!");
            return true;
        }
        return false;
    }
}
