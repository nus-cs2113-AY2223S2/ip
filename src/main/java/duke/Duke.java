package duke;

import duke.instructions.Deadline;
import duke.instructions.Event;
import duke.instructions.Task;
import duke.instructions.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String EMPTY_DESCRIPTION = "     ☹ OOPS!!! The description cannot be empty.";
    public static final String LINE = "    ____________________________________________________________";
    private static final String UNRECOGNISED_INPUT = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final ArrayList<Task> taskNameList = new ArrayList<>();

    public static void greeting() {

        System.out.println(LINE);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
    }

    public static void inputValues(String input) throws DukeException {
        String[] command = input.split("\\s+");
        switch (command[0]) {
        case "list":
            listMain(input);
            break;
        case "mark":
            markMain(command);
            break;
        case "ummark":
            unMarkMain(command);
            break;
        case "todo":
            toDoMain(input);
            break;
        case "deadline":
            deadlineMain(input);
            break;
        case "event":
            eventMain(input);
            break;
        case "delete":
            deleteMain(input, command);
            break;
        default:
            throw new DukeException(UNRECOGNISED_INPUT);

        }
    }

    public static void listMain(String input) {
        Task task = new Task(input);
        System.out.println(LINE);
        System.out.print(task.guideline());
        for (int indexOfInstruction = 0; indexOfInstruction < taskNameList.size(); indexOfInstruction++) {
            System.out.print("     " + (indexOfInstruction + 1) + "."
                    + taskNameList.get(indexOfInstruction).getState().trim() + System.lineSeparator());
        }
        System.out.println(LINE);

    }

    public static void markMain(String[] command) {
        int indexTask = Integer.parseInt(command[1]);
        System.out.println(LINE);
        taskNameList.get(indexTask - 1).mark();
        System.out.print("     Nice! I've marked this task as done:"
                + System.lineSeparator()
                + taskNameList.get(indexTask - 1).getState());
        System.out.println(LINE);
    }

    public static void unMarkMain(String[] command) {
        int indexOfTask = Integer.parseInt(command[1]);
        System.out.println(LINE);
        taskNameList.get(indexOfTask - 1).unMark();
        System.out.print("     OK, I've marked this task as not done yet:"
                + System.lineSeparator()
                + taskNameList.get(indexOfTask - 1).getState());
        System.out.println(LINE);

    }

    public static void toDoMain(String input) throws DukeException {
        Todo toDo = new Todo(input);
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

    public static void eventMain(String input) {
        Event event = new Event(input);
        Task task = new Task(input);
        taskNameList.add(event);
        String outputForEvent = event.guideline()
                + event.getState()
                + task.numberOfTask(taskNameList.size());
        System.out.println(outputForEvent);
        System.out.println(LINE);

    }

    public static void deadlineMain(String input) {
        Deadline deadLine = new Deadline(input);
        Task task = new Task(input);
        System.out.println(LINE);
        taskNameList.add(deadLine);
        String outputForDeadline = deadLine.guideline()
                + deadLine.getState()
                + task.numberOfTask(taskNameList.size());
        System.out.println(outputForDeadline);
        System.out.println(LINE);

    }
    public static void deleteMain(String input, String[] command){
        int indexTask = Integer.parseInt(command[1]);
        Task task = new Task(input);
        System.out.println(LINE);
        System.out.print("     Noted. I've removed this task:"
                + System.lineSeparator()
                + taskNameList.get(indexTask - 1).getState());
        taskNameList.remove(indexTask - 1);
        System.out.println(task.numberOfTask(taskNameList.size()));
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        greeting();
        Scanner userInput = new Scanner(System.in);

        while (true) {
            String taskName = userInput.nextLine();
            if (taskName.equalsIgnoreCase("bye")) {
                break;
            }
            try {
                inputValues(taskName);
            } catch (Exception e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }
        }
    }
}
