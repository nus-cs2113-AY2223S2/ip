import exceptions.MarkOutOfBounds;
import exceptions.UnmarkOutOfBounds;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    /***
     * Adds processed user inputs into the list of stored values.
     * @param storedValues List of tasks from user inputs.
     * @param taskNum The existing position in the list.
     * @param line User input to be processed.
     * @return Updated position in the list.
     */
    static int addTask(ArrayList<Task> storedValues, int taskNum, Task line) {
        formattingLine();
        System.out.println("Got it. I've added this task: \n" + line.toString() + "\n" +
                "Now you have " + (taskNum + 1) + " tasks in the list.\n");
        formattingLine();
        // Store value in line into list
        storedValues.add(taskNum,line);
        taskNum += 1;
        try {
            Storage.writeToFile(line);
        } catch (IOException e) {
            System.out.println("Something went wrong..." + e.getMessage());
        }
        return taskNum;
    }

    /***
     * Prints the fixed formatting line when called.
     */
    public static void formattingLine() {
        System.out.println(Duke.LINE_FORMATTING);
    }

    /***
     * Deletes the task in the list and the txt file, when the user no longer needs the task.
     * @param line The command input from user.
     * @param storedValues List of inputs stored by the user.
     * @throws IOException Thrown when error is detected.
     */
    static void deleteTask(String line, ArrayList<Task> storedValues) throws IOException {
        int length = line.length();
        String itemToDelete = line.substring(Duke.REMOVE_DELETE_NUM, length);
        int posToDelete = Integer.parseInt(itemToDelete);
        Task value = storedValues.get(posToDelete - 1);
        int currLine = 0;

        Storage.deleteTaskInTxt(posToDelete, currLine);

        storedValues.remove(posToDelete - 1);
        formattingLine();
        System.out.println("Noted. I've removed this task: \n" +
                value + "\n" +
                "Now you have " + (Duke.taskNum - 1) + " tasks in the list. \n");
        formattingLine();

        Duke.taskNum -= 1;
    }

    /***
     * Unmarks the task in the list when called.
     * @param storedValues List of tasks from user inputs.
     * @param line User input to be processed.
     * @throws UnmarkOutOfBounds Thrown when the value input is out of bounds.
     * @throws IOException Thrown when other errors are detected.
     */
    static void unmarkItem(ArrayList<Task> storedValues, String line) throws UnmarkOutOfBounds, IOException {
        int length = line.length();
        String itemToMark = line.substring(Duke.REMOVE_UNMARK_NUM, length);
        int numToMark = Integer.parseInt(itemToMark);
        // Unmark the item
        if (Duke.taskNum < numToMark) {
            throw new UnmarkOutOfBounds();
        } else {
            Storage.unmarkTaskInTxt(storedValues, numToMark);
        }
    }

    /***
     * Marks the task in the list when called.
     * @param storedValues List of tasks from user inputs.
     * @param line User input to be processed.
     * @param taskNum The task number from user input that is to be marked.
     * @throws MarkOutOfBounds Thrown when the task number exceeds the number of items in list.
     * @throws IOException Thrown when error is detected.
     */
    static void markItem(ArrayList<Task> storedValues, String line, int taskNum) throws MarkOutOfBounds, IOException {
        int length = line.length();
        String itemToMark = line.substring(Duke.REMOVE_MARK_NUM, length);
        int numToMark = Integer.parseInt(itemToMark);
        // Mark the item as complete
        if (taskNum < numToMark) {
            // Means that it is out of bounds
            throw new MarkOutOfBounds();
        } else {
            Storage.markTaskInTxt(storedValues, numToMark);
        }
    }

    /***
     * Print all items within the list of stored tasks.
     * @param storedValues List of tasks from user inputs.
     */
    static void printList(ArrayList<Task> storedValues, String textToPrint) {
        int currValue = 0;
        formattingLine();
        System.out.println(textToPrint);
        for (Task value : storedValues) {
            System.out.println((currValue + 1) + "." + value.toString());
            currValue += 1;
        }
        formattingLine();
    }

    /***
     * Searches the tasks within the existing task list with the same keyword typed in the "find" command.
     * @param storedValues The existing task list that the user has.
     * @param wordToFind The string that the user is trying to find within their task list.
     */
    static void findTasks(ArrayList<Task> storedValues, String wordToFind) {
        ArrayList<Task> keywordList = new ArrayList<>();
        for (Task task : storedValues) {
            if (task.description.contains(wordToFind)) {
                keywordList.add(task);
            }
        }

        Ui.printWordToFind(keywordList, wordToFind);
    }
}
