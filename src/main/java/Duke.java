import java.util.Scanner;

public class Duke {
    
    public static void greet() {
        String greet = "____________________________________________________________\n" +
                "Hello! I'm Bob\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greet);
    }
    
    public static void exit() {
        String exit = "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(exit);
    }
    public static void main(String[] args) {
        greet();
        Tasks list = new Tasks();
        
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        
        while (!line.equalsIgnoreCase("bye")) {
            String horizontalLine = "____________________________________________________________\n";
            System.out.print(horizontalLine);
    
            runCommand(list, line);
    
            System.out.println(horizontalLine);
            line = in.nextLine();
        }
        
        exit();
    }
    
    private static void runCommand(Tasks list, String line) {
        if (line.equalsIgnoreCase("list")) {
            list.printList();
        } else if (line.equalsIgnoreCase("help")) {
            printHelp();
        } else if (line.startsWith("mark ")) {
            String[] words = line.split(" ");
            list.markTaskDone(Integer.parseInt(words[1]));
        } else if (line.startsWith("unmark ")) {
            String[] words = line.split(" ");
            list.markTaskUndone(Integer.parseInt(words[1]));
        } else if (line.startsWith("todo ")) {
            list.addTask(new Todo(line.substring(4)));
        } else if (line.startsWith("event ")) {
            String[] terms = line.split("/");
            String description = terms[0].substring(5);
            list.addTask((new Event(description, terms[1].substring(5), terms[2].substring(4))));
        } else if (line.startsWith("deadline ")) {
            String[] terms = line.split("/");
            String description = terms[0].substring(8);
            list.addTask(new Deadline(description, terms[1].substring(3)));
        } else {
            // to change to error
            System.out.println("Error: invalid command, below are the commands available");
            printHelp();
        }
    }
    
    private static void printHelp() {
        // TODO
        System.out.println("list, todo, event, deadline, mark, unmark");
    }
}
