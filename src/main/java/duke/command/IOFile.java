package duke.command;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * The IOFile class reads and writes
 * onto a .txt file to save the tasks listed
 */
public class IOFile {

    public static final String FILEPATH = "data/tasklist.txt";

    /**
     * This method reads the tasks saved
     * onto the .txt file
     *
     * @param storedUserTasks ArrayList containing listed Tasks
     * @throws FileNotFoundException FileNotFoundException has occurred
     */
    public static void readData(ArrayList<Task> storedUserTasks) throws FileNotFoundException {
        String originalString, description, by, from, to;
        int indexOfBy, indexOfFrom, indexOfTo;
        boolean isDone;

        File f = new File(FILEPATH);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            originalString = s.nextLine();
            isDone = originalString.charAt(4) == 'X';
            switch (originalString.charAt(1)) {
            case 'T':
                description = originalString.substring(7);
                storedUserTasks.add(new Todo(description));
                break;
            case 'D':
                originalString = originalString.substring(7).replace("(by:", "/by");
                originalString = originalString.substring(0, originalString.length() - 1);
                indexOfBy = originalString.indexOf("/by");
                description = originalString.substring( 0, indexOfBy-1);
                by = originalString.substring(indexOfBy+4);
                storedUserTasks.add(new Deadline(description,by));
                break;
            case 'E':
                originalString = originalString.substring(7).replace("(from:", "/from");
                originalString = originalString.replace("to:", "/to");
                originalString = originalString.substring(0, originalString.length() - 1);
                indexOfFrom = originalString.indexOf("/from");
                indexOfTo = originalString.indexOf("/to");
                description = originalString.substring(0,indexOfFrom-1);
                from = originalString.substring(indexOfFrom+6,indexOfTo-1);
                to = originalString.substring(indexOfTo+4);
                storedUserTasks.add(new Event(description,from,to));
                break;
            }
            storedUserTasks.get(Duke.userTextCount).isDone = isDone;
            Duke.userTextCount++;
        }
    }

    /**
     * This method writes the tasks saved
     * onto the .txt file
     *
     * @param storedUserTasks ArrayList containing listed Tasks
     * @throws IOException IOException has occurred
     */
    public static void writeData(ArrayList<Task> storedUserTasks) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        for(int i=0; i<Duke.userTextCount; i++) {
            fw.write(storedUserTasks.get(i).toString()+'\n');
        }
        fw.close();
    }
}
