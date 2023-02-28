package duke.commands;

public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "bye";
    public void execute() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
