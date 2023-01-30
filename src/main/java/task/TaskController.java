package task;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskController implements ITaskController  {
    ArrayList<Task> TaskList = new ArrayList<Task>();
    public String addTask(Task newTask) {
        TaskList.add(newTask);

        return "Got it. I've added this task:\n " + 
                newTask.toString() +
                String.format("\nNow you have %d tasks in the list.", TaskList.size());
    }
    public ArrayList<Task> getTasks() throws EmptyTaskListException {
        if (TaskList.isEmpty()) {
            throw new EmptyTaskListException("*** Empty List", new NoSuchElementException());
        }
        return TaskList;
    }

    public String toString() {
        String Text = IntStream.range(0, TaskList.size())
                                .mapToObj(index -> String.format("%d. %s\n", index+1, TaskList.get(index)))
                                .collect(Collectors.joining("\n"));
        return Text;
    }
    @Override
    public int getCount() {
        return TaskList.size();
    }

    @Override
    public String markTask(int taskIndex) throws TaskIndexOutOfRangeException, TaskMarkException {
        final boolean MARK_STATUS = true;
        return handleTaskMark(MARK_STATUS, taskIndex);
    }

    @Override
    public String unmarkTask(int taskIndex) throws TaskIndexOutOfRangeException, TaskMarkException {
        final boolean MARK_STATUS = false;
        return handleTaskMark(MARK_STATUS, taskIndex);
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
        if (taskIndex <= 0 || taskIndex > TaskList.size()) {
            // Bad index input by user
            throw new TaskIndexOutOfRangeException("Invalid index given to mark!", new IllegalArgumentException());
        }
        try {
            final String USER_VIEW_MESSAGE;

            if (isMark) {
                TaskList.get(taskIndex - 1).setMark(true);
                USER_VIEW_MESSAGE = "Nice! I've marked this task as done:";
            } else {
                TaskList.get(taskIndex - 1).setMark(false);
                USER_VIEW_MESSAGE = "OK, I've marked this task as not done yet:";
            }

            return String.format("%s\n %s",
                    USER_VIEW_MESSAGE,
                    TaskList.get(taskIndex - 1).toString());
        } catch (TaskMarkException e) {
            throw e;
        }
    }
}