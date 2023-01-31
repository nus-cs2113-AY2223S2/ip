package duke.main;

import duke.tasks.TaskList;

public class Duke {

    public static void main(String[] args) {
        UI applicationProcess = new UI();
        TaskList newTaskList = new TaskList();
        applicationProcess.greet();
        applicationProcess.inputFunction(newTaskList);
        applicationProcess.sayBye();
    }
}
