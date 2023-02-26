package duke.command;

import duke.tasklist.TaskList;
import duke.tasks.Task;

import static duke.ui.Ui.printBorder;

public class FindCommand {

    public static void findTask(TaskList tasks, String keyword){
        int counter = 0;
        System.out.println("Here are the matching tasks in your list:");
        for (Task t : tasks.getTasks()){
            counter++;
            if(t.name.contains(keyword)){
                System.out.println(counter + ". " + t);
            }
        } 
        printBorder();
    }

}
