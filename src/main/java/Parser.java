public class Parser {
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
            break;
        case StrIntLib.cmdUnmark:
            TaskList.uncheckDoneStatus(parsedInputs);
            break;
        case StrIntLib.cmdAdd:
            TaskList.addTask(partsList);
            break;
        case StrIntLib.cmdToDo:
            TaskList.addToDo(parsedInputs);
            break;
        case StrIntLib.cmdEvent:
            TaskList.addEvent(partsList);
            break;
        case StrIntLib.cmdDeadline:
            TaskList.addDeadline(partsList);
            break;
        case StrIntLib.cmdDelete:
            TaskList.deleteTask(parsedInputs);
            break;
        case StrIntLib.cmdFind:
            TaskList.findTasks(partsList[0]);
            break;
        default:
            Ui.invalidCommand();
            break;
        }
    }
}
