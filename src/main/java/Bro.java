import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

import bro.exceptions.invalidInputFormat;
import bro.exceptions.invalidTaskIndexException;
import bro.tasks.Type;
import bro.tasks.Task;
import bro.tasks.ToDo;
import bro.tasks.Deadline;
import bro.tasks.Event;
public class Bro {
    public static final String HORIZONTAL_LINE = "\n─────────────────────────────────────────────────────────────────────────────────────────────\n";
    static final String PATH_NAME = "saved_tasks.txt";
    public static final String GREETING = " Sup bro. I'm Bro.\n" + " What do you want?";
    public static final String TASK_DOES_NOT_EXIST = " Bro that task number does not exist...";
//    static final String FILE_NOT_FOUND = "File not found";
    static final String IO_ERROR = "Error in reading/writing saved tasks file";

    public static void main(String[] args) throws IOException {
        ArrayList<Task> taskList;
        try {
            taskList = Save.getSavedTasks(new ArrayList<>());
        } catch (FileNotFoundException e) {     // create a new file to save the list of tasks
            File f = new File(PATH_NAME);
            if (f.createNewFile()) {                  // throws IOException
                System.out.println(" New File \"saved_tasks.txt\" created at: " + f.getAbsolutePath());
            }
            taskList = Save.getSavedTasks(new ArrayList<>());
        }
        System.out.println(HORIZONTAL_LINE + GREETING + HORIZONTAL_LINE);
        // User Input
        String line;
        StringBuilder reply;  // Use StringBuilder as we concatenate Strings in a loop later on
        Scanner in = new Scanner(System.in);
        boolean haveInput = true;
        while (haveInput) {
            line = in.nextLine();
            String[] arrayOfInputs = line.split(" ");
            switch (arrayOfInputs[0]) {
            case "bye":
                reply = new StringBuilder(" Bye bye bro.");
                haveInput = false;
                break;
            case "list":
                reply = new StringBuilder(" Your tasks:\n");
                for (int i = 0; i < taskList.size(); ++i) {
                    Task currentTask = taskList.get(i);
                    String mark = currentTask.mark();
                    reply.append(" ").append(i + 1).append(".[").append(currentTask.getType()).append("][").append(mark).append("] ").append(currentTask).append("\n");
                }
                break;
            case "mark": // Fallthrough
            case "unmark":
                boolean markAsComplete = arrayOfInputs[0].equals("mark");   // this boolean decides if the following `markComplete()` marks the task as Completed or Uncompleted
                try {
                    reply = markComplete(markAsComplete, taskList, arrayOfInputs);
                } catch (invalidInputFormat e) {
                    reply = new StringBuilder(e.toString());
                } catch (invalidTaskIndexException e) {
                    reply = new StringBuilder(TASK_DOES_NOT_EXIST);
                }
                Save.saveToFile(taskList);
                break;
            case "todo":
                try {
                    reply = createToDo(taskList, arrayOfInputs);
                } catch (invalidInputFormat e) {
                    reply = new StringBuilder(e.toString());
                }
                Save.saveToFile(taskList);
                break;
            case "deadline":
                try {
                    reply = createDeadline(taskList, arrayOfInputs);
                } catch (invalidInputFormat e) {
                    reply = new StringBuilder(e.toString());
                }
                Save.saveToFile(taskList);
                break;
            case "event":
                StringBuilder eventName = new StringBuilder();
                StringBuilder startTime = new StringBuilder();
                StringBuilder endTime = new StringBuilder();
                int indexOfStartTime = Arrays.asList(arrayOfInputs).indexOf("/from");
                int indexOfEndTime = Arrays.asList(arrayOfInputs).indexOf("/to");
                for (int i = 1; i < indexOfStartTime; ++i) {
                    eventName.append(" ").append(arrayOfInputs[i]);
                }
                for (int i = indexOfStartTime + 1; i < indexOfEndTime; ++i) {
                    startTime.append(" ").append(arrayOfInputs[i]);
                }
                for (int i = indexOfEndTime + 1; i < arrayOfInputs.length; ++i) {
                    endTime.append(" ").append(arrayOfInputs[i]);
                }
                Task event = new Event(eventName.toString().trim(), startTime.toString().trim(), endTime.toString().trim());
                taskList.add(event);
                reply = new StringBuilder(" added: " + event);
                Save.saveToFile(taskList);
                break;
            case "delete":
                try {
                    reply = deleteTask(taskList, arrayOfInputs);
                } catch (invalidInputFormat e) {
                    reply = new StringBuilder(e.toString());
                } catch (invalidTaskIndexException e) {
                    reply = new StringBuilder(TASK_DOES_NOT_EXIST);
                }
                Save.saveToFile(taskList);
                break;
            default:
                reply = new StringBuilder(" Not a valid command bro...");
            }
            System.out.println(HORIZONTAL_LINE + reply + HORIZONTAL_LINE);  // Output reply
        }
    }

