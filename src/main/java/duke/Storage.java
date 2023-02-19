package duke;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Deals with loading the TaskList from a file and saving the TaskList to a file
 */
public class Storage {
    private static String filepath;

    /**
     * Uses serialization to save the TaskList object into a file.
     *
     * @param taskList The object containing the list of tasks to save.
     * @throws IOException If there is an error writing the serialized object into the file.
     */
    public static void saveTaskList(TaskList taskList) throws IOException {
        FileOutputStream file = new FileOutputStream(filepath);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(taskList);
        out.close();
        file.close();
    }

    /**
     * Returns the TaskList after it has been deserialized from the file.
     *
     * @return The TaskList after deserialization from the file.
     * @throws IOException If there is an error loading the TaskList object from the file.
     * @throws ClassNotFoundException If there is an error loading the TaskList object from the file.
     */
    public static TaskList loadTaskList() throws IOException, ClassNotFoundException {

        //deserialization
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
