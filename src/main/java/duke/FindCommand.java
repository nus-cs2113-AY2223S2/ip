package duke;
import java.io.IOException;

public class FindCommand extends Command{

    private TaskList arrayLL;
    private Ui ui;
    private Storage store;

    Task task;

    public FindCommand(Ui ui, Storage store, TaskList arrayLL) {
        this.ui = ui;
        this.store = store;
        this.arrayLL = arrayLL;
    }

    @Override
    void complete(String line) throws IOException {
        int index = line.indexOf(" ");
        if (!line.equals("find")) {
            String str = line.substring(index + 1);
            System.out.println("Here are the matching tasks in your list:");
            for(int i =0; i<arrayLL.arrayL.size(); i++) {
                String tempii = String.valueOf(arrayLL.getTask(i));
                if (tempii.contains(str)) {
                    System.out.println(tempii);
                }
            }
        }
    }
}