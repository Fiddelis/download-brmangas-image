package fileutils;

import java.io.File;

public class FolderCreator {
    public FolderCreator(String path, String name, int chapter) {
        File folder = new File(path + name + "\\chapter" + chapter);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
