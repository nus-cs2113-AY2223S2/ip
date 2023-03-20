package duke;

import java.io.IOException;

public class MarkCommand extends Command {

    private TaskList arrayLL;
    private Ui ui;
    private Storage store;

    Task task;

    public MarkCommand(Ui ui, Storage store, TaskList arrayLL) {
        this.ui = ui;
        this.store = store;
        this.arrayLL = arrayLL;
    }

    /**
     * If the user inputs the word mark depending on the line the functions marks
     * @param line It contains the input of the line that we want to mark
     * @throws IOException so that the requirements are meant by user input and there is no error
     */

    @Override
    void complete(String line) throws IOException {
        int index = line.indexOf(" ");
        if (!line.equals("mark")){
            String str = line.substring(index + 1);
            int pos = Integer.parseInt(str);
            Task taskMarked = arrayLL.getTask(pos - 1);
            taskMarked.setStatusIcon(true);
            //System.out.printf(" " + "Nice! I've marked this task as done:\n" + "\t" + " " + "[" +
            //        taskMarked.getStatusIcon() + "]" + " " + taskMarked.description + "\n");
            System.out.println("____________________________________________________________\n");
            //editToFile(f.getAbsolutePath(), tasks);
            store.Storedata(taskMarked);

        }else{
            System.out.println(":(( Sorry please drink some coffee and enter valid mark command");
            System.out.println("____________________________________________________________\n");
        }
    }
}
