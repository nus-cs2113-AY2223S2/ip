public class Messages {
    public static final String DIVIDER = "________________________________________________________________________________";
    public static final String GREETING = "Hello there! I'm Buddy\n"
            + "How may I assist you?";
    public static final String INTRODUCTION = "Here are the possible commands: todo, deadline, event, list, mark, unmark, find, bye\n" + "Type <help> if you are unsure of what these commands do!";
    public static final String EXITMESSAGE = "Hope I was of help to you! Have a great day and see you again, Buddy :)";
    public static final String HELPMESSAGE =
            "These are the various commands and their functions. Please type in the FORMAT that is stated below\n" +
            "There are 3 add task commands which add 3 kinds of tasks: todo, deadline and event\n" +
            "todo: Adds a todo to your task list\n" +
            "FORMAT: todo <todo name>\n" +
            "deadline: Adds a deadline to your task list\n" +

            "FORMAT: deadline <deadline name> /by <YYYY-MM-DD> (where YYYY is year, MM is month and DD is day)\n" +
            "event: Adds an event to your task list\n" +
            "FORMAT: event <event name> /from <start> /to <end>\n" +
            "There are 5 other action commands: list, mark, unmark, find and bye\n" +
            "list: lists all the tasks that you have remaining\n" +
            "FORMAT: list\n" +
            "mark: marks a task as done with an X\n" +
            "FORMAT: mark <task number on the list>\n" +
            "unmark: marks a task as not done\n" +
            "FORMAT: unmark <task number on the list>\n" +
            "find: finds a task which contains the keyword that you typed\n" +
            "FORMAT: find <matching keyword>\n" +
             "bye: exits the application\n" +
             "FORMAT: bye\n" +
             "Hope that this was useful!";
}
