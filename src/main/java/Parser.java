import duke.DukeException;
import java.util.Scanner;

public class Parser {
    private final TaskList tasks;

    public Parser() {
        tasks = new TaskList();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void handleCommands() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!(command.equals("bye"))) {
            try {
                String[] input = command.split(" ", 2); //only check the first word
                String firstWord = input[0];
                switch (firstWord) {
                    //list tasks
                    case "list" :
                        tasks.listTasks();
                        break;
                    //find task
                    case "find" :
                        tasks.findTask(input);
                        break;
                    //mark task
                    case "mark":
                        tasks.markTask(input);
                        break;
                    //un-mark task
                    case "unmark":
                        tasks.unmarkTask(input);
                        break;
                    //delete task
                    case "delete":
                        tasks.deleteTask(input);
                        break;
                    //to-do
                    case "todo":
                        tasks.addTodo(input);
                        break;
                    //deadline
                    case "deadline":
                        tasks.addDeadline(input);
                        break;
                    //event
                    case "event":
                        tasks.addEvent(input);
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