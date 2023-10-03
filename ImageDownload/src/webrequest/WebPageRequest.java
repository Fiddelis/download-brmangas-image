package webrequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WebPageRequest {
    public InputStream inputRequest(String imageUrl) throws IOException {
        InputStream inputStream;
        URL url;

        try {
            url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            inputStream = connection.getInputStream();
        } catch (IOException e) {   // caso não consiga conexão com o link .jpg
            imageUrl = imageUrl.substring(0, imageUrl.length() - 4) + ".png";    // altera o formato da imagem

            url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            inputStream = connection.getInputStream();
        }

        return inputStream;
    }
}
