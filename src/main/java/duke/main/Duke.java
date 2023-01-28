package duke.main;

import duke.commands.UI;
import duke.task.TaskList;

public class Duke {

    public static void main(String[] args) {
        UI newUser = new UI();
        TaskList newTaskList = new TaskList();
        newUser.greet();
        newUser.inputFunction(newTaskList);
        newUser.sayBye();
    }
}
