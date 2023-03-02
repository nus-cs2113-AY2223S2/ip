package duke.Commands;

import static duke.Duke.LINE_SPACING;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";
    public void cmd() {
        System.out.println(LINE_SPACING);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(LINE_SPACING);
    }

}
