package duke;

import duke.task.TaskList;
import duke.ui.ExceptionsUi;
import duke.ui.Ui;

public class Duke {
    private static final String FILE_PATH = "duke.txt";
    private Ui ui;
    private ExceptionsUi exceptionsUi;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    public Duke() {
        storage = new Storage(FILE_PATH);
        taskList = new TaskList();
        ui = new Ui();
        exceptionsUi = new ExceptionsUi();
        parser = new Parser();
        storage.getFileData(taskList);
    }

    public void run() {
        String command;
        String commandArgs;
        ui.greet();
        while (true) {
            command = parser.parse();
            commandArgs = parser.getCommandArgs();
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
                } else {
                    exceptionsUi.printInvalidCommand();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                exceptionsUi.printArrayIndexOutOfBoundsException();
            }
            storage.saveDataToFile(taskList);
        }
        ui.printExit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
