import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {
    public static void makeFile (String path, String name) throws IOException {
        File directory, file;
        directory = new File(name);
        file = new File(path);

        // if dir does not exist, make it
        if (!directory.exists()){
            directory.mkdirs();
        }

        try {
            file.createNewFile();
        } catch (IOException exception) {
            System.out.println("An error occured when creating file");
        }
    }

    public static void appendFile(String path, String data) throws IOException {
        FileWriter add;
        add = new FileWriter(path, true);
        add.write(data);
        add.close();
    }

    public static void addToFile(String path, String data) throws IOException {
        FileWriter add;
        add = new FileWriter(path);
        add.write(data);
        add.close();
    }
}