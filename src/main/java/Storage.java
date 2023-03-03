import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Represents a storage that deals with local file.
 * A <code>Storage</code> can write and scan text data to/from the local file.
 */

public class Storage {
    static String home = System.getProperty("user.home");
    static java.nio.file.Path path = Paths.get(home, "duke.txt");

    public Storage(){
        try {
            if (!Files.exists(path)) {
                UI.printFileCreatedComment(Files.createFile(path).toString());
            }
        } catch(IOException e){
            UI.printFileNotCreatedComment();
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<String> scanData() throws IOException {
        ArrayList<String> existingTasks = new ArrayList<>();
        existingTasks.addAll(Files.readAllLines(path));
        return existingTasks;
    }


    public void writeToFile(String textAdded){
        try {
            Files.writeString(path, textAdded);
        } catch (IOException e){
            UI.printSaveErrorComment();
            System.out.println(e.getMessage());
        }
    }

}
