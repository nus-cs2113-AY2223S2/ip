package task;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskController implements ITaskController  {
    ArrayList<Task> list = new ArrayList<Task>();
    public Task addTask(Task newTask) {
        list.add(newTask);
        return newTask;
    }
    public ArrayList<Task> getTasks() throws EmptyTaskListException {
        if (list.isEmpty()) {
            throw new EmptyTaskListException("*** Empty List", new NoSuchElementException());
        }
        return list;
    }

    public String toString() {
        String Text = IntStream.range(0, list.size())
                                .mapToObj(index -> String.format("%d. %s\n", index+1, list.get(index)))
                                .collect(Collectors.joining("\n"));
        return Text;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String markTask(int taskIndex) throws TaskIndexOutOfRangeException, TaskMarkException {
        return handleTaskMark(true, taskIndex);
    }

    @Override
    public String unmarkTask(int taskIndex) throws TaskIndexOutOfRangeException, TaskMarkException {
        return handleTaskMark(false, taskIndex);
    }
    /**
     * Marks/Unmarks the task based on isMark and task index. Throws and exception when task index
     * is not valid or when task mark status does not change.
     * Private method to abstract the implementation from the public methods that call it.
     * @param isMark
     * @param taskIndex
     * @return String message for Duke to display to user
     * @throws TaskIndexOutOfRangeException
     * @throws TaskMarkException
     */
    private String handleTaskMark(boolean isMark, int taskIndex) throws TaskIndexOutOfRangeException, TaskMarkException {
        if (taskIndex <= 0 || taskIndex > list.size()) {
            // Bad index input by user
            throw new TaskIndexOutOfRangeException("Invalid index given to mark!", new IllegalArgumentException());
        }
        try {
            String dukeMessage;

            if (isMark) {
                list.get(taskIndex - 1).setMark(true);
                dukeMessage = "Nice! I've marked this task as done:";
            } else {
                list.get(taskIndex - 1).setMark(false);
                dukeMessage = "OK, I've marked this task as not done yet:";
            }

            return String.format("%s\n %s",
                    dukeMessage,
                    list.get(taskIndex - 1).toString());
        } catch (TaskMarkException e) {
            throw e;
        }
    }
}