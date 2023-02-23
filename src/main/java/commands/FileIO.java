package commands;

import tasks.Task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileIO {
    final static String parentDir = "data";
    final static Path parentPath = Paths.get(parentDir);
    static File f = new File("data/userData.txt");

    public static boolean isExist() {
        return f.exists();
    }

    public static void runFileIO(ArrayList<Task> list) {
        boolean isFileExist = isExist();
        if (isFileExist) {
            ReadFile.readFile(f, list);
        } else {
            CreateFile.createFile(f, parentPath);
        }
    }

}
