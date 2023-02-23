package parser;

import duke.Duke;

public class DukeParser {

    private Duke duke;

    public DukeParser(Duke duke) {
        this.duke = duke;
    }
    
    public void parseUserInput(String input) {
        String[] split = input.trim().split("\\s+", 2);
        String command = split[0];
        String args = split.length == 2 ? split[1] : "";
        switch (command) {
        case "bye":
            duke.hasEnteredBye = true;
            duke.exit();
            break;
        case "list":
            duke.taskList.listTasks();
            break;
        case "help":
            duke.ui.printHelp();
            break;
        case "mark":
            try {
                duke.taskList.markDone(Integer.parseInt(args));
            } catch (Exception e) {
                System.out.println("Please input an integer after mark.");
            }
            break;
        case "unmark":
            try {
                duke.taskList.unmarkDone(Integer.parseInt(args));
            } catch (Exception e) {
                System.out.println("Please input an integer after unmark.");
            }
            break;
        case "todo":
            duke.taskList.addToDo(args);
            break;
        case "deadline":
            duke.taskList.addDeadline(args);
            break;
        case "event":
            duke.taskList.addEvent(args);
            break;
        case "delete":
            try {
                duke.taskList.deleteTask(Integer.parseInt(args));
            } catch (Exception e) {
                System.out.println("Please input an integer after delete.");
            }
            break;
        case "find":
            duke.taskList.findTask(args);
            break;
        case "save":
            duke.savefileManager.save(duke.taskList);
            break;
        default:
            duke.ui.printInvalidCommand();
        }
    }
}
