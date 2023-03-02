package constants;

/**
 * An interface that contains the usual automated messages mentioned by the chatbot.
 */
public interface Message {
    String GOODBYE = "Bye. Hope to see you again soon!";
    String FIND_TASK = "Here are the matching tasks in your list:";
    String LIST_NUMBER = "Now you have %d tasks in the list.\n";
    String LIST_TASKS = "Here are the tasks in your list:";
    String MARKED = "Nice! I've marked this task as done:";
    String TASK_ADDED = "Got it. I've added this task:";
    String UNMARKED = "OK, I've marked this task as not done yet:";
    String WELCOME = "Hello! I'm Duke\nWhat can I do for you?\n";
}
