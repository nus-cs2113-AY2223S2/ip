package duke;

import java.io.IOException;

public class UnMarkCommand extends Command {

    private TaskList arrayLL;
    private Ui ui;
    private Storage stor;

    Task task;

    public UnMarkCommand(Ui ui, Storage stor, TaskList arrayLL) {
        this.ui = ui;
        this.stor = stor;
        this.arrayLL = arrayLL;
    }
    /**
     * If the user inputs the word mark depending on the line the functions unmarks
     * @param line It contains the input of the line that we want to unmark
     * @throws IOException so that the requirements are meant by user input and there is no error
     */

    void complete(String line) throws IOException {
        int index = line.indexOf(" ");
        if (!line.equals("unmark")) {
            String str = line.substring(index + 1);
            int pos = Integer.parseInt(str);
            Task taskUnmarked = arrayLL.getTask(pos - 1);
            taskUnmarked.setStatusIcon(false);
            //System.out.printf("" + "Okay, I've marked this task as not done yet:\n" + "\t" + " " + "[" +
            //        taskUnmarked.getStatusIcon() + "]" + " " + taskUnmarked.description + "\n");
            System.out.println("____________________________________________________________\n");
            //editToFile(f.getAbsolutePath(), tasks);


        } else {
            System.out.println(":(( Sorry please drink some coffee and enter valid unmark command");
            System.out.println("____________________________________________________________\n");
        }
    }
}
