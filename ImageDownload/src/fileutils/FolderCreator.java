package fileutils;

import java.io.File;

public class ChapterFolderCreator {
    public void create(String path, String nameManga) {
        File directory = new File(path + nameManga);

        if (!directory.exists()) {
            directory.mkdirs();
            System.out.println("Diretório criado com sucesso!");
        } else {
            System.out.println("Diretorio existente.");
        }
    }
}
