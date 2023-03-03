package duke.utils;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.util.ArrayList;
import static duke.utils.Ui.*;

public class TaskList {
    Ui ui = new Ui();
    public ArrayList<Task> list = new ArrayList<>();
    public ArrayList<String> taskDescriptions= new ArrayList<>();
    public ArrayList<Task> matchingList = new ArrayList<>();
    public int matchingTasksNum;

    public void printAllTasks(int currentTaskNum) {
        int currentPrintedTask = 0;
        int placeHolder = currentTaskNum;
        System.out.println(LINE);
        if (placeHolder == 0) {
            System.out.println("No Task!");
        } else {
            while (placeHolder > 0) {
                System.out.println(currentPrintedTask + 1 + ". " + list.get(currentPrintedTask).toString());
                currentPrintedTask++;
                placeHolder--;
            }
        }
        System.out.println(LINE);
    }

    public void findMatchingTasks(String keyword) {
        for (Task task : list) {
            taskDescriptions.add(task.description);
        }
        int i = 0;
        for (String taskDescription : taskDescriptions) {
            if (taskDescription.contains(keyword)) {
                matchingList.add(list.get(i));
            };
            i++;
        }
    }

    public void printMatchingTasks() {
        int index = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task matchingTask : matchingList) {
            System.out.println(index + ". " + matchingTask.toString());
            index++;
        }
        System.out.println(System.lineSeparator() + Ui.LINE);
        taskDescriptions.clear();
        matchingList.clear();
    }

    public void addEvent(String content) {
        String[] phrases;
        phrases = content.split("/");
        if (phrases.length < 3) {
            ui.printErrorMessage(EVENT_TIME_ERROR_MESSAGE);
        } else {
            list.add(new Event(phrases[0], phrases[1], phrases[2]));
        }
    }

    public void addDeadline(String content) {
        String[] phrases;
        phrases = content.split("/by ");
        if (phrases.length < 2) {
            ui.printErrorMessage(DEADLINE_TIME_ERROR_MESSAGE);
        } else {
            list.add(new Deadline(phrases[0], phrases[1]));
        }
    }

    public void addTodo(String content) {
        list.add(new Todo(content));
    }

    public void deleteTask(String content) {
        list.remove(Integer.parseInt(content) - 1);
        matchingTasksNum--;
    }

    public void toggleMark(String content, boolean shouldMarkAsDone) throws DukeException {
        int posOfMark = Integer.parseInt(content) - 1;
        if (!(posOfMark >= 0 && posOfMark <= matchingTasksNum)) {
            ui.printErrorMessage(INVALID_NUM_ERROR_MESSAGE);
            throw new DukeException();
        } else {
            if (shouldMarkAsDone) {
                list.get(posOfMark).markAsDone();
            } else {
                list.get(posOfMark).markAsUndone();
            }
        }
    }

    public void printAddTaskMessage() {
        System.out.println(LINE + "Got it. I've added this task:\n"
                + "  "
                + this.list.get(matchingTasksNum).toString()
                + System.lineSeparator());
        matchingTasksNum++;
        printTotalTasksNum(matchingTasksNum);
    }

    public void printDeleteTaskMessage(int taskNum) {
        System.out.println(LINE + "Got it. I've deleted this task:\n"
                + "  "
                + this.list.get(taskNum).toString()
                + System.lineSeparator());
    }

    public void printTotalTasksNum(int currentTaskNum) {
        if (currentTaskNum == 1) {
            System.out.println("Now you have " + currentTaskNum + " task in the list."
                    + System.lineSeparator() + Ui.LINE);
        } else {
            System.out.println("Now you have " + currentTaskNum + " tasks in the list."
                    + System.lineSeparator() + Ui.LINE);
        }
    }
}