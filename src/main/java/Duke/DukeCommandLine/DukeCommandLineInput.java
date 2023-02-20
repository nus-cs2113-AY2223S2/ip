package Duke.DukeCommandLine;

import Duke.DukeFunction.DukeList;
import Duke.DukeFunction.DukeStorage;
import Duke.DukeFunction.DukeUI;
import Duke.DukeTask.DukeDeadline;
import Duke.DukeTask.DukeEvent;
import Duke.DukeTask.DukeTask;

public class DukeCommandLineInput {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_DATE = "date";
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
    public void execute(DukeList tasks, DukeUI ui,
                        DukeParser parser, DukeStorage storage) throws DukeException {
        int id;
        switch (this.commandType){
        case COMMAND_BYE:
            ui.printString("Bye. Hope to see you again soon!");
            ui.printGoodbyeLogo();
            break;
        case COMMAND_LIST:
            tasks.listTask();
            break;
        case COMMAND_MARK:
            try {
                id = Integer.parseInt(this.commandMessage);
            } catch (NumberFormatException integerException) {
                throw new DukeException("Sorry, the id is invalid!");
            }
            tasks.markDone(id-1);
            break;
        case COMMAND_UNMARK:
            try {
                id = Integer.parseInt(this.commandMessage);
            } catch (NumberFormatException integerException) {
                throw new DukeException("Sorry, the id is invalid!");
            }
            tasks.unmarkDone(id-1);
            break;
        case COMMAND_DELETE:
            try {
                id = Integer.parseInt(this.commandMessage);
            } catch (NumberFormatException integerException) {
                throw new DukeException("Sorry, the id is invalid!");
            }
            tasks.deleteTask(id-1);
            break;
        case COMMAND_TODO:
            try {
                DukeTask newTask = parser.processTask(this.commandMessage);
                tasks.addTask(newTask);
            } catch (DukeException e) {
                throw e;
            }
            break;
        case COMMAND_DEADLINE:
            try {
                DukeDeadline deadline = parser.processDeadline(this.commandMessage);
                tasks.addTask(deadline);
            } catch (DukeException e) {
                throw e;
            }
            break;
        case COMMAND_EVENT:
            try {
                DukeEvent event = parser.processEvent(this.commandMessage);
                tasks.addTask(event);
            } catch (DukeException e) {
                throw e;
            }
            break;
        case COMMAND_DATE:
            try {
                tasks.listTaskByDate(this.commandMessage);
            } catch (DukeException e) {
                throw e;
            }
            break;
        default: // invalid command
            throw new DukeException("Sorry, I don't know what that means :-( ("
                    + this.commandType + ")");
        }
        storage.saveTask(tasks);
    }
}
