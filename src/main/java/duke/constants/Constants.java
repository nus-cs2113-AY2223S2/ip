package duke.constants;

public class Constants {

    public static final String LINEBREAK = "====================================================";

    public static final String MESSAGE_HELP = "Here are the commands you can use:\n"
            + "1. todo <task name> - Adds a todo task to the list.\n"
            + "2. deadline <task name> /by <YYYY-MM-DD> - Adds a deadline task to the list.\n"
            + "3. event <task name> /from <YYYY-MM-DD HHmm> /to <YYYY-MM-DD HHmm> - Adds an event task to the list.\n"
            + "4. list - Lists all tasks in the list.\n"
            + "5. mark <task number> - Marks a task as done.\n"
            + "6. unmark <task number> - Marks a task as not done.\n"
            + "7. find <keyword> - Finds tasks with the keyword.\n"
            + "8. delete <task number> - Deletes a task from the list.\n"
            + "9. help - Shows program usage instructions.\n"
            + "10. bye - Exits the program.\n"
            + LINEBREAK;

    public static final String INTRO = LINEBREAK + "\nHello I'm\n" +
            "    ____        _        \n" +
            "   |  _ \\ _   _| | _____ \n" +
            "   | | | | | | | |/ / _ \\\n" +
            "   | |_| | |_| |   <  __/\n" +
            "   |____/ \\__,_|_|\\_\\___|" + "\nWhat can I do for you?\n" +
            "Input your tasks and I'll keep track of them!\n" +
            "If you're unsure of how to use me, type \"help\" to see what I can do!\n" + LINEBREAK;
}
