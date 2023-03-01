package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.ExceptionsUi;
import duke.ui.Ui;

public class Duke {
    private static final String FILE_PATH = "duke.txt";
    private TaskList taskList;
    private Storage storage;


    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList();
        storage.getFileData(taskList);
    }

    public void run() {
        String command;
        String commandArgs;
        Ui.greet();
        while (true) {
            command = Parser.parse();
            commandArgs = Parser.getCommandArgs();
            try {
                if (command.equals("bye")) {
                    break;
                } else if (command.equals("list")) {
                    taskList.listTask();
                } else if (command.equals("mark")) {
                    taskList.markTask(commandArgs);
                } else if (command.equals("unmark")) {
                    taskList.unmarkTask(commandArgs);
                } else if (command.equals("todo")) {
                    taskList.addTodo(commandArgs);
                } else if (command.equals("deadline")) {
                    taskList.addDeadline(commandArgs);
                } else if (command.equals("event")) {
                    taskList.addEvent(commandArgs);
                } else if (command.equals("delete")) {
                    taskList.deleteTask(commandArgs);
                } else if (command.equals("find")) {
                    taskList.findTask(commandArgs);
                } else {
                    ExceptionsUi.printInvalidCommandError();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                ExceptionsUi.printArrayIndexOutOfBoundsExceptionError();
            }
            storage.saveDataToFile(taskList);
        }
        Ui.printExit();
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

}
