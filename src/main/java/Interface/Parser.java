package Interface;

import Exceptions.DukeException;

public class Parser {
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
