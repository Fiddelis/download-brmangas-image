import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome do Mangá: ");
        String nameManga = sc.nextLine();
        System.out.print("Capitulo minimo: ");
        int minCap = sc.nextInt();
        System.out.print("Capitulo maximo: ");
        int maxCap = sc.nextInt();

        File directory = new File("/home/fidelis/Documentos/" + nameManga);
        if(!directory.exists()) {
            directory.mkdirs();
        } else {
            System.out.println("Diretorio existente");
        }


        for(int i = minCap;i <= maxCap; i++) {
            File cap = new File("/home/fidelis/Documentos/" + nameManga + "/Cap-" + i);
            if(!cap.exists()) {
                cap.mkdirs();
            }
            int j = 0;
            while (true) {
                j++;
                String imageUrl = "https://dn1.imgstatic.club/uploads/v/vagabond/" + i + "/" + j + ".jpg";
                String destinationFile = "/home/fidelis/Documentos/" + nameManga + "/Cap-" + i + "/pagina-" + j + ".jpg";

                try {
                    URL url = new URL(imageUrl);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();

                    try (FileOutputStream outputStream = new FileOutputStream(destinationFile)) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        System.out.println(imageUrl);
                    }
                } catch (IOException e) {
                    System.out.println("Capitulo " + i + " baixado com sucesso!");
                    break;
                }
            }
        }
    }
}