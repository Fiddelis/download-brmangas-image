package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import fileutils.FolderCreator;
import webrequest.WebChapterRequest;
import fileutils.ImageDownload;

public class Main {
    public static void main(String[] args) throws IOException {
        ImageDownload image = new ImageDownload();
        WebChapterRequest webChapterRequest = new WebChapterRequest();
        Scanner sc = new Scanner(System.in);

        String link;

        System.out.print("Caminho Destino: ");  // exemplo: C:\Users\lucas.ferreira\Documents\Github\download-brmangas-image\
        String path = sc.nextLine();
        System.out.print("Nome do Mangá: ");
        String nameManga = sc.nextLine().replace(" ", "-").toLowerCase();
        System.out.print("Capitulo minimo: ");
        int chapterMin = sc.nextInt();
        System.out.print("Capitulo maximo: ");
        int chapterMax = sc.nextInt();

        try {
            link = webChapterRequest.ChapterRequest("https://www.brmangas.net/ler/" + nameManga + "-" + chapterMin + "-online/");
            if (link.equals("not found")) {
                System.out.println("Link da imagem não encontrado");
            }
        } catch (IOException e) {
            System.out.println("Nome invalido");
            return;
        }

        String linkManga = link.substring(0, link.indexOf("/uploads/"));
        System.out.println("Host:" + linkManga);

        for (int chapter = chapterMin; chapter <= chapterMax; chapter++) {
            FolderCreator folderCreator = new FolderCreator(path, nameManga, chapter);

            boolean chapterCompleted = false;
            int page = 0;
            while (!chapterCompleted) {
                page++;
                String imageUrl = linkManga + "/uploads/" + nameManga.charAt(0) + "/" + nameManga + "/" + chapter + "/" + page + ".jpg";
                String destinationFile = path + nameManga + "/chapter" + chapter + "\\" + page + ".jpg";

                chapterCompleted = image.downloadPage(imageUrl, destinationFile, chapter);
            }
        }
    }
}