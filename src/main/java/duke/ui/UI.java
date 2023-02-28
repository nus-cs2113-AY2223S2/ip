package duke.ui;

import duke.data.textImage;
import duke.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UI {

    public static void printTodo(String message) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [T][ ] " + message);
    }

    public static void printDeadline(String taskName, String by) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [D][ ] " + taskName + " (by: " + by + ")");
    }

    public static void printEvent(String taskName, String start, String end) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [E][ ] " + taskName + " (from: " + start + " to: " + end + ")");
    }

    public static void printTaskListLength(int taskListLength) {
        System.out.println("Now you have " + taskListLength + " tasks in the list.");
    }

    public static void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());    }
    }

    public static void printMarkMessage(String status, String description) {
        System.out.println("  [" + status + "] " + description);
    }

    public static void printFindInList(ArrayList<Task> tasks, String keyword){
        int index = 1;
        HashMap<Integer, Task> mapList = new HashMap<>();
        System.out.println(textImage.HORIZONTAL_LINE);
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                mapList.put(index, task);
            }
            index++;
        }
        if (mapList.isEmpty()){
            printMessage("No matching tasks in your list");
        } else {
            printMessage("Here are the matching tasks in your list:");
            for (Map.Entry<Integer, Task> entry : mapList.entrySet()) {
                System.out.println(entry.getKey() + "." + entry.getValue());
            }
        }
        System.out.println(textImage.HORIZONTAL_LINE);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void showWelcomeMessage() {
        System.out.println(textImage.WELCOME_TEXT_IMAGE);
        System.out.println(textImage.HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(textImage.HORIZONTAL_LINE);
    }

    public static void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}



