package program;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import manga.HtmlPage;
import manga.Images;

public class Main {
    public static void main(String[] args) throws IOException {
        Images mangas = new Images();
        HtmlPage htmlPage = new HtmlPage();
        Scanner sc = new Scanner(System.in);

        System.out.print("Caminho Destino: ");  // /home/fidelis/Documentos/
        String path = sc.nextLine();
        System.out.print("Nome do Mangá: ");
        String nameManga = sc.nextLine().replace(" ", "-").toLowerCase();
        System.out.print("Capitulo minimo: ");
        int chapterMin = sc.nextInt();
        System.out.print("Capitulo maximo: ");
        int chapterMax = sc.nextInt();

        String link = htmlPage.request("https://www.brmangas.net/ler/" + nameManga + "-" + chapterMin + "-online/");
        int index = link.indexOf("/uploads/");
        String linkManga = link.substring(0, index);
        System.out.println(linkManga);
        File directory = new File(path + nameManga);
        if(!directory.exists()) {
            directory.mkdirs();
            System.out.println("Diretório criado com sucesso!");
        } else {
            System.out.println("Diretorio existente.");
        }

        for(int chapter = chapterMin; chapter <= chapterMax; chapter++) {
            File cap = new File(path + nameManga + "/chapter" + chapter);
            if(!cap.exists()) {
                cap.mkdirs();
            }
            boolean chapterCompleted = false;
            int page = 0;
            while(!chapterCompleted) {
                page++;
                String imageUrl = linkManga + "/uploads/" + nameManga.charAt(0) + "/" + nameManga + "/" + chapter + "/" + page + ".jpg";
                System.out.println(imageUrl);
                String destinationFile = path + nameManga + "/chapter" + chapter + "\\" + page + ".jpg";

                chapterCompleted = mangas.downloadChapter(imageUrl, destinationFile, chapter);
            }
        }
    }
}