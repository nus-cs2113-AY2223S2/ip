public class OutputDialogueManager {
    public static void printInteraction(DialogueTypes interactionType) {
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
        case ADD_TASK:
            System.out.println("_(ovo)-| Added: ");
            break;
        case HELP:
            System.out.println("(^A^)== Here's the list of commands you can tell me to do:");
            System.out.println("     - To add a to-do task: todo taskName" + System.lineSeparator() +
                    "       (eg: todo borrow book)");
            System.out.println("     - To add a task with deadline: deadline taskName /by deadline" +
                    System.lineSeparator() + "       (eg: deadline return book /by Sunday)");
            System.out.println("     - To add an event: event eventName /from startTime /to endTime" +
                    System.lineSeparator() + "       (eg: event project meeting /from Mon 2pm /to 4pm)");
            System.out.println("     - To look at the stored tasks: list");
            System.out.println("     - To mark/unmark a task as done: mark/unmark taskIndex" + System.lineSeparator() +
                    "       (eg: mark 1)");
            System.out.println("     - To end this programme: bye");
            break;
        case COUNT_OF_TASKS:
            System.out.print("_(#o#)_| Currently, the number of task I remembered is: ");
            break;
        case GOODBYE:
            System.out.println("/(0A0)/ Bye. See you next time!");
            break;
        default:
            System.out.println("|(@A@)| I can't understand what you are talking about.");
            break;
        }
    }

}
