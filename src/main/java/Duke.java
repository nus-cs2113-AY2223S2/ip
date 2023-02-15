import java.util.Scanner;
import java.util.ArrayList;



public class Duke {
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }
    public static void main(String[] args) throws InvalidInputException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input;

        Task task = new Task();
        
        while((input = in.nextLine()) != "") {
            try {

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    in.close();
                    return;
                }
                else if (input.equals("list")) {
                    task.getItems();
                }
                else if (input.startsWith("mark ")) {
                    task.setDone(input);
                }
                else if (input.startsWith("unmark ")) {
                    task.setNotDone(input);
                }
                else if (input.startsWith("todo ")) {
                    
                    new Todo(input);
                }
                else if (input.startsWith("deadline ")) {
                    
                    new Deadline(input);
                }
                else if (input.startsWith("event ")) {
                    
                    new Event(input);
                }
                else if (input.startsWith("delete ")) {
                    
                    task.delete(input);
                }
                else {
                    throw new InvalidInputException();
                }
            }
            catch (InvalidInputException e) {
                System.out.println("Invalid input");
            }
        }
        
    }
}
