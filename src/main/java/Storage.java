import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    public static String filePath;
    public Storage(String path){
        this.filePath=path;
    }
    public static String load() {
        return filePath;
    }

    /**
     * to save the taskList to txt file
     * @param list the whole taskList created by user
     */
    public static void save(ArrayList<Task> list){
        try {
            FileWriter writer = new FileWriter("duke.txt", false);
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i).toString());
                writer.write("\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
