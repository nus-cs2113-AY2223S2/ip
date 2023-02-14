import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {

    public static File createFile() {
        try {
            File f = new File("save.txt");
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists.");
            }
            return f;
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return null;
    }

    private static void readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String type;
        }
    }
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(createFile());
        for (int i = 0; i < Psyduck.getTaskCount(); i++) {
            fw.write( Psyduck.getTask(i + 1).getType() + " / "
                    + Psyduck.getTask(i + 1).isDone() + " / "
                    + Psyduck.getTask(i + 1).getDescription() + " / "
                    + System.lineSeparator());
        }
        fw.close();
    }

}
