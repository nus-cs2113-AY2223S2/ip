package duke.keycommands;

import duke.Common;
import duke.Task;

public class ListCommand {
    public ListCommand() {
        printItems();
    }

    private static void printItems() {
        for (int i = 0; i < Common.tasks.size(); ++i) {
            Task item = Common.tasks.get(i);
            if (item == null) {
                break;
            } else if (item.getClassSymbol().equals("[T]")) {
                System.out.println((i + 1) + "." + item.getClassSymbol()
                        + item.getMarkingStatus() + " " + item.getContent());
            } else if (item.getClassSymbol().equals("[D]")) {
                System.out.println((i + 1) + "." + item.getClassSymbol()
                        + item.getMarkingStatus() + " " + item.getContent() + "(by: " + item.getDate() + ")");
            } else if (item.getClassSymbol().equals("[E]")) {
                System.out.println((i + 1) + "." + item.getClassSymbol()
                        + item.getMarkingStatus() + " " + item.getContent() + "(from: " + item.getBeginDate()
                        + " to: " + item.getEndDate() + ")");
            }
        }
    }
}
