package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.util.ArrayList;

public class UI {
    protected String ui;

    public UI(String ui) {
        this.ui = ui;
    }

    public static void taskPrinter(ArrayList<Task> taskArray, int index) {
        if (taskArray.get(index) instanceof ToDo) {
            System.out.println(index + ". " + ((ToDo) taskArray.get(index)).getToDo() + " " + taskArray.get(index).getDoneStatus()
                    + " " + taskArray.get(index).getDescription());
        }
        if (taskArray.get(index) instanceof Deadline) {
            System.out.println(index + ". " + ((Deadline) taskArray.get(index)).getDeadline() + " " + taskArray.get(index).getDoneStatus()
                    + " " + taskArray.get(index).getDescription() + " (" + ((Deadline) taskArray.get(index)).getDate() + ")");
        }
        if (taskArray.get(index) instanceof Event) {
            System.out.println(index + ". " + ((Event) taskArray.get(index)).getEvent() + " " + taskArray.get(index).getDoneStatus()
                    + " " + taskArray.get(index).getDescription() + " (" + ((Event) taskArray.get(index)).getStartAndEnd() + ")");
        }
    }

    public static void listPrinter(ArrayList<Task> taskArray, int taskIndex) {
        for (int j = 0; j < taskIndex; ++j) {
            taskPrinter(taskArray, j);
        }
    }

    public static void printer(String ui, ArrayList<Task> taskArray, int taskIndex) {
        switch (ui) {
        case "welcome":
            System.out.println("Good day. YodaBot, I am.");
            System.out.println("Assistance, you need?");
            break;
        case "bye":
            System.out.println("See you soon, I hope. Goodbye.");
            break;
        case "empty":
            System.out.println("Empty, list is.");
            break;
        case "list":
            System.out.println("As shown, list is:");
            listPrinter(taskArray, taskIndex);
            break;
        case "marked":
            System.out.println("Marked it, I have:");
            taskPrinter(taskArray, taskIndex);
            break;
        case "unmarked":
            System.out.println("Unmarked it, I have:");
            taskPrinter(taskArray, taskIndex);
            break;
        case "notInList":
            System.out.println("In list, it is not.");
            break;
        case "addTask":
            System.out.println("Added, I have:");
            taskPrinter(taskArray, taskIndex);
            break;
        case "delete":
            System.out.println("Deleted, I have");
            break;
        case "wrongTodo":
            System.out.println("Error: To do what, I ask?");
            break;
        case "generalError":
            System.out.println("Understand, I do not.");
            break;
        default:
            break;
        }
    }
}
