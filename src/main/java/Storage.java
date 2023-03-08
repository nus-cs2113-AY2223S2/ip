import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static messages.operationalMessages.MESSAGE_NO_EXISTING_FILE;

public class Storage {

    /**
     * loads a Todo task from an existing list
     *
     * @param isDoneString whether the task is done
     * @param description  String containing description of Todo to be added
     * @return Todo read from file
     */
    public static Task loadNewTodo(String isDoneString, String description) {
        Todo readTodo = new Todo(description);
        if (isDoneString.equals("1")) {
            readTodo.isDone = true;
        }
        return readTodo;
    }

    /**
     * loads an Event task from an existing list
     *
     * @param isDoneString whether the task is done
     * @param description  String containing description of the Event to be added
     * @param start        start date and/or time of the event
     * @param end          end date and/or time of the event
     * @return Event read from file
     */
    public static Task loadNewEvent(String isDoneString, String description, String start, String end) {
        Event readEvent = new Event(description, start, end);
        if (isDoneString.equals("1")) {
            readEvent.isDone = true;
        }
        return readEvent;
    }

    /**
     * loads a Deadline task from an existing list
     *
     * @param isDoneString whether the task is done
     * @param description  String containing description of the Deadline to be added
     * @param by           due date and/or time of the deadline
     * @return Deadline read from file
     */
    public static Task loadNewDeadline(String isDoneString, String description, String by) {
        Deadline readDeadline = new Deadline(description, by);
        if (isDoneString.equals("1")) {
            readDeadline.isDone = true;
        }
        return readDeadline;
    }

    /**
     * identifies the type of task being loaded
     *
     * @param items    ArrayList of Tasks containing all the task items added
     * @param taskArgs Array of Strings of task information
     */
    public void loadTask(ArrayList<Task> items, String[] taskArgs) {
        if (taskArgs[0].equals("T")) {
            items.add(loadNewTodo(taskArgs[1].trim(), taskArgs[2].trim()));
        } else if (taskArgs[0].equals("E")) {
            items.add(loadNewEvent(taskArgs[1].trim(), taskArgs[2].trim(), taskArgs[3].trim(), taskArgs[4].trim()));
        } else {
            taskArgs[0] = "D";
            items.add(loadNewDeadline(taskArgs[1].trim(), taskArgs[2].trim(), taskArgs[3].trim()));
        }
    }

    /**
     * loads all tasks in an existing file
     *
     * @param items ArrayList of Tasks containing all the task items added
     * @throws FileNotFoundException if there is no existing file
     */
    public void loadFromFile(ArrayList<Task> items) throws FileNotFoundException {
        File f = new File("data/Duke.txt");
        if (f.exists()) {
            Scanner scan = new Scanner(f);
            while (scan.hasNext()) {
                String taskLine = scan.nextLine();
                String[] taskArgs = taskLine.split("\\|");
                loadTask(items, taskArgs);
            }
        } else {
            System.out.println(MESSAGE_NO_EXISTING_FILE);
            File dataFolder = new File("data");
            dataFolder.mkdir();
        }
    }

    /**
     * changes the list of tasks
     *
     * @param items ArrayList of Tasks containing all the task items added
     * @throws IOException if changes cannot be made
     */
    public static void writeToFile(ArrayList<Task> items) throws IOException {
        FileWriter fw = new FileWriter("data/Duke.txt");
        StringBuilder sb = new StringBuilder();
        for (Task item : items) {
            String newSerialisedTask = item.toSerialisedString();
            sb.append(newSerialisedTask).append("\n");
        }
        String serialString = sb.toString();
        fw.write(serialString);
        fw.close();
    }
}