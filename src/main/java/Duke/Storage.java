package Duke;

import java.io.File;

import Duke.Tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Deal with writing and reading file given a relative path.
 */
public class Storage {
    private static final String FILEPATH = ".\\lists.txt";

    /**
     * Write the content of the task list to a file
     * given the relative path of the file.
     * If the file does not exist, then create a new file and write.
     *
     * @param Tasks the task list
     * @param index the length of the list
     * @throws IOException
     */
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

    /**
     * Try to find whether the file exists based on the relative path.
     * If the file exists, write the content of the file line by line
     * to the task list in the parameter list.
     * If not, then do nothing
     *
     * @param lists The task list that the content of the file should be written to
     * @return the length of the list
     * @throws FileNotFoundException
     */
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
                if (line.substring(0, 7).contains("X")) {
                    lists[count].isDone = true;
                }
                count++;
            }
        }
        return count;
    }
}
