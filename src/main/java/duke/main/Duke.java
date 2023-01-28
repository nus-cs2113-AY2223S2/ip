package duke.main;

import duke.commands.UI;
import duke.task.*;
import duke.commands.DukeException;

import java.util.Scanner;

public class Duke {

    public static void emptyList() {
        System.out.println("Your list is empty!\n" + UI.getLineBreak());
    }

    public static void main(String[] args) {
        UI newUser = new UI();
        TaskList newTaskList = new TaskList();
        newUser.greet();
        newUser.inputFunction(newTaskList);
        newUser.sayBye();
    }
}
