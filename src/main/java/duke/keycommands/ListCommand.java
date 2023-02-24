package duke.keycommands;

import duke.Common;
import duke.tasktypes.Task;

public class ListCommand {
    public ListCommand() {
        printItems();
    }

    private static void printItems() {
        for (int i = 0; i < Common.tasks.size(); ++i) {
            Task item = Common.tasks.get(i);
            if (item == null) {
                break;
            } else {
                System.out.println((i + 1) + "." + item.printTask());
            }
        }
    }
}
