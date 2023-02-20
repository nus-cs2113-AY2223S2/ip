import parser.Parser;
import storage.StorageFile;
import todolist.TaskList;
import ui.UI;

import java.util.Scanner;
import java.io.File;

public class Duke {
    //Instructions Strings
    public static final String ACTION_LIST = "list";
    public static final String ACTION_FIND = "find";
    public static final String ACTION_DELETE = "delete";
    public static final String ACTION_MARK_COMPLETE = "mark";
    public static final String ACTION_MARK_INCOMPLETE = "unmark";
    public static final String ACTION_NEW_TODO = "todo";
    public static final String ACTION_NEW_DEADLINE = "deadline";
    public static final String ACTION_NEW_EVENT = "event";
    public static final String ACTION_GOODBYE = "bye";
    //Error Strings
    public static final String ERROR_UNKNOWN_INSTRUCTION = "What are you talking about?";

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        File data = StorageFile.initialiseData(taskList);

        boolean madeAnyValidInstruction = false;

        Scanner in = new Scanner(System.in);
        String[] inputMessage = Parser.processInputMessage(in);
        while (!inputMessage[0].equals(ACTION_GOODBYE) && taskList.getSize() < 100) {
            switch (inputMessage[0]) {
            case ACTION_LIST:
                taskList.listAllTasks();
                inputMessage = Parser.processInputMessage(in);
                madeAnyValidInstruction = true;
                break;
            case ACTION_DELETE:
                int taskIndex = Parser.checkActionInputValidity(inputMessage, taskList.getSize());
                if (taskIndex >= 0) {
                    taskList.deleteATask(taskIndex);
                    StorageFile.updateData(taskList);
                    madeAnyValidInstruction = true;
                }
                inputMessage = Parser.processInputMessage(in);
                break;
            case ACTION_FIND:
                String query = Parser.processQuery(inputMessage);
                if (!query.equals("")) {
                    taskList.searchByKeyword(query);
                    madeAnyValidInstruction = true;
                }
                inputMessage = Parser.processInputMessage(in);
                break;
            case ACTION_MARK_COMPLETE:
                taskIndex = Parser.checkActionInputValidity(inputMessage, taskList.getSize());
                if (taskIndex >= 0) {
                    taskList.markTaskComplete(taskIndex);
                    StorageFile.updateData(taskList);
                    madeAnyValidInstruction = true;
                }
                inputMessage = Parser.processInputMessage(in);
                break;
            case ACTION_MARK_INCOMPLETE:
                taskIndex = Parser.checkActionInputValidity(inputMessage, taskList.getSize());
                if (taskIndex >= 0) {
                    taskList.markTaskIncomplete(taskIndex);
                    StorageFile.updateData(taskList);
                    madeAnyValidInstruction = true;
                }
                inputMessage = Parser.processInputMessage(in);
                break;
            case ACTION_NEW_TODO:
                String todoTask = Parser.processToDoMessage(inputMessage);
                if (!todoTask.equals("")) {
                    taskList.addNewTodoTask(todoTask);
                    StorageFile.updateData(taskList);
                    madeAnyValidInstruction = true;
                }
                inputMessage = Parser.processInputMessage(in);
                break;
            case ACTION_NEW_DEADLINE:
                String[] deadlineTask = Parser.processDeadlineMessage(inputMessage);
                if (deadlineTask.length == 2) {
                    taskList.addNewDeadlineTask(deadlineTask);
                    StorageFile.updateData(taskList);
                    madeAnyValidInstruction = true;
                }
                inputMessage = Parser.processInputMessage(in);
                break;
            case ACTION_NEW_EVENT:
                String[] eventTask = Parser.processEventMessage(inputMessage);
                if (eventTask.length == 3) {
                    taskList.addNewEventTask(eventTask);
                    StorageFile.updateData(taskList);
                    madeAnyValidInstruction = true;
                }
                inputMessage = Parser.processInputMessage(in);
                break;
            default:
                System.out.println(ERROR_UNKNOWN_INSTRUCTION);
                inputMessage = Parser.processInputMessage(in);
            }
        }
        UI.printGoodbyeMessage(madeAnyValidInstruction, taskList.getSize());
    }
}

