package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String LINE = "\t____________________________________________________________";
    static final String COMMAND_BYE = "bye";
    static final String COMMAND_LIST = "list";
    static final String COMMAND_MARK = "mark";
    static final String COMMAND_UNMARK = "unmark";
    static final String COMMAND_EVENT = "event";
    static final String COMMAND_TODO = "todo";
    static final String COMMAND_DELETE = "delete";
    static final String COMMAND_DEADLINE = "deadline";
    static ArrayList<Task> tasks = new ArrayList<>();
    static int textCount = 0;

    public static void printInvalidInput(String taskType) {
        System.out.println(LINE);
        System.out.println("\t☹ Error! Invalid input.");
        System.out.println("\tPlease provide a integer number for " + "\"" + taskType + "\" command.");
        System.out.println(LINE);
    }

    public static void doCommandGreet() {
        System.out.println(LINE);
        System.out.println("\tHello! I'm Duke.");
        System.out.println("\tHow can I help you today?\n");
        System.out.println(LINE);
    }

    public static void doCommandBye() {
        System.out.println(LINE);
        System.out.println("\tBye! Remember to finish your tasks.\n");
        System.out.println(LINE);
    }

    public static void doCommandMark(int taskNum) {
        try {
            tasks.get(--taskNum).setStatus(true);
            System.out.println(LINE);
            System.out.println("\tNoted. Task " + (taskNum + 1) + " has been marked as \"complete\":");
            System.out.println("\t  " + tasks.get(taskNum).getTaskNameAndStatus());
            System.out.println(LINE);
        } catch (IndexOutOfBoundsException | NullPointerException out_mark_b) {
            System.out.println(LINE);
            System.out.println("\t☹ Error! Invalid task number given.");
            System.out.println("\tPlease use \"list\" command to see your task numbers.");
            System.out.println(LINE);
        }
    }

    public static void doCommandUnmark(int taskNum) {
        try {
            tasks.get(--taskNum).setStatus(false);
            System.out.println(LINE);
            System.out.println("\tOh, ok. Task " + (taskNum + 1) + " has been marked as \"incomplete\":");
            System.out.println("\t  " + tasks.get(taskNum).getTaskNameAndStatus());
            System.out.println(LINE);
        } catch (IndexOutOfBoundsException | NullPointerException out_unmark_b) {
            System.out.println(LINE);
            System.out.println("\t☹ Error! Invalid task number given.");
            System.out.println("\tPlease use \"list\" command to see your task numbers.");
            System.out.println(LINE);
        }
    }

    public static void doCommandList() {
        System.out.println(LINE);
        int count = 1;
        if (textCount == 0) {
            System.out.println("\tYou have no pending tasks! ☺");
        } else {
            System.out.println("\tHere are your tasks:");
            for (int index = 0; index < textCount; index++) {
                System.out.print("\t" + count + ".");
                System.out.println(tasks.get(index));
                count++;
            }
        }
        System.out.println(LINE);
    }

    public static void doCommandTodo(String taskName) {
        tasks.add(new Todo(taskName));
        textCount++;
        System.out.println(LINE);
        System.out.println("\t" + "Task added!");
        System.out.println("\t  " + tasks.get(textCount - 1));
        System.out.println("\t" + "Now you have " + textCount + " pending tasks.");
        System.out.println(LINE);
    }

    public static void doCommandDeadline(String taskName, String taskDeadline) {
        tasks.add(new Deadline(taskName, taskDeadline));
        textCount++;
        System.out.println(LINE);
        System.out.println("\t" + "Task added!");
        System.out.println("\t  " + tasks.get(textCount - 1));
        System.out.println("\t" + "Now you have " + textCount + " pending tasks.");
        System.out.println(LINE);
    }

    public static void doCommandEvent(String eventName, String eventDetailsPartOne, String eventDetailsPartTwo) {
        tasks.add(new Event(eventName, eventDetailsPartOne, eventDetailsPartTwo));
        textCount++;
        System.out.println(LINE);
        System.out.println("\t" + "Task added!");
        System.out.println("\t  " + tasks.get(textCount - 1));
        System.out.println("\t" + "Now you have " + textCount + " pending tasks.");
        System.out.println(LINE);
    }

    public static void doCommandDelete(int taskNum) {
        System.out.println(LINE);
        System.out.println("\t" + "Task removed!");
        System.out.println("\t  " + tasks.get(taskNum - 1));
        System.out.println("\t" + "Now you have " + (textCount - 1) + " pending tasks.");
        tasks.remove(taskNum - 1);
        System.out.println(LINE);
        textCount--;
    }

    public static void handleUserCommand(String userCommand) {
        String[] extractFirstWord = userCommand.split(" ", 2);
        String firstWord = extractFirstWord[0];
        switch (firstWord) {
        case COMMAND_MARK: {
            try {
                int taskNum = Integer.parseInt(extractFirstWord[1]);
                doCommandMark(taskNum);
            } catch (IndexOutOfBoundsException out_mark_a) {
                System.out.println(LINE);
                System.out.println("\t☹ Error! \"mark\" command requires a task number.");
                System.out.println(LINE);
            } catch (NumberFormatException num_mark_a) {
                printInvalidInput("mark");
            }
            break;
        }
        case COMMAND_UNMARK: {
            try {
                int taskNum = Integer.parseInt(extractFirstWord[1]);
                doCommandUnmark(taskNum);
            } catch (IndexOutOfBoundsException out_unmark_a) {
                System.out.println(LINE);
                System.out.println("\t☹ Error! \"unmark\" command requires a task number.");
                System.out.println(LINE);
            } catch (NumberFormatException num_unmark_a) {
                System.out.println(LINE);
                System.out.println("\t☹ Error! Invalid input.");
                System.out.println("\tPlease provide a integer number for \"unmark\" command.");
                System.out.println(LINE);
            }
            break;
        }
        case COMMAND_LIST:
            doCommandList();
            break;
        case COMMAND_BYE:
            doCommandBye();
            break;
        case COMMAND_DELETE:
            try {
                int taskNum = Integer.parseInt(extractFirstWord[1]);
                doCommandDelete(taskNum);
            } catch (IndexOutOfBoundsException out_unmark_a) {
                System.out.println(LINE);
                System.out.println("\t☹ Error! \"delete\" command requires a task number.");
                System.out.println(LINE);
            } catch (NumberFormatException num_unmark_a) {
                System.out.println(LINE);
                System.out.println("\t☹ Error! Invalid input.");
                System.out.println("\tPlease provide a integer number for \"delete\" command.");
                System.out.println(LINE);
            }
            break;
        case COMMAND_TODO: {
            try {
                String taskName = (extractFirstWord[1]);
                doCommandTodo(taskName);
            } catch (IndexOutOfBoundsException out_todo_a) {
                System.out.println(LINE);
                System.out.println("\t☹ Error! \"todo\" command is empty.");
                System.out.println("\tPlease provide a description of the task.");
                System.out.println(LINE);
            }
            break;
        }
        case COMMAND_DEADLINE: {
            try {
                int index = extractFirstWord[1].indexOf("/by");
                String taskName = extractFirstWord[1].substring(0, index);
                String taskDeadline = extractFirstWord[1].substring(index + 4);
                doCommandDeadline(taskName, taskDeadline);

            } catch (StringIndexOutOfBoundsException out_deadline_a) {
                System.out.println(LINE);
                System.out.println("\t☹ Error! Invalid format for \"deadline\" command.");
                System.out.println(LINE);
            } catch (IndexOutOfBoundsException out_deadline_a) {
                System.out.println(LINE);
                System.out.println("\t☹ Error! \"deadline\" command is empty.");
                System.out.println("\tPlease provide details of task and due date/time.");
                System.out.println(LINE);
                break;
            }
        }
        case COMMAND_EVENT: {
            try {
                int indexOfEventDetailsPartOne = extractFirstWord[1].indexOf("/from");
                int indexOfEventDetailsPartTwo = extractFirstWord[1].indexOf("/to");
                String eventName = extractFirstWord[1].substring(0, indexOfEventDetailsPartOne);
                String eventDetailsPartOne = extractFirstWord[1].substring(indexOfEventDetailsPartOne + 6, indexOfEventDetailsPartTwo - 1);
                String eventDetailsPartTwo = extractFirstWord[1].substring(indexOfEventDetailsPartTwo + 4);
                doCommandEvent(eventName, eventDetailsPartOne, eventDetailsPartTwo);

            } catch (StringIndexOutOfBoundsException out_deadline_a) {
                System.out.println(LINE);
                System.out.println("\t☹ Error! Invalid format for \"event\" command.");
                System.out.println(LINE);
            } catch (IndexOutOfBoundsException out_event_a) {
                System.out.println(LINE);
                System.out.println("\t☹ Error! \"event\" command is empty.");
                System.out.println("\tPlease provide details of task and date/time.");
                System.out.println(LINE);
                break;
            }
        }
        default: {
            System.out.println(LINE);
            System.out.println("\t☹ Error! Please input a valid command!");
            System.out.println(LINE);
        }
        }
    }

    public static void main(String[] args) {
        doCommandGreet();
        Scanner in = new Scanner(System.in);
        String userCommand;
        do {
            userCommand = in.nextLine();
            handleUserCommand(userCommand);
        } while (!userCommand.equals(COMMAND_BYE));
    }
}
