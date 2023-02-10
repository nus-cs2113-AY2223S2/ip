package inu.parser;

import inu.tasklist.*;
import inu.tasklist.TaskList;
import inu.parser.StorageFile.*;

public class Inu {

    public TaskList taskList;

    public Inu() {

        taskList = new TaskList();
        new StorageFile(taskList);

        Ui.printGreeting();
        UserCommands.parseCommand(taskList);
        Ui.printFarewell();
        StorageFile.writeFile(taskList);

    }

    public static void main(String[] args) {

        new Inu();

    }
}
