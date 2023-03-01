package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class FindTask {
    public static void searchForKeyword(String keyWord, ArrayList<Task> storedUserTasks) {
        System.out.println(Display.DIVIDER);
        int count = 0;
        for (int i = 0; i < storedUserTasks.size(); i++) {
            if(storedUserTasks.get(i).toString().contains(keyWord)){
                count++;
                if (count==1) {
                    System.out.println(Display.SPACER+"Here are the matching tasks in your list:");
                }
                System.out.println(Display.SPACER+(i+1)+"."+storedUserTasks.get(i).toString());
            }
        }
        if (count==0) {
            System.out.println(Display.SPACER+"No tasks contained the keyword.");
        }
        System.out.println(Display.DIVIDER);
    }

}
