package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;


public class TaskList {
    static void addEventTask(ArrayList<Task> tasks, String[] informationNeededForPerformingUserRequest) {
        tasks.add(new Event(informationNeededForPerformingUserRequest[1], informationNeededForPerformingUserRequest[2], informationNeededForPerformingUserRequest[3]));
        Ui.printNotification(tasks.get(tasks.size() - 1), "event", tasks.size());
    }

    static void addDeadlineTask(ArrayList<Task> tasks, String[] informationNeededForPerformingUserRequest) {
        tasks.add(new Deadline(informationNeededForPerformingUserRequest[1], informationNeededForPerformingUserRequest[2]));
        Ui.printNotification(tasks.get(tasks.size() - 1), "deadline", tasks.size());
    }

    static void addTodoTask(ArrayList<Task> tasks, String[] informationNeededForPerformingUserRequest) {
        tasks.add(new ToDo(informationNeededForPerformingUserRequest[1]));
        Ui.printNotification(tasks.get(tasks.size() - 1), "todo", tasks.size());
    }

    static void deleteTask(ArrayList<Task> tasks, String[] informationNeededForPerformingUserRequest) {
        int indexToRemove = Integer.parseInt(informationNeededForPerformingUserRequest[1]) - 1; // 0-indexed
        Ui.printNotification(tasks.get(indexToRemove), "delete", tasks.size() - 1);
        tasks.remove(indexToRemove);
    }

    static void unmarkTask(ArrayList<Task> tasks, String[] informationNeededForPerformingUserRequest) {
        int indexToBeUnmarked = Integer.parseInt(informationNeededForPerformingUserRequest[1]) - 1; // 0-indexed
        tasks.get(indexToBeUnmarked).setDone(false);
        Ui.printNotification(tasks.get(indexToBeUnmarked), "unmark", tasks.size());
    }

    static void markTask(ArrayList<Task> tasks, String[] informationNeededForPerformingUserRequest) {
        int indexToBeMarked = Integer.parseInt(informationNeededForPerformingUserRequest[1]) - 1; // 0-indexed
        tasks.get(indexToBeMarked).setDone(true);
        Ui.printNotification(tasks.get(indexToBeMarked), "mark", tasks.size());
    }
}