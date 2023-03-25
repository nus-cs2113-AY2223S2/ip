import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class containing methods that allow reading and writing save data from a text file
 */
public class Storage {

    /**
     * Writes content in command line to Duke.txt, if the command was valid AND altered the list
     * @param filePath text file to be written to (Duke.txt)
     * @param textToAdd text to be written
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    /**
     * reads all previous command lines stored in Duke.txt to restore list to its previous state upon
     * reopening Duke
     * @param filePath text file to be written to (Duke.txt)
     * @param i current index in tasks for Mark, where necessary
     * @param tasks arraylist of tasks
     * @param formatter for formatting LocalDateTime values to "yyyy-MM-dd'T'HH:mm"
     */
    //reads all previous command lines stored in Duke.txt to restore list to its previous state upon
    //reopening Duke
    public static void readFileContents(String filePath, int i, ArrayList<Task> tasks, DateTimeFormatter formatter) throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String line;
        while (s.hasNext()) {
            line = s.nextLine();
            if (line.startsWith("todo")) {
                List.todo(tasks, formatter, line);
                i++;
            } else if (line.startsWith("deadline")) {
                List.deadline(tasks, formatter, line);
                i++;
            } else if (line.startsWith("event")) {
                List.event(tasks, formatter, line);
                i++;
            } else if (line.startsWith("delete")) {
                List.delete(tasks, i, line);
            } else if (line.startsWith("mark")) {
                Mark.mark(i, line, tasks);
            } else if (line.startsWith("unmark")) {
                Mark.unmark(i, line, tasks);
            }
        }
    }

}
