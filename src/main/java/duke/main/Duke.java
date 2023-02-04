package duke.main;

import duke.commands.Ui;
import duke.tasks.TaskList;

public class Duke {

    public static void main(String[] args) {
        Ui applicationProcess = new Ui();
        TaskList newTaskList = new TaskList();
        applicationProcess.greet();
        applicationProcess.run(newTaskList);
        applicationProcess.sayBye();
    }
}