    /**
     * Validate the input format, creates a Todo object and returns Bro's reply.
     *
     * @param taskList List of all tasks
     * @param arrayOfInputs Array of input words
     * @return Bro's reply
     */
    private static StringBuilder createToDo(ArrayList<Task> taskList, String[] arrayOfInputs) throws invalidInputFormat {
        StringBuilder todoName = new StringBuilder();
        if (arrayOfInputs.length < 2) {
            throw new invalidInputFormat(Type.TODO);
        }
        for (int i = 1; i < arrayOfInputs.length; ++i) {
            todoName.append(" ").append(arrayOfInputs[i]);
        }
        Task todo = new ToDo(todoName.toString().trim());
        taskList.add(todo);
        return new StringBuilder(" added: " + todo);
    }

    /**
     * Validate the input format, creates a Deadline object and returns Bro's reply.
     *
     * @param taskList List of all tasks
     * @param arrayOfInputs Array of input words
     * @return Bro's reply
     */
    private static StringBuilder createDeadline(ArrayList<Task> taskList, String[] arrayOfInputs) throws invalidInputFormat {
        int indexOfDeadline = Arrays.asList(arrayOfInputs).indexOf("/by");
        if (indexOfDeadline == -1 || indexOfDeadline == arrayOfInputs.length - 1) { // user did not input "/by" or did not input a deadline time
            throw new invalidInputFormat(Type.DEADLINE);
        }
        // Separate arrayOfInputs into `deadlineName` and `by` to construct deadline object
        StringBuilder deadlineName = new StringBuilder();
        StringBuilder by = new StringBuilder();
        for (int i = 1; i < indexOfDeadline; ++i) {
            deadlineName.append(" ").append(arrayOfInputs[i]);
        }
        for (int i = indexOfDeadline + 1; i < arrayOfInputs.length; ++i) {
            by.append(" ").append(arrayOfInputs[i]);
        }
        Task deadline = new Deadline(deadlineName.toString().trim(), by.toString().trim());
        taskList.add(deadline);
        return new StringBuilder(" added: " + deadline);
    }

    /**
     * @param queryType Type of the Task that the input is referring to (this variable is meant for exception handling)
     * @param sizeOfTaskList Size of the list of all Tasks
     * @param arrayOfInputs Array of input words
     * @return The valid input Task Index
     */
    private static int checkAndGetValidTaskIndex(Type queryType, int sizeOfTaskList, String[] arrayOfInputs) throws invalidInputFormat, invalidTaskIndexException {
        int taskIndex;
        // Validate if input format is valid
        try {
            taskIndex = Integer.parseInt(arrayOfInputs[1]) - 1;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new invalidInputFormat(queryType);
        }
        // Validate if input Task index is valid
        if (taskIndex + 1 > sizeOfTaskList || taskIndex < 0) {
            throw new invalidTaskIndexException();
        }
        return taskIndex;
    }

    /**
     * Validate the input format and task index, sets the task completion status and returns Bro's reply according to said completion status.
     *
     * @param markAsComplete boolean that decides if this method marks the input task as Completed or Uncompleted
     * @param taskList List of all tasks
     * @param arrayOfInputs Array of input words
     * @return Bro's reply
     */
    private static StringBuilder markComplete(boolean markAsComplete, ArrayList<Task> taskList, String[] arrayOfInputs) throws invalidInputFormat, invalidTaskIndexException {
        int taskIndex = checkAndGetValidTaskIndex(Type.MARK, taskList.size(), arrayOfInputs);
        if (markAsComplete){   // mark as Completed
            taskList.get(taskIndex).setCompleted();
            return new StringBuilder(" Marked " + taskList.get(taskIndex) + " as done.");
        }
        else {                      // mark as Uncompleted
            taskList.get(taskIndex).setUncompleted();
            return new StringBuilder(" Marked " + taskList.get(taskIndex) + " as not done.");
        }
    }

    /**
     * @param taskList List of all Tasks
     * @param arrayOfInputs Array of input words
     * @return Bro's reply
     */
    private static StringBuilder deleteTask(ArrayList<Task> taskList, String[] arrayOfInputs) throws invalidInputFormat, invalidTaskIndexException {
        int taskIndex = checkAndGetValidTaskIndex(Type.DELETE, taskList.size(), arrayOfInputs);
        Task currentTask = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        return new StringBuilder(" Ok bro I remove this task:\n" + "   [" + currentTask.getType() + "][" + currentTask.mark() + "] " + currentTask + "\n Now you have " + taskList.size() + " tasks in the list.");
    }
}
