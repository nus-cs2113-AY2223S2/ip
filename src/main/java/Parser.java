import duke.DukeException;
import java.util.Scanner;

public class Parser {
    //private TaskList tasks;

    public Parser() {
        //tasks = new TaskList();
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void handleCommands() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!(command.equals("bye"))) {
            try {
                String[] input = command.split(" ", 2); //only check the first word
                String firstWord = input[0];
                switch (firstWord) {
                    //list tasks
                    case "list" :
                        TaskList.listTasks();
                        break;
                    //find task
                    case "find" :
                        TaskList.findTask(input);
                        break;
                    //mark task
                    case "mark":
                        TaskList.markTask(input);
                        break;
                    //un-mark task
                    case "unmark":
                        TaskList.unmarkTask(input);
                        break;
                    //delete task
                    case "delete":
                        TaskList.deleteTask(input);
                        break;
                    //to-do
                    case "todo":
                        TaskList.addTodo(input);
                        break;
                    //deadline
                    case "deadline":
                        TaskList.addDeadline(input);
                        break;
                    //event
                    case "event":
                        TaskList.addEvent(input);
                        break;
                    //unidentified command
                    default:
                        throw new DukeException();
                }
            } catch (DukeException e) {
                printLine();
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means.");
                printLine();
            }
            command = in.nextLine(); //read next command
        }
    }
}