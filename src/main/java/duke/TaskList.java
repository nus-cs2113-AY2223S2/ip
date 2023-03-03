package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static duke.Ui.*;

public class TaskList {

    public static int addEvent(String line, int item, ArrayList tasks) {
        try {
            int toIndex = line.indexOf("/to");
            String toDate = line.substring(toIndex + 3);
            int fromIndex = line.indexOf("/from");
            String fromDate = line.substring(fromIndex + 5, toIndex);
            int descriptionIndex = line.indexOf("event");
            String description = line.substring(descriptionIndex + 6, fromIndex);

            Event newEvent = new Event(description, fromDate, toDate);
            tasks.add(newEvent);
            item++;
            displayTaskAddedMessage(newEvent);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(EMPTY_EVENT);

        } finally {
            return item;

        }
    }

    public static int addDeadline(String line, int item, ArrayList tasks) {
        try {
            int byIndex = line.indexOf("/by");
            String byDate = line.substring(byIndex + 3);
            int descriptionIndex = line.indexOf("deadline");
            String description = line.substring(descriptionIndex + 9, byIndex);

            Deadline newDeadline = new Deadline(description, byDate);
            tasks.add(newDeadline);

            item++;
            displayTaskAddedMessage(newDeadline);

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(EMPTY_DEADLINE);

        } finally {
            return item;

        }
    }

    public static int addToDo(@NotNull String line, int item, ArrayList tasks) throws IOException {
        try {
            int descriptionIndex = line.indexOf("todo");
            String description = line.substring(descriptionIndex + 5);

            Todo newTodo = new Todo(description);
            tasks.add(newTodo);
            item++;
            displayTaskAddedMessage(newTodo);

        } catch (StringIndexOutOfBoundsException e) {
            // CHANGE TO UI text
            System.out.println(EMPTY_TODO);

        } finally {
            return item;

        }

    }
    public static int deleteTask(String line, int item, ArrayList tasks) {
        try {
            String inputMessageArray[] = new String[2];
            inputMessageArray = line.split(" ");
            int numToDelete = Integer.parseInt(inputMessageArray[1]) - 1;

            // Check if list item number exists in list
            if (numToDelete >= 0 && numToDelete < item) {
                // Delete item from list
                Task deletedTask = (Task) tasks.remove(numToDelete);
                item--;
                displayTaskDeletedMessage(deletedTask);
                displayNumItemsInList(item);

            } else {
                System.out.println("Item number " + (numToDelete + 1) + " does not exist in list");
            }
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println(EMPTY_LISTNUM);
        }
        return item;
    }

    public static void toggleDoneStatus(String line, int item, String msg, boolean status, ArrayList tasks) {
        try {
            String inputMessageArray[] = new String[2];
            inputMessageArray = line.split(" ");
            int numToMark = Integer.parseInt(inputMessageArray[1]) - 1;

            // Check if list item number exists in list
            if (numToMark >= 0 && numToMark < item) {
                // Check if it is already done/not done in list
                if (((Task) tasks.get(numToMark)).getIsDone() != status) {
                    // Update IsDone status
                    ((Task) tasks.get(numToMark)).setIsDone(status);

                    System.out.println(HORIZONTAL + "\n\t" + msg);
                    System.out.println("\n\t\t" + ((Task) tasks.get(numToMark)).getDescription() + "\n");
                    System.out.println(HORIZONTAL);
                } else {
                    System.out.println("No change, task was already as is");
                }
            } else {
                System.out.println("Item number " + (numToMark + 1) + " does not exist in list");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(EMPTY_LISTNUM);

        }
    }

    public static void findTasks (String line, ArrayList tasks) {
        try {
            String inputMessageArray[] = new String[2];
            inputMessageArray = line.split(" ");
            String taskToFind = inputMessageArray[1];

            String taskDescrip;


            System.out.println("Here are the matching tasks in your list: \n");

            for (int i = 0; i < tasks.size(); i++) {
                taskDescrip = ((Task) tasks.get(i)).getPureDescription();
                String taskDescripArray[] = new String[5];
                taskDescripArray = taskDescrip.split(" ");

                for (String word: taskDescripArray) {
                    // if matching word
                    if (word.equals(taskToFind)) {
                        System.out.println("\n\t" + (i + 1) + ". " + ((Task) tasks.get(i)).getDescription() + "\n");
                    }
                }

            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(EMPTY_LISTNUM);

        }

    }


}
