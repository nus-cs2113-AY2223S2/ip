import java.util.Scanner;
import java.util.ArrayList;
import java.security.InvalidParameterException;
import java.util.MissingFormatArgumentException;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (true) {
            String userInput = in.nextLine();
            processUserInput(tasks, userInput);
        }
    }
    private static String[] splitActionAndDescription(String userInput) {
        String[] actionAndDescription = userInput.split(" ", 2);
        return actionAndDescription.length == 2 ? actionAndDescription : new String[]{actionAndDescription[0], ""};
    }
    private static void processUserInput(ArrayList<Task> tasks, String userInput) {
        String[] actionAndDescription = splitActionAndDescription(userInput);
        String action = actionAndDescription[0];
        String description = actionAndDescription[1];
        switch (action) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            break;
        case "list":
            executeListAction(tasks);
            break;
        case "mark":
            executeMarkAction(tasks, description);
            break;
        case "unmark":
            executeUnmarkAction(tasks, description);
            break;
        case "todo":
            executeTodoAction(tasks, description);
            break;
        case "event":
            executeEventAction(tasks, description);
            break;
        case "deadline":
            executeDeadlineAction(tasks, description);
            break;
        case "delete":
            executeDeleteAction(tasks, description);
            break;
        default:
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    private static void executeListAction(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.println(tasks.get(i).toString());
        }
    }
    private static void executeMarkAction(ArrayList<Task> tasks, String description) {
        Integer index = Integer.parseInt(description) - 1;
        tasks.get(index).isCompleted = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
    }
    private static void executeUnmarkAction(ArrayList<Task> tasks, String description) {
        Integer index = Integer.parseInt(description) - 1;
        tasks.get(index).isCompleted = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index).toString());
    }
    private static void printAcknowledgement(ArrayList<Task> tasks) {
        int lastIndexOfTasks = tasks.size() - 1;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(lastIndexOfTasks).toString());
        if (tasks.size() == 1){
            System.out.println("Now you have " + tasks.size()+ " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
    private static void executeTodoAction(ArrayList<Task> tasks, String description) {
        try {
            addTodoToList(tasks, description);
            printAcknowledgement(tasks);
        } catch (Exception e){
            System.out.println("Unable to add todo: No tasks given.");
        }
    }
    private static void addTodoToList(ArrayList<Task> tasks, String description) throws DukeException {
        if(description==""){
            throw new DukeException(new IllegalArgumentException());
        }
        tasks.add(new Todo(description));
    }
    private static void executeEventAction(ArrayList<Task> tasks, String description) {
        try {
            String[] indexArr = splitDescriptionEvent(description);
            String name = indexArr[0].trim();
            String fromDate = indexArr[1].substring(5).trim();
            String toDate = indexArr[2].substring(3).trim();
            tasks.add(new Event(description, fromDate, toDate));
            printAcknowledgement(tasks);
        }catch (DukeException e){
            System.out.println("Not enough commands to execute \"event\"");
        }
    }
    private static String[] splitDescriptionEvent(String description) throws DukeException {
        String[] indexArr = description.split("/", 3);
        if(indexArr.length < 3){
            throw new DukeException(new IllegalArgumentException());
        }
        return indexArr;
    }
    private static void executeDeadlineAction(ArrayList<Task> tasks, String description) {
        try {
            String[] indexArr = splitDescriptionDeadline(description);
            String name = indexArr[0].trim();
            String byDate = indexArr[1].substring(3).trim();
            tasks.add(new Deadline(description, byDate));
            printAcknowledgement(tasks);
        }catch(DukeException e){
            System.out.println("Not enough commands to execute \"deadline\"");
        }
    }
    private static String[] splitDescriptionDeadline(String description) throws DukeException {
        String[] indexArr = description.split("/", 2);
        if(indexArr.length < 2){
            throw new DukeException(new IllegalArgumentException());
        }
        return indexArr;
    }
    private static void executeDeleteAction(ArrayList<Task>tasks, String description) {
        Integer index = Integer.parseInt(description) - 1;
        String deletedTask = tasks.get(index).name;
        tasks.remove((int)index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        if (tasks.size() == 1){
            System.out.println("Now you have " + tasks.size()+ " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
}