package Duke;
import java.io.File;
import Duke.Tasks.Task;
import Duke.TaskList;
import Duke.Tasks.Todo;
import Duke.Tasks.Event;
import Duke.Tasks.Deadline;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Storage {
    private static final String FILEPATH=".\\lists.txt";
    public static void writeToFile(Task[] Tasks, int index) throws IOException {
            File f = new File(FILEPATH);
            if (!f.exists()) {
                f.createNewFile();
                System.out.println("File created: " + f.getName());
            }
            FileWriter fw = new FileWriter(FILEPATH);
            for (int i = 0; i < index; i++) {
                fw.write((i + 1) + "." + Tasks[i].toString() + '\n');
            }
            fw.close();
        }
        public static int initializeList(Task[] lists) throws FileNotFoundException {
            File f = new File(FILEPATH);
            int count = 0;
            if (f.exists()) {
                Scanner s = new Scanner(f);
                String line;
                while (s.hasNext()) {
                    line = s.nextLine();
                    String taskType = line.substring(0, 5);
                    String[] contents = line.substring(6).split("]", 2);
                    if (taskType.contains("T")) {
                        TaskList.addTodo(lists, contents, count);
                    } else if (taskType.contains("E")) {
                        TaskList.addEvent(lists, contents, count);
                    } else if (taskType.contains("D")) {
                        TaskList.addDeadline(lists, contents, count);
                    } else {
                        System.out.println("There's unexpected content in your file.");
                    }
                    count++;
                }
            }
            return count;
        }
}
