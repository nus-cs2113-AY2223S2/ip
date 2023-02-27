package AllCommands;
import BasisSupport.Spliterator;
import Support.*;

// Display all the AllCommands.Commands
public class ListCommand extends Commands {
    public static String line;
    public ListCommand(String line) {
        ListCommand.line = line;
    }

    /**
     * This method is to deal with the user's request to list all the commands they have input in the storage.
     * Command is only list without any further instruction.
     *
     * @param tasks All the tasks we have in the storage
     */

    public static void listCommandMethod(TaskList tasks) {
        Spliterator.printSpliterator();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.print(i + 1);
            System.out.println(". " + tasks.getTask(i).showTask());
        }
        Spliterator.printSpliterator();
    }

}