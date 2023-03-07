import java.io.IOException;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class TaskList{
    public static ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }

    /**
     * Deletes the task from the database based on the user input.
     * If task is invalid, prints invalid task message.
     * @param listOfTasks Arraylist containing tasks.
     * @param words String array containing input.
     */
    private static void removeTask(ArrayList<Task> listOfTasks, String[] words) {
        int number = Integer.parseInt(words[1]);
        if (number <= 0 || words.length == 1 || !isInt(words[1]) || listOfTasks.size() < number) {
            Duke.ui.showDeletingInvalidTaskError();
        } else {
            listOfTasks.get(number - 1).deleteTask();
            listOfTasks.remove(number - 1);
            --Task.taskCount;
            String addedString = getUpdatedFileContents(listOfTasks);
            try {
                Storage.writeToFile(addedString);
            } catch (IOException e) {
                Duke.ui.showDeletingTaskFromDatabaseError();
            }
        }
    }

    /**
     * Determines if the string is an integer.
     * @param str Str is the string we are checking
     * @return True if str is an integer, false otherwise
     * @catch NumberFormatException If str cannot be converted into integer
     */
    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Sums up the string that is to be inserted into databse txt file.
     * @param listOfTasks Arraylist containing tasks
     * @return the string to be added into the database txt file.
     */
    private static String getUpdatedFileContents(ArrayList<Task> listOfTasks) {
        String addedString = "";
        for (Task curr : listOfTasks) {
            String type = null;
            if (curr instanceof ToDo) {
                type = "T";
            } else if (curr instanceof Event) {
                type = "E";
            } else if (curr instanceof Deadline) {
                type = "D";
            }
            String currStr = curr.description + " --- " + type + " |" + curr.getStatusIcon() + "|\n";
            addedString += currStr;
        }
        return addedString;
    }

    /**
     * Deletes the task from the database based on the user input.
     * If task is invalid, prints invalid task message.
     * @param listOfTasks Arraylist containing tasks.
     * @param words String array containing input.
     */
    public static void removeTask(ArrayList<Task> listOfTasks, String[] words, Ui ui) {
        int number = Integer.parseInt(words[1]);
        if (number <= 0 || words.length == 1 || !isInt(words[1]) || listOfTasks.size() < number) {
            ui.showDeletingInvalidTaskError();
        } else {
            listOfTasks.get(number - 1).deleteTask();
            listOfTasks.remove(number - 1);
            --Task.taskCount;
            String addedString = getUpdatedFileContents(listOfTasks);
            try {
                Storage.writeToFile(addedString);
            } catch (IOException e) {
                ui.showDeletingTaskFromDatabaseError();
            }
        }
    }

    /**
     * Unmarks a valid task given by the user to indicate that the task has not completed.
     * If task is invalid, prints out invalid task message.
     * @param listOfTasks Arraylist containing tasks
     * @param words String array containing input
     */
    public static void unmarkTask(ArrayList<Task> listOfTasks, String[] words, Ui ui) {
        int number = Integer.parseInt(words[1]);
        if (number <= 0 || words.length == 1 || !isInt(words[1]) || listOfTasks.size() < number) {
            ui.showUnmarkingInvalidTaskError();
        } else {
            listOfTasks.get(number - 1).unmarkDone();
            System.out.println(" " + listOfTasks.get(number - 1).getStatusIcon() + " " + listOfTasks.get(number - 1).description);
            String addedString = getUpdatedFileContents(listOfTasks);
            try {
                Storage.writeToFile(addedString);
            } catch (IOException e) {
                ui.showUnmarkingTaskInDatabaseError();
            }
        }
    }

    /**
     * Marks a valid task given by the user to indicate that the task has been completed.
     * If task is invalid, print out invalid task message.
     * @param listOfTasks Arraylist containing tasks
     * @param words String array containing input
     */
    public static void markTask(ArrayList<Task> listOfTasks, String[] words) {
        int number = Integer.parseInt(words[1]);
        if (number <= 0 || words.length == 1 || !isInt(words[1]) || listOfTasks.size() < number) {
            Duke.ui.showMarkingInvalidTaskError();
        } else {
            listOfTasks.get(number - 1).markAsDone();
            System.out.println("  " + listOfTasks.get(number - 1).getStatusIcon() + " " + listOfTasks.get(number - 1).description);
            String addedString = getUpdatedFileContents(listOfTasks);
            try {
                Storage.writeToFile(addedString);
            } catch (IOException e) {
                Duke.ui.showMarkingTaskInDatabaseError();
            }
        }
    }

    /**
     * Prints out every task within listOfTasks.
     * If list is empty, print out empty list message.
     * @param listOfTasks Arraylist containing tasks
     */
    public static void printList(ArrayList<Task> listOfTasks) {
        Duke.ui.showListHeaderMessage();
        if (listOfTasks.size() == 0) {
            Duke.ui.showEmptyListMessage();
            return;
        }
        int index = 0;
        for (Task x : listOfTasks) {
            System.out.println(" " + (index + 1) + "." + x.statusMessage());
            ++index;
        }
    }
    /**
     * Find the corresponding tasks depending on user input
     * @param taskList taskList contains the arraylist that is needed to find the corresponding tasks
     * @param words String array containing input
     */
    public static void findTask(TaskList taskList, String[] words) {
        ArrayList<Task> filteredList = (ArrayList<Task>) taskList.listOfTasks.stream()
                .filter(t -> t.description.contains(words[1]))
                .collect(toList());
        System.out.println(" Here are the matching tasks in your list:");
        int index = 0;
        for (Task x : filteredList) {
            System.out.println(" " + (index + 1) + "." + x.statusMessage());
            ++index;
        }
    }
}
