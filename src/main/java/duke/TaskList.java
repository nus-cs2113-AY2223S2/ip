package duke;

import duke.instructions.Deadline;
import duke.instructions.Event;
import duke.instructions.Task;
import duke.instructions.Todo;

import java.util.ArrayList;

public class TaskList {

    private static final ArrayList<Task> taskNameList = new ArrayList<>();
    public static final String LINE = "    ____________________________________________________________";
    public static final String EMPTY_DESCRIPTION = "     ☹ OOPS!!! The description cannot be empty.";


    public static void listCommand(String command) {
        Task task = new Task(command);
        System.out.println(LINE);
        System.out.print(task.guideline());
        for (int indexOfInstruction = 0; indexOfInstruction < taskNameList.size(); indexOfInstruction++) {
            System.out.print("     " + (indexOfInstruction + 1) + "."
                    + taskNameList.get(indexOfInstruction).getState().trim() + System.lineSeparator());
        }
        System.out.println(LINE);

    }

    public static void markCommand(String[] command) {
        int indexTask = Integer.parseInt(command[1]);
        System.out.println(LINE);
        taskNameList.get(indexTask - 1).mark();
        System.out.print("     Nice! I've marked this task as done:"
                + System.lineSeparator()
                + taskNameList.get(indexTask - 1).getState());
        System.out.println(LINE);
    }

    public static void unMarkCommand(String[] command) {
        int indexOfTask = Integer.parseInt(command[1]);
        System.out.println(LINE);
        taskNameList.get(indexOfTask - 1).unMark();
        System.out.print("     OK, I've marked this task as not done yet:"
                + System.lineSeparator()
                + taskNameList.get(indexOfTask - 1).getState());
        System.out.println(LINE);

    }

    public static void toDoCommand(String input) throws DukeException {
        Todo toDo = new Todo(input);
        toDo.setTaskType("T");
        Task task = new Task(input);
        String[] tokens = input.split("\\s+", 2);
        if (tokens.length < 2) {
            throw new DukeException(EMPTY_DESCRIPTION);
        }
        System.out.println(LINE);
        taskNameList.add(toDo);
        String outputForTodo = toDo.guideline()
                + toDo.getState()
                + task.numberOfTask(taskNameList.size());
        System.out.println(outputForTodo);
        System.out.println(LINE);

    }

    public static void eventCommand(String input) {
        Event event = new Event(input);
        event.setTaskType("E");
        Task task = new Task(input);
        taskNameList.add(event);
        String outputForEvent = event.guideline()
                + event.getState()
                + task.numberOfTask(taskNameList.size());
        System.out.println(outputForEvent);
        System.out.println(LINE);

    }

    public static void deadlineCommand(String command) {
        Deadline deadLine = new Deadline(command);
        deadLine.setTaskType("D");
        Task task = new Task(command);
        System.out.println(LINE);
        taskNameList.add(deadLine);
        String outputForDeadline = deadLine.guideline()
                + deadLine.getState()
                + task.numberOfTask(taskNameList.size());
        System.out.println(outputForDeadline);
        System.out.println(LINE);
    }
    public static void deleteCommand(String command){
        int indexTask = Integer.parseInt(command);
        Task task = new Task(command);
        System.out.println(LINE);
        System.out.print("     Noted. I've removed this task:"
                + System.lineSeparator()
                + taskNameList.get(indexTask - 1).getState());
        taskNameList.remove(indexTask - 1);
        System.out.println(task.numberOfTask(taskNameList.size()));
        System.out.println(LINE);
    }
    public static void findCommand(String command){
        String keyword = command.substring(5);

        ArrayList<Task> matchedTask = new ArrayList<>();

        for (Task task : taskNameList) {
            if ((task.getState()).contains(keyword)) {
               matchedTask.add(task);
            }
        }
        int numberOfFoundTasks = matchedTask.size();
        int number0fTask = 1;
        System.out.println("     There are " + numberOfFoundTasks + " matching tasks in your list");
        for (Task foundTask : matchedTask) {
            if (foundTask != null) {
                System.out.print("     " +number0fTask + "." + foundTask.getState().trim() + System.lineSeparator());
            }
            number0fTask++;
        }
    }

}
