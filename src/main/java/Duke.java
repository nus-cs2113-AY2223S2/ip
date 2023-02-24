import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    public static ArrayList<Task> addTask(ArrayList<Task> taskList, Task newTask) {
        taskList.add(newTask);
        return taskList;
    }
    
    public static void welcomeMessage() {
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?");
    }
    public static void byeMessage() {
        System.out.println("See ya!");
    }
    public static void list(ArrayList<Task> tasksList) {
        System.out.println("Here are the tasks in your list:");
        int ID = 1;
        for (Task item : tasksList) {
            if (item == null) {
                break;
            }
            System.out.println(ID + "." + printIconStatus(item));
            ID += 1;
        }
    }

    public static void uncheckDoneStatus(String[] stringsList, ArrayList<Task> taskList) {
        if (Integer.parseInt(stringsList[1]) > taskList.size()){
            System.out.println("Item not found!");
        } else {
            taskList.get(Integer.parseInt(stringsList[1]) - 1).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            Task item = taskList.get(Integer.parseInt(stringsList[1]) - 1
            );
            System.out.println(printIconStatus(item));
        }
    }

    public static void checkDoneStatus(String[] stringsList, ArrayList<Task> taskList) {
        if (Integer.parseInt(stringsList[1]) > taskList.size()){
            System.out.println("Item not found!");
        } else {
            taskList.get(Integer.parseInt(stringsList[1]) - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            Task item = taskList.get(Integer.parseInt(stringsList[1]) - 1);
            System.out.println(printIconStatus(item));
        }
    }

    public static String printIconStatus(Task selectedTask) {
        String out = "[" + selectedTask.getIcon() + "][" + selectedTask.getStatusIcon() + "] " + selectedTask.description;
        if (selectedTask instanceof Events){
            out += " (" + selectedTask.getStart() + " " + selectedTask.getEnd() + ")";
        } else if (selectedTask instanceof Deadlines) {
            out += " (" + selectedTask.getDeadline() + ")";
        }
        return out;
    }
    private static ArrayList<Task> addDeadline(ArrayList<Task> taskList, String[] parts) {
        String content = parts[0];
        String dueDate = parts[1].replace("by ", "by: ").trim();
        Deadlines newDeadline = new Deadlines(content, dueDate);
        ArrayList<Task> newTaskList = addTask(taskList, newDeadline);
        System.out.println("Got it. I've added this task");
        System.out.println(printIconStatus(newDeadline));
        System.out.println("Now you have " + newTaskList.size() + " taskList in the list.");
        return newTaskList;
    }

    private static ArrayList<Task> addEvent(ArrayList<Task> taskList, String[] parts) {
        String content = parts[0];
        String start = parts[1].replace("from ", "from: ").trim();
        String end = parts[2].replace("to ", "to: ").trim();
        Events newEvent = new Events(content, start, end);
        ArrayList<Task> newTaskList = addTask(taskList, newEvent);
        System.out.println("Got it. I've added this task");
        System.out.println(printIconStatus(newEvent));
        System.out.println("Now you have " + newTaskList.size() + " taskList in the list.");
        return newTaskList;
    }
    private static ArrayList<Task> addToDo(ArrayList<Task> taskList, String[] inputs) {
        ToDos newToDo = new ToDos(inputs[1]);
        ArrayList<Task> newTaskList = addTask(taskList, newToDo);
        System.out.println("Got it. I've added this task");
        System.out.println(printIconStatus(newToDo));
        System.out.println("Now you have " + newTaskList.size() + " tasks in the list.");
        return newTaskList;
    }

    private static ArrayList<Task> addTask(ArrayList<Task> taskList, String[] parts) {
        Task newTask = new Task(parts[0]);
        ArrayList<Task> newTaskList = addTask(taskList, newTask);
        System.out.println("Task added!");
        return newTaskList;
    }
    private static void invalidCommand() {
        System.out.println("I beg your pardon?");
    }

    public static void main(String[] args) {
        welcomeMessage();
        String input;
        ArrayList<Task> taskList = new ArrayList<>();
        while (true) {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            String[] inputs = input.split(" ", 2);
            String command = inputs[0];
            String[] partsList = (inputs.length > 1) ? inputs[1].split("/") : null;
            switch (command) {
            case "bye" -> {
                byeMessage();
                return;
            }
            case "list" -> list(taskList);
            case "mark" -> checkDoneStatus(inputs, taskList);
            case "unmark" -> uncheckDoneStatus(inputs, taskList);
            case "add" -> taskList = addTask(taskList, partsList);
            case "todo" -> taskList = addToDo(taskList, inputs);
            case "event" -> taskList = addEvent(taskList, partsList);
            case "deadline" -> taskList = addDeadline(taskList, partsList);
            default -> invalidCommand();
            }
        }
    }
}
