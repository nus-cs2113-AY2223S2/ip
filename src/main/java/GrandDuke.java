import java.util.Scanner;  // Import the Scanner class

public class GrandDuke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void println() {
        System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    public static void output(String message) {
        System.out.print("        ");
        System.out.println(message);
    }         
    public static void output_wl(String message) {
        println();
        output(message);
        println();
    }                 
    public static void greet() {
        output_wl("Hello! I'm GrandDuke\n" + "        What can I do for you?");
    }
    public static void exit() {
        output_wl("Bye. Hope to see you again soon!");
    }

    public static void parseCommand(String input) {
        String[] commands = input.split(" ");
        try{
            switch(commands[0]) {
                case "":
                    output_wl("Input a task");
                    break;
    
    
                case "bye":
                    exit();
                    System.exit(0);
                    break;
    
    
                case "list":
                    println();
                    output("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        output(Integer.toString(i + 1) + "." + tasks[i].getTaskPrint());
                    }
                    println();
                    break;
    
    
                case "mark":
                    if (Integer.parseInt(commands[1]) <= taskCount) {
                        tasks[Integer.parseInt(commands[1]) - 1].setDone(true);
                    } else {
                        output_wl("List number provided exceeds total tasks");
                    }
                    break;
                    
    
                case "unmark":  
                    if (Integer.parseInt(commands[1]) <= taskCount) {
                        tasks[Integer.parseInt(commands[1]) - 1].setDone(false);
                    } else {
                        output_wl("List number provided exceeds total tasks");
                    }
                    break;
    
    
                default:
                    Task newTask = new Task(input);
                    tasks[taskCount++] = newTask;
                    output_wl("___" + input + "___ has been added to your list\n");
    
            }
        } catch(Exception e) {
            output_wl("Wrong format of input, please try again");
        }
    }
    public static void main(String[] args) {
        String logo = "  _____                     _______       _        \n"
        + "|  __ \\                   | |  _  \\     | |       \n"
        + "| |  \\/_ __ __ _ _ __   __| | | | |_   _| | _____ \n"
        + "| | __| '__/ _` | '_ \\ / _` | | | | | | | |/ / _ \\ \n"
        + "| |_\\ \\ | | (_| | | | | (_| | |/ /| |_| |   <  __/\n"
        + " \\____/_|  \\__,_|_| |_|\\__,_|___/  \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        
        greet();
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        output_wl("Enter your Tasks/Commands Here: ");
        while (true) {
            String command = input.nextLine();
            parseCommand(command);
        }
    }
}
