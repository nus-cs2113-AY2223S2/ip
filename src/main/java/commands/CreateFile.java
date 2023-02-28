package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFile {
    /**
     * if the file is not existed create an empty txt file
     * @param f file name
     * @param parentPath the parent path of the file
     */
    public static void createFile(File f, Path parentPath) {
        try {
            Files.createDirectories(parentPath);
            if (f.createNewFile()) {
                System.out.println("File Created");
            }
        } catch (IOException e) {
            System.out.println("Error occurs");
        }
    }
}
