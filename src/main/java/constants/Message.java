package constants;

/**
 * An enum that contains the messages that will be mentioned by the chat bot
 */
public enum Message {
    TASK_ADDED("Got it. I've added this task:"),
    GOODBYE("Bye. Hope to see you again soon!"),
    LIST_NUMBER("Now you have %d tasks in the list.\n"),
    LIST_TASKS("Here are the tasks in your list:"),
    MARKED("Nice! I've marked this task as done:"),
    UNMARKED("OK, I've marked this task as not done yet:"),
    WELCOME("Hello! I'm Duke\nWhat can I do for you?\n");

    public final String message;

    Message(String message) {
        this.message = message;
    }
}
