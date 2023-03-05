import java.io.IOException;

/**
 * The Parser class handles the parsing of user input.
 */
public class Parser {
    /**
     * This method interprets input and executes commands based on given input.
     *
     * @param input User input
     */
    public static void parseCommand(String input){
        String[] parsedInputs = input.split(" ", 2);
        String command = parsedInputs[0];
        String[] partsList = (parsedInputs.length > 1) ? parsedInputs[1].split("/") : null;
        switch (command.toLowerCase()) {
        case StrIntLib.cmdList:
            TaskList.list();
            break;
        case StrIntLib.cmdMark:
            TaskList.checkDoneStatus(parsedInputs);
            try {
                Storage.writeSave();
                System.out.println(StrIntLib.saveDone);
            } catch (IOException e) {
                System.out.println(StrIntLib.saveError);
            }
            break;
        case StrIntLib.cmdUnmark:
            TaskList.uncheckDoneStatus(parsedInputs);
            try {
                Storage.writeSave();
                System.out.println(StrIntLib.saveDone);
            } catch (IOException e) {
                System.out.println(StrIntLib.saveError);
            }
            break;
        case StrIntLib.cmdAdd:
            TaskList.addTask(partsList);
            try {
                Storage.writeSave();
                System.out.println(StrIntLib.saveDone);
            } catch (IOException e) {
                System.out.println(StrIntLib.saveError);
            }
            break;
        case StrIntLib.cmdToDo:
            TaskList.addToDo(parsedInputs);
            try {
                Storage.writeSave();
                System.out.println(StrIntLib.saveDone);
            } catch (IOException e) {
                System.out.println(StrIntLib.saveError);
            }
            break;
        case StrIntLib.cmdEvent:
            TaskList.addEvent(partsList);
            try {
                Storage.writeSave();
                System.out.println(StrIntLib.saveDone);
            } catch (IOException e) {
                System.out.println(StrIntLib.saveError);
            }
            break;
        case StrIntLib.cmdDeadline:
            TaskList.addDeadline(partsList);
            try {
                Storage.writeSave();
                System.out.println(StrIntLib.saveDone);
            } catch (IOException e) {
                System.out.println(StrIntLib.saveError);
            }
            break;
        case StrIntLib.cmdDelete:
            TaskList.deleteTask(parsedInputs);
            try {
                Storage.writeSave();
                System.out.println(StrIntLib.saveDone);
            } catch (IOException e) {
                System.out.println(StrIntLib.saveError);
            }
            break;
        case StrIntLib.cmdFind:
            TaskList.findTasks(partsList[0]);
            break;
        case StrIntLib.cmdHelp:
            for (String line : StrIntLib.cmds) {
                System.out.println(line);
            }
            break;
        default:
            Ui.invalidCommand();
            break;
        }
    }
}
