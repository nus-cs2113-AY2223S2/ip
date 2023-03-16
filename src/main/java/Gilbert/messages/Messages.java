package Gilbert.messages;

public final class Messages {
    public final static String SPACE =
            " ______ ______ ______ ______ ______ ______ ______ ______ ______ ______\n" +
            "|______|______|______|______|______|______|______|______|______|______|";
    public final static String TASKS = "Here are the tasks in your list:\n";
    public final static String DONE = "Nice! I've marked this task as done:\n";
    public final static String UNDONE = "OK, I've marked this task as not done yet:\n";
    public final static String DEADLINE = "Got it. I've added this deadline:";
    public final static String TODO = "Got it. I've added this todo:";
    public final static String EVENT = "Got it. I've added this event:";
    public final static String BYE = SPACE +"\nBye. Hope to see you again soon!\n" +SPACE;
    public final static String GREET = SPACE + "\nHello! I'm Gilbert :D\n" + "What can I do for you?\n" + SPACE;
    public final static String EMPTY = "It seems you have not entered any task! Please add the task behind\n" + "the task type.\n" + SPACE;
    public final static String EMPTYLIST = "There are no tasks in your list!\n" + SPACE;
    public final static String ERROR = "It seems like you have entered an invalid command!\n" +
            "Please type 'help' to list the available Gilbert commands!\n" + SPACE;
    public final static String OUTOFBOUNDS = "You have entered an index that doesn't exist! Please try again.\n" + SPACE;
    public final static String FIND = "Here are some of the tasks that matches your keyword:";
    public final static String NOTFOUND = "There are no matching entries found!\n" + SPACE;
    public final static String INDEX = "Please input a proper index behind the command!\n" + SPACE;
    public final static String FILE = "Oops, no existing files found! A new save file has been created!";
    public final static String HELP = "Below are some of the commands that you can use, please type them out\n" +
            "in the same order (ignore the <> brackets):\n" +
            "\n" +
            "There are 3 types of tasks that can be inserted into Gilbert. They are:\n" +
            "todo \t\t\t|todo <task to be done>\n" +
            "event \t\t\t|event <task to be done>\n" +
            "deadline \t\t|deadline <task to be done> / <time>\n" +
            "\n" +
            "Following which, there are 6 commands that can be used in Gilbert:\n" +
            "list\t\t\t|lists out the existing tasks\n" +
            "mark <index>\t|marks the task at the index specified as done\n" +
            "unmark <index>\t|marks the task at the index specified as not done\n" +
            "help\t\t\t|displays this message\n" +
            "find <keyword>\t|shows the tasks containing the corresponding keyword\n" +
            "bye\t\t\t\t|exits Gilbert\n" + SPACE;

}
