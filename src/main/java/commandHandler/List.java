package commandHandler;

import data.tasksList;
import ui.Display;

public class List {
    public static void listTasks() {
        if (tasksList.userTaskCount == 0) {
            Display.warnUser("You have no tasks added!");
            return;
        }
        Display.notifyUser("These are the following tasks you have:");
        for (int i = 0; i < tasksList.userTaskCount; i++) {
            Display.printLine();
            int index = i;
            index++;
            System.out.print(index + ".");
            System.out.println(tasksList.userTasksList.get(i));
            Display.printLine();
        }

    }

}
