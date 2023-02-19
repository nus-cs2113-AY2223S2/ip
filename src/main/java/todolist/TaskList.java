package todolist;

import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;

import java.util.ArrayList;

public class TaskList {
    //All the messages Strings
    public static final String COMPLETED_TASK_MESSAGE = "Nice! I've marked this task as done!";
    public static final String INCOMPLETE_TASK_MESSAGE = "Why are you being lazy? >:(";
    public static final String ADDED_TASK_MESSAGE = "Done! Added: ";
    public static final String TASK_REMOVED_MESSAGE_ONE = "Task removed:";
    public static final String TASK_REMOVED_MESSAGE_TWO = "Total tasks left: ";
    //Error Strings
    public static final String ERROR_NO_TASKS_IN_LIST = "Behold the fields of which I keep your tasks. " +
            "Lay thine eyes upon it, and see that it is barren";

    public ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public Task getTask(int taskIndex) {
        return list.get(taskIndex);
    }

    public void addTask(Task newTask) {
        list.add(newTask);
    }

    public int getSize() {
        return list.size();
    }

    public void listAllTasks() {
        Task currentTodo;
        int sizeOfTodoList = list.size();
        if(sizeOfTodoList == 0) {
            System.out.println(ERROR_NO_TASKS_IN_LIST);
        }
        for (int i = 0; i < sizeOfTodoList; i += 1) {
            currentTodo = list.get(i);
            String printedMessage = String.format("%d.%s", i + 1, currentTodo);
            System.out.println(printedMessage);
        }
    }

    public void deleteATask(int taskIndex) {
        Task currentTodo = list.get(taskIndex);
        list.remove(taskIndex);
        System.out.println(TASK_REMOVED_MESSAGE_ONE);
        System.out.println(currentTodo);
        System.out.println(TASK_REMOVED_MESSAGE_TWO + list.size());
    }

    public void markTaskComplete(int taskIndex) {
        Task currentTodo = list.get(taskIndex);
        currentTodo.setComplete();
        System.out.println(COMPLETED_TASK_MESSAGE);
        System.out.println(currentTodo);
    }

    public void markTaskIncomplete(int taskIndex) {
        Task currentTodo = list.get(taskIndex);
        currentTodo.setIncomplete();
        System.out.println(INCOMPLETE_TASK_MESSAGE);
        System.out.println(currentTodo);
    }

    public void addNewEventTask(String[] inputMessage) {
        Event newEvent = new Event(inputMessage[0], inputMessage[1], inputMessage[2]);
        list.add(newEvent);
        System.out.println(ADDED_TASK_MESSAGE + newEvent);
    }

    public void addNewDeadlineTask(String[] inputMessage) {
        Deadline newDeadline = new Deadline(inputMessage[0], inputMessage[1]);
        list.add(newDeadline);
        System.out.println(ADDED_TASK_MESSAGE + newDeadline);
    }

    public void addNewTodoTask(String task) {
        Todo newTodo = new Todo(task);
        this.list.add(newTodo);
        System.out.println(ADDED_TASK_MESSAGE + newTodo);
    }

}
