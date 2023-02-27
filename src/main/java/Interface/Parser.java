package Interface;

import Exceptions.DukeException;

public class Parser {

    /**
     * Reads in the user input, parses the first keyword and executes the commands accordingly,
     * by default try to add to task list
     *
     * @param isContinue boolean condition to continue running the function
     */
    public static void runDude(boolean isContinue) {
        while(isContinue) {
            String command = Ui.readCommand();
            String firstKeyword = command.split(" ")[0];
            switch (firstKeyword) {
            case "list":
                TaskList.listTasks();
                break;
            case "mark":
                try {
                    TaskList.mark(command);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "unmark":
                try {
                    TaskList.unmark(command);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "delete":
                try {
                    TaskList.delete(command);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "bye":
                Ui.farewell();
                isContinue = false;
                break;
            default:
                try {
                    TaskList.addToList(command);
                } catch (DukeException error) {
                    System.out.println(error.getMessage());
                }
                break;
            }
        }
    }
}
