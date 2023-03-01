package Commands;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import java.util.ArrayList;
import static Commands.Text.line;
import java.io.FileWriter;
import java.io.IOException;

public class command {

    public static void printList(ArrayList<Task> taskList) {
        //checks if list is empty first
        if (taskList.isEmpty()) {
            error.listIsEmpty();
            error.invalidCommand();
        } else {
            System.out.println("Here are the tasks in your list:\n");
            for (Task item : taskList) {
                System.out.print((taskList.indexOf(item) + 1) + ".");
                System.out.println(item.toString());
            }
            System.out.println(line);
        }
    }

    public static void deleteItem(String userInput, ArrayList<Task> taskList) {
        int itemNumber = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
        //to check if an item is within the index of the list
        if (itemNumber >= taskList.size()) {
            error.indexNotFound();
        } else {
            System.out.println(line);
            System.out.println("Noted. I've removed this task:\n" + taskList.get(itemNumber).getSymbol()
                    + taskList.get(itemNumber).getStatusIcon() + taskList.get(itemNumber).getDescription() + '\n');
            taskList.remove(itemNumber);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            System.out.println(line);
        }
    }

    public static void markItem(String userInput, ArrayList<Task> taskList) {
        int itemNumber = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
        //to check if an item is within the index of the list
        if (itemNumber >= taskList.size()){
            error.indexNotFound();
        } else {
            taskList.get(itemNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + taskList.get(itemNumber).getStatusIcon() + taskList.get(itemNumber).getDescription());
            System.out.println(line);
        }
    }

    public static void unMarkItem(String userInput, ArrayList<Task> taskList) {
        int itemNumber = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
        //to check if an item is within the index of the list
        if (itemNumber >= taskList.size()) {
            error.indexNotFound();
        } else {
            taskList.get(itemNumber).markAsUnDone();
            System.out.println("OK, I've marked this task as not done yet:\n" + "    "
                    + taskList.get(itemNumber).getStatusIcon() + taskList.get(itemNumber).getDescription());
            System.out.println(line);
        }
    }

    public static void createEvent(String userInput, ArrayList<Task> taskList){
        String[] userInputParts;
        userInput = userInput.replace("event","");
        userInputParts = userInput.split("/");
        Event event = new Event(userInputParts[0], userInputParts[1], userInputParts[2]);
        taskList.add(event);
        System.out.println("Got it. I've added this task: ");
        System.out.println(event.getSymbol() + event.getStatusIcon() + event.getDescription());
        System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
        System.out.println(line);
    }

    public static void createDeadline(String userInput, ArrayList<Task> taskList){
        String[] userInputParts;
        userInput = userInput.replace("deadline","");
        userInputParts = userInput.split("/");
        Deadline deadline = new Deadline(userInputParts[0], userInputParts[1]);
        taskList.add(deadline);
        System.out.println(deadline.getSymbol() + deadline.getStatusIcon() + deadline.getDescription());
        System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
        System.out.println(line);
    }

    public static void createToDo(String userInput, ArrayList<Task> taskList){
        userInput = userInput.replace("todo ","");
        String dummy = userInput.replace(" ","");
        //checks if description is empty and throws warning back to user
        if (dummy.isEmpty()){
            error.emptyDescription();
        } else {
            Task task = new Task(userInput);
            taskList.add(task);
            System.out.println("Got it. I've added this task: ");
            System.out.println(task.getSymbol() + task.getStatusIcon() + " " + task.getDescription());
            System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
            System.out.println(line);
        }
    }
}
