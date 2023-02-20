package Duke.DukeCommandLine;

import Duke.DukeFunction.DukeList;
import Duke.DukeFunction.DukeUI;
import Duke.DukeTask.DukeDeadline;
import Duke.DukeTask.DukeEvent;
import Duke.DukeTask.DukeTask;

public class DukeCommandLineInput {
    String commandType;
    String commandMessage;
    public DukeCommandLineInput(String commandType, String commandMessage) {
        this.commandType = commandType;
        this.commandMessage = commandMessage;
    }
    public String getCommandType() {
        return this.commandType;
    }
    public String getCommandMessage() {
        return this.commandMessage;
    }
    public boolean isExit() {
        return this.commandType.equals("bye");
    }
    public void execute(DukeList tasks, DukeUI ui, DukeParser parser) throws DukeTaskInputException {
        int id;
        switch (this.commandType){
        case "bye":
            ui.printString("Bye. Hope to see you again soon!");
            ui.printGoodbyeLogo();
            break;
        case "list":
            tasks.listTask();
            break;
        case "mark":
            try {
                id = Integer.parseInt(this.commandMessage);
            } catch (NumberFormatException integerException) {
                throw new DukeTaskInputException("Sorry, the id is invalid!");
            }
            tasks.markDone(id-1);
            break;
        case "unmark":
            try {
                id = Integer.parseInt(this.commandMessage);
            } catch (NumberFormatException integerException) {
                throw new DukeTaskInputException("Sorry, the id is invalid!");
            }
            tasks.unmarkDone(id-1);
            break;
        case "delete":
            try {
                id = Integer.parseInt(this.commandMessage);
            } catch (NumberFormatException integerException) {
                throw new DukeTaskInputException("Sorry, the id is invalid!");
            }
            tasks.deleteTask(id-1);
            break;
        case "todo":
            try {
                DukeTask newTask = parser.processTask(this.commandMessage);
                tasks.addTask(newTask);
            } catch (DukeTaskInputException e) {
                throw e;
            }
            break;
        case "deadline":
            try {
                DukeDeadline deadline = parser.processDeadline(this.commandMessage);
                tasks.addTask(deadline);
            } catch (DukeTaskInputException e) {
                throw e;
            }
            break;
        case "event":
            try {
                DukeEvent event = parser.processEvent(this.commandMessage);
                tasks.addTask(event);
            } catch (DukeTaskInputException e) {
                throw e;
            }
            break;
        default: // invalid command
            throw new DukeTaskInputException("Sorry, I don't know what that means :-( ("
                    + this.commandType + ")");
        }
        tasks.saveTask();
    }
}
