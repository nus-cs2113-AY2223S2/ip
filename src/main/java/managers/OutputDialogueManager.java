package managers;

import enums.DialogueTypes;
import enums.ErrorDialogueTypes;

public class OutputDialogueManager {
    public void printInteraction(DialogueTypes interactionType) {
        switch (interactionType) {
        case GREETINGS:
            System.out.println("<(0v0)/ Hey! Liri here!" + System.lineSeparator()
                    + "<(-v-)> How can I help you?");
            break;
        case LIST_TASKS:
            System.out.println("|(*O*)| Here's the tasks I remembered:");
            break;
        case MARK_TASK:
            System.out.println("*^(^v^)^* Great! I have marked this task as done:");
            break;
        case UNMARK_TASK:
            System.out.println("_(@_@)_ Hmmm. I shall mark this task as not done:");
            break;
        case DELETE_TASK:
            System.out.println("|(0o0)> Gocha! I will now delete this task:");
            break;
        case ADD_TASK:
            System.out.println("_(ovo)-| Added:");
            break;
        case HELP_MENU:
            System.out.println("(^A^)== Here's the list of commands you can tell me to do:"
                    + System.lineSeparator() + "     - To add a to-do task: todo taskName" + System.lineSeparator() +
                    "       (eg: todo borrow book)" + System.lineSeparator() +
                    "     - To add a task with deadline: deadline taskName /by deadline" +
                    System.lineSeparator() + "       (eg: deadline return book /by Sunday)" + System.lineSeparator() +
                    "     - To add an event: event eventName /from startTime /to endTime" +
                    System.lineSeparator() + "       (eg: event project meeting /from Mon 2pm /to 4pm)"
                    + System.lineSeparator() + "     - To look at the stored tasks: list"  + System.lineSeparator() +
                    "     - To mark/unmark a task as done: mark/unmark taskIndex" + System.lineSeparator() +
                    "       (eg: mark 1)" + System.lineSeparator() +
                    "     - To end this programme: bye");
            break;
        case COUNT_OF_TASKS:
            System.out.print("_(#o#)_| Currently, the number of task I remembered is: ");
            break;
        case FIND_TASK:
            System.out.println("|(*v*)| Here are all the matching tasks I found:");
            break;
        case GOODBYE:
            System.out.println("/(0A0)/ Bye. See you next time!");
            break;
        case UNKNOWN_TYPE:
            // fallthrough
        default:
            System.out.println("|(@A@)| I can't understand what you are talking about.");
            printInteraction(DialogueTypes.HELP_MENU);
            break;
        }
    }

    public void printErrorDialogue(ErrorDialogueTypes errorMessage) {
        switch (errorMessage) {
        case EMPTY_TASK_NAME:
            System.out.println("|(@A@)| The description seems to be empty or incomplete, please remember to fill it");
            break;
        case DEADLINE_WRONG_FORMAT:
            System.out.println("|(@A@)| I think your format for adding a new deadline task is wrong."
                    + System.lineSeparator() +
                    "(0v0)-- Here's an example of adding a deadline task: deadline return book /by Sunday");
            break;
        case EVENT_WRONG_FORMAT:
            System.out.println("|(@A@)| I think your format for adding an new event is wrong."
            + System.lineSeparator() + "(0v0)-- Here's an example of adding an event: " +
                    "event project meeting /from Mon 2pm /to 4pm");
            break;
        case TASK_NUMBER_OUT_OF_RANGE:
            System.out.println("|(@A@)| I don't remember any task of this number, please try a new one");
            break;
        case INVALID_TASK_NUMBER:
            System.out.println("|(@A@)| I don't think you have entered a number for the task number.");
            break;
        case NO_TASK_IN_LIST:
            System.out.println("|(#A#)| I don't remember any task, please add some following the format below: "
                    + System.lineSeparator() + "     - To add a to-do task: todo taskName" + System.lineSeparator() +
                    "       (eg: todo borrow book)" + System.lineSeparator() +
                    "     - To add a task with deadline: deadline taskName /by deadline" +
                    System.lineSeparator() + "       (eg: deadline return book /by Sunday)"
                    + System.lineSeparator() + "     - To add an event: event eventName /from startTime /to endTime" +
                    System.lineSeparator() + "       (eg: event project meeting /from Mon 2pm /to 4pm)");
            break;
        case ERROR_WHEN_SAVING:
            System.out.println("/(TAT)/  There seems to be some error when saving data...");
            break;
        case NO_MATCHING_TASK:
            System.out.println("|(@A@)| I don't see any task that contain the word you are searching.");
            break;
        default:
            System.out.println("|(TAT)| Some error that I do not understand have happened.");
            break;
        }
    }
}
