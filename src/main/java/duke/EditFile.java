package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EditFile {
    private static final String FILE = "./duke.txt";
    private static final String DIRECTORY = "data";

    /**
     * Checks that the appropriate directory and file exists for saving the file later. If the directory or file does
     * not exist, create the directory or file respectively
     */
    public static void checkFile() throws IOException,FileNotFoundException {
        File directory = new File(DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File text = new File(FILE);
        if (!text.exists()) {
            text.mkdir();
        }
    }

    /**
     * Loads the saved list of task into the current list of task
     *
     * @param tasks the list of tasks from the saved file
     * @throws FileNotFoundException the file does not exist
     */
    public static void loadFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File("data/duke.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String text = s.nextLine();
            String[] message = text.split("//" ,-1);
            switch (message[0]) {
            case "T":
                Todo loadTodo = new Todo(message[2]);
                tasks.add(loadTodo);
                if (message[1].equals(1)) {
                    loadTodo.markAsDone();
                }
                break;
            case "D":
                Deadline loadDeadline = new Deadline(message[2], message[3]);
                tasks.add(loadDeadline);
                if (message[1].equals(1)) {
                    loadDeadline.markAsDone();
                }
                break;
            case "E":
                Event loadEvent = new Event(message[2], message[3], message[4]);
                tasks.add(loadEvent);
                if (message[1].equals(1)) {
                    loadEvent.markAsDone();
                }
                break;
            default:
                break;
            }
        }
    }

    /**
     * Saves the list of tasks to the previously created file path
     *
     * @param filePath the path where the file is located at
     * @param tasks the list of tasks
     * @throws IOException the file path does not exist or is incorrect
     */
    public static void writetoFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).saveTask());
        }
        fw.close();
    }
}