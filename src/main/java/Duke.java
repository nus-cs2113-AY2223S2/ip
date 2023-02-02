import java.util.Arrays;
import java.util.Scanner;
public class Duke {

    public static Task[] addTask(Task[] tasks, Task task){
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = task;
        return tasks;
    }

    public static String joinString(String[] strings, int idx){
        String joinedString = "";
        for (int i = idx; i < strings.length; i++){
            if (strings[i] == null){
                break;
            } else {
                joinedString += strings[i] + " ";
            }
        }
        return joinedString.trim();
    }
    public static void welcome(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }
    public static void bye(){
        System.out.println("Adios!");
    }
    public static void list(Task[] arr){
        System.out.println("Here are the tasks in your list:");
        int ID = 1;
        for (Task tsk : arr) {
            if (tsk==null){break;}
            System.out.println(ID + "." + printIconStatus(tsk));
            ID += 1;
        }
    }

    public static void unmark(String[] strings, Task[] tasks){
        if (Integer.parseInt(strings[1]) > tasks.length){
            System.out.println("Item not found!");
        } else {
            tasks[Integer.parseInt(strings[1]) - 1].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            Task tsk = tasks[Integer.parseInt(strings[1]) - 1];
            System.out.println(printIconStatus(tsk));
        }
    }

    public static void mark(String[] strings, Task[] tasks){
        if (Integer.parseInt(strings[1]) > tasks.length){
            System.out.println("Item not found!");
        } else {
            tasks[Integer.parseInt(strings[1]) - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            Task tsk = tasks[Integer.parseInt(strings[1]) - 1];
            System.out.println(printIconStatus(tsk));
        }
    }

    public static String printIconStatus(Task tsk){
        // if (tsk instanceof [Class]) to check if tsk is of a certain class
        String out = "[" + tsk.getIcon() + "][" + tsk.getStatusIcon() + "] " + tsk.description;
        if (tsk instanceof Events){
            out += " (" + tsk.getStart() + " " + tsk.getEnd() + ")";
        } else if (tsk instanceof  Deadlines) {
            out += " (" + tsk.getDeadline() + ")";
        }
        return out;
    }
    public static void main(String[] args) {
        welcome();
        String input;
        Task[] tasks = new Task[0];
        int lstID = 1;
        while (true) {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            String[] inputs = input.split(" ");
            String[] parts = input.split("/");
            switch(inputs[0]){
            case "bye":
                bye();
                return;
            case "list":
                list(tasks);
                break;
            case "mark":
                mark(inputs, tasks);
                break;
            case "unmark":
                unmark(inputs, tasks);
                break;
            case "add":
                Task tsk = new Task(joinString(inputs, 2));
                tasks = addTask(tasks, tsk);
                System.out.println("Task added!");
                break;
            case "todo":
                ToDos todo = new ToDos(joinString(inputs, 1));
                tasks = addTask(tasks, todo);
                System.out.println("Got it. I've added this task");
                System.out.println(printIconStatus(todo));
                System.out.println("Now you have " + tasks.length + " tasks in the list.");
                break;
            case "event":
                parts[0] = parts[0].replace("event ", "").trim();
                parts[1] = parts[1].replace("from ", "from: ").trim();
                parts[2] = parts[2].replace("to ", "to: ").trim();
                Events event = new Events(parts[0], parts[1], parts[2]);
                tasks = addTask(tasks, event);
                System.out.println("Got it. I've added this task");
                System.out.println(printIconStatus(event));
                System.out.println("Now you have " + tasks.length + " tasks in the list.");
                break;
            case "deadline":
                parts[0] = parts[0].replace("deadline ", "").trim();
                parts[1] = parts[1].replace("by ", "by: ").trim();
                Deadlines deadline = new Deadlines(parts[0], parts[1]);
                tasks = addTask(tasks, deadline);
                System.out.println("Got it. I've added this task");
                System.out.println(printIconStatus(deadline));
                System.out.println("Now you have " + tasks.length + " tasks in the list.");
                break;
            default:
                System.out.println("Command not recognised! Try again!");
            }
        }
    }
}
