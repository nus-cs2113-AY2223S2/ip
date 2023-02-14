package duke;

import java.io.IOException;

public class Parser {
    protected TaskList taskList;
    protected FileDataHandler fileDataHandler;
    //protected Ui ui;
    public Parser (TaskList taskList, FileDataHandler fileDataHandler) {
        this.taskList = taskList;
        this.fileDataHandler = fileDataHandler;
    }

    public boolean parse (String[] inputs) throws IOException, DukeException {

        if (inputs[0].equals("bye")) {
            return true;
        }
        if (inputs[0].isEmpty()) { //settle for the case of empty inputs
            return false;
        }
        if (inputs[0].equals("list")) { //want to print out the task list
            taskList.printTaskList();
            return false;
        }
        if (inputs[0].equals("mark")) {
            int taskNumber = Integer.parseInt(inputs[1]);
            taskList.getTask(taskNumber - 1).markAsDone();
            taskList.printMarkedTask(taskNumber - 1);
            fileDataHandler.saveFile(taskList);
            return false;
        }
        if (inputs[0].equals("unmark")) {
            int taskNumber = Integer.parseInt(inputs[1]);
            taskList.getTask(taskNumber - 1).markAsNotDone();
            taskList.printUnmarkedTask(taskNumber - 1);
            fileDataHandler.saveFile(taskList);
            return false;
        }
        if (inputs[0].equals("todo")) {
            taskList.addTodoTask(inputs[1]);
            fileDataHandler.saveFile(taskList);
            return false;
        }
        if (inputs[0].equals("deadline")) {
            taskList.addDeadlineTask(inputs[1]);
            fileDataHandler.saveFile(taskList);
            return false;
        }
        if (inputs[0].equals("event")) {
            taskList.addEventTask(inputs[1]);
            fileDataHandler.saveFile(taskList);
            return false;
        }
        if (inputs[0].equals("delete")) {
            int taskNumber = Integer.parseInt(inputs[1]);
            taskList.deleteTask(taskNumber);
            fileDataHandler.saveFile(taskList);
            return false;
        }
        throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
