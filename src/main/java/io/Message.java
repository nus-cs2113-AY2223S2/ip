package io;

public enum Message {

    LINE("____________________________________________________________"),
    WELCOME(" Hello! I'm Duke\n     What can I do for you?"),
    GOODBYE(" Bye. Hope to see you again soon!"),
    TASK_ADDED(" Got it. I've added this task:\n"),
    TASK_COUNT(" Now you have %d tasks in the list.\n"),
    TASK_LIST(" Here are the tasks in your list:"),
    TASK_MARKED(" Nice! I've marked this task as done:"),
    TASK_UNMARKED(" OK, I've marked this task as not done yet:"),

    // Errors
    ERR_MAX_TASKS_EXCEEDED(" Too many tasks!"),
    ERR_INVALID_INDEX(" Invalid index!"),
    ERR_KEY_NOT_FOUND(" You missed out your \"%s\" argument!"),
    ERR_UNKNOWN_COMMAND(" Sorry I don't recognise that command."),
    ERR_UNKNOWN_EXCEPTION(" Something went wrong: ");



    private final String text;

    Message(String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return text;
    }
}
