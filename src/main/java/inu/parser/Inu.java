package inu.parser;

import inu.tasklist.*;
import inu.tasklist.TaskList;

public class Inu {

    public TaskList taskList;

    public Inu() {

        taskList = new TaskList();

        Ui.printGreeting();
        UserCommands.parseCommand(taskList);
        Ui.printFarewell();

    }

    public static void main(String[] args) {

        new Inu();

    }
}
