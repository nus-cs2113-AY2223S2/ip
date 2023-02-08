import java.util.Scanner;

public class Duke {
    public static final String DIVIDER = "____________________________________________________________\n";
    
    public static void main(String[] args) {
        greet();
        
        Tasks list = new Tasks();
        readCommandLine(list);
        
        exit();
    }
    
    public static void greet() {
        String greet = DIVIDER +
                "Hello! I'm Bob\n" +
                "What can I do for you?\n" +
                DIVIDER;
        System.out.println(greet);
    }
    
    private static void readCommandLine(Tasks list) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        
        // continuously reads input from command line until command 'bye' is inputted
        while (!line.equalsIgnoreCase("bye")) {
            System.out.print(DIVIDER);
            runCommand(list, line);
            System.out.println(DIVIDER);
            
            line = in.nextLine();
        }
    }
    
    private static void runCommand(Tasks list, String line) {
        String[] commandAndArg = splitCommandAndArgs(line);
        String command = commandAndArg[0];
        String arg = commandAndArg[1];
        
        switch(command) {
        case "list":
            list.printList();
            break;
        case "help":
            printHelp();
            break;
        case "mark":
            runMark(list, arg);
            break;
        case "unmark":
            runUnmark(list, arg);
            break;
        case "todo":
            addTodo(list, arg);
            break;
        case "event":
            addEvent(list, arg);
            break;
        case "deadline":
            addDeadline(list, arg);
            break;
        default:
            printUnknownCommandMessage();
            printHelp();
            break;
        }
    }
    
    private static String[] splitCommandAndArgs(String line) {
        final String[] splitStrings = line.split(" ", 2);
        return splitStrings.length == 2 ? splitStrings : new String[]{splitStrings[0], ""};
    }
    
    private static void printHelp() {
        // TODO
        System.out.println("list, todo, event, deadline, mark, unmark");
    }
    
    private static void runMark(Tasks list, String arg) {
        int taskNumber = Integer.parseInt(arg);
        if (taskNumber <= list.getTasksCount()) {
            list.markTaskDone(taskNumber);
        } else {
            System.out.println("Error: task number given out of range");
        }
    }
    
    private static void runUnmark(Tasks list, String arg) {
        int taskNumber = Integer.parseInt(arg);
        if (taskNumber <= list.getTasksCount()) {
            list.markTaskUndone(taskNumber);
        } else {
            System.out.println("Error: task number given out of range");
        }
    }
    
    private static void addTodo(Tasks list, String arg) {
        Todo newTodo = new Todo(arg);
        list.addTask(newTodo);
    }
    
    private static void addEvent(Tasks list, String arg) {
        String[] descriptionFromAndTo = splitEventArg(arg);
        String description = descriptionFromAndTo[0];
        String from = descriptionFromAndTo[1];
        String to = descriptionFromAndTo[2];
        
        Event newEvent = new Event(description, from, to);
        list.addTask(newEvent);
    }
    
    private static String[] splitEventArg(String arg) {
        String[] splitDescription = arg.split("/from", 2); // separate the argument into description and fromAndTo
        String[] splitFromAndTo = splitDescription[1].split("/to", 2); // separate fromAndTo into from and to
        return new String[] {splitDescription[0].trim(), splitFromAndTo[0].trim(), splitFromAndTo[1].trim()};
    }
    
    private static void addDeadline(Tasks list, String arg) {
        String[] descriptionAndBy = splitDeadlineArg(arg);
        String description = descriptionAndBy[0];
        String by = descriptionAndBy[1];
        
        Deadline newDeadline = new Deadline(description, by);
        list.addTask(newDeadline);
    }
    
    private static String[] splitDeadlineArg(String arg) {
        String[] splitDescriptionAndBy = arg.split("/by", 2);
        return new String[] {splitDescriptionAndBy[0].trim(), splitDescriptionAndBy[1].trim()};
    }
    
    private static void printUnknownCommandMessage() {
        System.out.println("Unknown command detected, please use the following commands only");
    }
    
    public static void exit() {
        String exit = DIVIDER +
                "Bye. Hope to see you again soon!\n" +
                DIVIDER;
        System.out.println(exit);
    }
}
