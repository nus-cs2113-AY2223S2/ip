package duke;

import java.io.IOException;

import tasklist.TaskList;

public class Duke {
    private static boolean hasEnteredBye = false;
    private static final boolean WITH_SCANNER = true;
    private static DukeUi ui = new DukeUi(WITH_SCANNER);
    private static TaskList taskList;
    private static SavefileManager savefileManager = new SavefileManager();

    public static void exit() {
        ui.printLine();
        savefileManager.save(taskList);
        ui.closeScanner();
        System.out.println("Bye. Hope to see you again soon!");
    }
    
    public static void parseUserInput(String input) {
        String[] split = input.trim().split("\\s+", 2);
        String command = split[0];
        String args = split.length == 2 ? split[1] : "";
        switch (command) {
        case "bye":
            hasEnteredBye = true;
            exit();
            break;
        case "list":
            taskList.listTasks();
            break;
        case "help":
            ui.printHelp();
            break;
        case "mark":
            try {
                taskList.markDone(Integer.parseInt(args));
            } catch (Exception e) {
                System.out.println("Please input an integer after mark.");
            }
            break;
        case "unmark":
            try {
                taskList.unmarkDone(Integer.parseInt(args));
            } catch (Exception e) {
                System.out.println("Please input an integer after unmark.");
            }
            break;
        case "todo":
            taskList.addToDo(args);
            break;
        case "deadline":
            taskList.addDeadline(args);
            break;
        case "event":
            taskList.addEvent(args);
            break;
        case "delete":
            try {
                taskList.deleteTask(Integer.parseInt(args));
            } catch (Exception e) {
                System.out.println("Please input an integer after delete.");
            }
            break;
        case "find":
            taskList.findTask(args);
            break;
        case "save":
            savefileManager.save(taskList);
            break;
        default:
            System.out.println("Invalid command entered, please enter 'help' to see " +
            "the list of commands.");
        }
    }
    
    public static void main(String[] args) {
        ui.greetUser();
        try {
            savefileManager.checkSaveDir();
            savefileManager.checkSavefile();
            taskList = savefileManager.parseSavefile();
            ui.printLine();
        } catch (IOException e) {
            System.out.println("Savefile cannot be found, please delete the savefile yourself");
            System.out.println("cos I am too lazy to delete it for you for now.");
            return;
        } catch (Exception e) {
            return;
        }
        
        while (!hasEnteredBye) {
            String line = ui.getNextLine();
            parseUserInput(line);
            ui.printLine();
        }
        ui.closeScanner();
    }
}
