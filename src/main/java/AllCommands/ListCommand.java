package AllCommands;
import Support.*;

// Display all the AllCommands.Commands
public class ListCommand extends Commands {
    public static String line;
    public ListCommand(String line) {
        ListCommand.line = line;
    }

    public static void listCommandMethod(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.print(i + 1);
            System.out.println(". " + tasks.getTask(i).showTask());
        }
        System.out.println("____________________________________________________________");
    }

}