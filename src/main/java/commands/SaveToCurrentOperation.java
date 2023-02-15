package commands;

import exceptions.InvalidTaskException;
import tasks.Deadline;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class SaveToCurrentOperation {
    public static void save(String task, ArrayList<Task> list) throws InvalidTaskException {
        String description_temp = task.split("/")[1];
        System.out.println("description_temp" + description_temp);
        String description = description_temp.split("Description:")[1];
        System.out.println("description: " + description);
        System.out.println(Parser.parseTaskType(task));
        switch (Parser.parseTaskType(task)) {
            case "T":
                System.out.println("Task type: T");
                list.add(new Todo(description));
                break;
            case "D":
//                String deadline = deadline_temp.split("by:")[0];
//                AddDeadline.addDeadlineTask(list, description);
                break;
            case "E":
                break;
            default:
                break;
        }
    }

}
