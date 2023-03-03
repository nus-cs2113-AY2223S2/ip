package duke.Commands;

import duke.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String term;
    public FindCommand(String term) {
        this.term = term;
    }
    public void cmd() {
        Ui ui = new Ui();
        ui.showLine();
        if (tasks.getSize() == 0) {
            System.out.println("There are no tasks to search.");
        } else {
            System.out.println("\t Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.getSize(); i++) {
                if (tasks.getTask(i).getDescription().contains(this.term)) {
                    System.out.println(tasks.getTask(i));
                }
            }
        }

    }

}
