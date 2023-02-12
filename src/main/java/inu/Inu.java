package inu;

import inu.task.TaskList;
import inu.storage.StorageFile;
import inu.commons.Ui;
import inu.parser.UserCommands;

public class Inu {

    public TaskList taskList;

    public Inu() {
        taskList = new TaskList();
        StorageFile.loadTaskListFromFile(taskList);
        Ui.printGreeting();
        UserCommands.parseCommand(taskList);
        Ui.printFarewell();
        StorageFile.saveTaskListToFile(taskList);
    }

    public static void main(String[] args) {
        new Inu();
    }
}