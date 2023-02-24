package duke.keycommands;
import duke.Common;
import duke.Task;

import java.io.IOException;

public class DeleteCommand {
    private String userInput;
    private String[] separatedKeyWordAndContent;
    private static final String REMOVE_MESSAGE = "Noted. I've removed this task:";
    public DeleteCommand(String userInput, String[] separatedKeyWordAndContent) {
        this.userInput = userInput;
        this.separatedKeyWordAndContent = separatedKeyWordAndContent;
        doDeleteCommand();
    }

    public void doDeleteCommand() {
        if (userInput.split(" ").length != 2) {
            System.out.println(Common.INSTRUCTION + "\n"
                    + "delete: Number");
        } else {
            deleteTask(separatedKeyWordAndContent);
        }
    }

    private static void deleteTask(String[] seperatedWords) {
        try {
            int taskNumber = Integer.parseInt(seperatedWords[1]);
            if (taskNumber > Common.tasks.size()) {
                System.out.println(Common.BIG_NUMBER);
            } else {
                Task item = Common.tasks.get(taskNumber - 1);
                System.out.println(REMOVE_MESSAGE);
                if (item.getClassSymbol().equals("[T]")) {
                    System.out.println(item.getClassSymbol() + item.getMarkingStatus() + " " + item.getContent());
                } else if (item.getClassSymbol().equals("[D]")) {
                    System.out.println(item.getClassSymbol() + item.getMarkingStatus() + " " + item.getContent()
                            + "(by: " + item.getDate() + ")");
                } else if (item.getClassSymbol().equals("[E]")) {
                    System.out.println(item.getClassSymbol() + item.getMarkingStatus() + " " + item.getContent()
                            + "(from: " + item.getBeginDate() + " to: " + item.getEndDate() + ")");
                }
                Common.tasks.remove(taskNumber - 1);
                System.out.println("Now you have " + Common.tasks.size() + " tasks in the list");
                try {
                    Common.dataFile.deleteTask(Common.FILE_PATH, taskNumber);
                } catch (IOException error) {
                    System.out.println(Common.WRITEFILE_EXCEPTION_MESSAGE);
                }
            }
        } catch (Exception error) {
            System.out.println(Common.INSTRUCTION + "\n" + seperatedWords[0] + ": Number");
        }
    }

}
