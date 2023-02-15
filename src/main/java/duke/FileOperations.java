package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {

    //hardcode file path: "data/duke.txt"

    //create file if file doesn't exist
    public static void createFile (String filePath, String pathName) throws IOException {
        /*String pathName = "data";
        String filePath = "data/duke.txt";*/
        File directory = new File(pathName);
        File file = new File(filePath);
        if (!directory.exists()){
            directory.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException exception) {
            System.out.println("Error while creating the file.");
        }
    }

    //Write to file
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    //append to file
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

}