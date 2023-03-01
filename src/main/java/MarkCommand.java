import duke.Task;

import java.io.IOException;

public class MarkCommand extends Command{

    private TaskList arrayLL;
    private Ui ui;
    private Storage store;

    Task task;

    public MarkCommand(Ui ui, Storage store, TaskList arrayLL) {
        this.ui = ui;
        this.store = store;
        this.arrayLL = arrayLL;
    }

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

        }else{
            System.out.println(":(( Sorry please drink some coffee and enter valid mark command");
            System.out.println("____________________________________________________________\n");
        }
    }
}
