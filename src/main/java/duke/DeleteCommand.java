package duke;

import java.io.IOException;

public class DeleteCommand extends Command {
    private TaskList arrayLL;
    private Ui ui;
    private Storage store;

    Task task;

    public DeleteCommand(Ui ui, Storage store, TaskList arrayLL) {
        this.ui = ui;
        this.store = store;
        this.arrayLL = arrayLL;
    }
    /**
     * This function deletes the command depending on the line input by the user
     * @param line it is the line input by the user telling delete and which line
     */

    void complete(String line) throws IOException {
        int index = line.indexOf(" ");
        if (!line.equals("delete")) {
            String str = line.substring(index + 1);
            int pos = Integer.parseInt(str);
            System.out.println("Noted! The task has been removed \n");
            System.out.println("\t" + arrayLL.getTask(pos - 1));
            arrayLL.delete(pos - 1);
            //System.out.println("You have " + arrayLL.size() + " number of tasks in you list.");
            System.out.println("____________________________________________________________\n");
            //editToFile(f.getAbsolutePath(), tasks);
        }
    }
}
