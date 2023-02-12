import tasktype.*;
import java.io. *;

public class FileSave {
    public static void initialise() throws IOException {
        File newFile = new File("./myTaskList.csv");

        if (!newFile.exists()) {
            newFile.createNewFile();
        }
    }

    



}
