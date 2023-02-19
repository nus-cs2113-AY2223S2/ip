package duke;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage implements java.io.Serializable {
    private static String filepath;

    public static void saveTaskList(TaskList list) throws IOException {
        // Serialization and saving of object in a file
        FileOutputStream file = new FileOutputStream(filepath);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(list);
        out.close();
        file.close();
    }

    public static TaskList loadTaskList() throws IOException, ClassNotFoundException {

        //deserialisation
        FileInputStream file = new FileInputStream(filepath);
        ObjectInputStream in = new ObjectInputStream(file);
        TaskList list = (TaskList) in.readObject();
        in.close();
        file.close();

        return list;
    }

    public Storage(String filepath) {
        this.filepath = filepath;
    }
}
