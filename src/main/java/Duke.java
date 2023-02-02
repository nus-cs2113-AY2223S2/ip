import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    //Definition lists //
    public static void printMargin(){
        String MARGIN = "*----------------------------*";
        System.out.println(MARGIN);
    }
    public static void endProgram(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void welcomeMessage(){
        String logo = "┊┊┊┊┊╭╭╭╮╮╮┊┊┊┊ \n" +
                "┊┊┊┊┊╰╰╲╱╯╯┊┊┊┊ \n" +
                "┊┏╮╭┓╭━━━━━━╮┊┊ \n" +
                "┊╰╮╭╯┃┈┈┈┈┈┈┃┊┊ \n" +
                "┊┊┃╰━╯┈┈╰╯┈┈┃┊┊ \n" +
                "┊┊┃┈┈┈┈┈┈┈╰━┫┊┊ \n" +
                "╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲\n" ;

        System.out.println(logo);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
    }

    // Create array to store the tasks
    public static ArrayList<Task> tasksArray = new ArrayList<>();

    // Counter to track how many tasks in taskList
    public static int taskCount = 0;

    // Run program here:
    public static void main(String[] args) {

        printMargin();
        welcomeMessage();
        printMargin();

        Scanner input = new Scanner(System.in);
        String userCommand;

        do{
            userCommand = input.nextLine();

            enter(userCommand);
        }
        while(!userCommand.equals("bye"));

        }

    // How the program runs based on user's input commands
    public static void enter(String userCommand) {
        // Split user's input into individual words for easier index access
        String[] wordSeparator = userCommand.split(" ");
        // Identify first word in the user's input and execute corresponding command
        String KEYWORD = wordSeparator[0];

        switch (KEYWORD) {
        case "bye":        //Exit the program
            printMargin();
            endProgram();
            printMargin();
            break;

        case "list":       // View taskList
            printMargin();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasksArray.size(); i++) {
                System.out.println(Integer.toString(i + 1) + ". " + tasksArray.get(i).toString() );
            }
            printMargin();
            break;

        case "mark":       // Mark task as done
            int taskToMark = Integer.parseInt(wordSeparator[1]) - 1;
            printMargin();
            System.out.println("Nice! I've marked this task as done:");
            tasksArray.get(taskToMark).setDone(true);
            System.out.println("  " +  tasksArray.get(taskToMark).getStatusIcon()  + tasksArray.get(taskToMark).description);
            printMargin();
            break;


        case "unmark":      // unmark task
            int taskToUnmark = Integer.parseInt(wordSeparator[1]) - 1;
            printMargin();
            System.out.println("OK, I've marked this task as not done yet:");
            tasksArray.get(taskToUnmark).setDone(false);
            System.out.println("  " +  tasksArray.get(taskToUnmark).getStatusIcon()  + tasksArray.get(taskToUnmark).description);
            printMargin();
            break;



        case "todo":        // add todo task
            // Get the task todo
            String taskToDo = userCommand.substring(4,userCommand.length());
            Todo todoTask = new Todo(taskToDo);

            printMargin();
            System.out.println("Got it. I've added this task:");
            System.out.println("  "+ todoTask);
            taskCount++ ;
            System.out.println("Now you have "+ taskCount + " tasks in the list.");
            printMargin();

            tasksArray.add(todoTask);
            break;


        case "deadline":    // add deadline task
            // Filter task and due date from user' input
            int filterIdx = userCommand.indexOf('/');
            // Get task from user's input
            String deadlineTask = userCommand.substring(8,filterIdx-1);
            // Get due date from user's input
            String dueBy = userCommand.substring(filterIdx+3,userCommand.length());
            Deadline d = new Deadline(deadlineTask, dueBy);

            printMargin();
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + d);
            taskCount++ ;
            System.out.println("Now you have "+ taskCount + " tasks in the list.");
            printMargin();

            tasksArray.add(d);
            break;

        case "event":       // add event task
            // Filter task and due date from user' input
            int filterEventIdx = userCommand.indexOf('/');
            int filterEventIdx2 = userCommand.lastIndexOf('/');
            // Get task from user's input
            String eventTask = userCommand.substring(5,filterEventIdx-1);
            // Get event start time
            String startTime = userCommand.substring(filterEventIdx+5,filterEventIdx2-1);
            // Get event end time
            String endTime = userCommand.substring(filterEventIdx2+3,userCommand.length());
            // Get duration of event
            String eventDuration = startTime + " " + endTime;
            Event e = new Event(eventTask,startTime, endTime);

            printMargin();
            System.out.println("Got it. I've added this task:");
            System.out.println("  "+ e);
            taskCount++ ;
            System.out.println("Now you have "+ taskCount + " tasks in the list.");
            printMargin();

            tasksArray.add(e);
            break;

        default:            // adding generic tasks
            Task t = new Task(userCommand) ;
            tasksArray.add(t);
            taskCount++;
            printMargin();
            System.out.println("added: " + t.description);
            printMargin();
            break;

        }

    }

}
