public class Greeting {

    /**
     * Prints out the farewell message.
     *
     * @param isSinglish Whether the language setting is currently in Singlish
     */
    public static void sayGoodbye(boolean isSinglish) {
        if (isSinglish) {
            System.out.println("Ok bye bye, come back soon ah!");
        } else {
            System.out.println("Bye. Hope to see you again soon!");
        }
        printHorizontalLines(isSinglish);
    }

    /**
     * Prints out horizontal lines for formatting.
     *
     * @param isSinglish Whether the language setting is currently in Singlish
     */
    public static void printHorizontalLines(boolean isSinglish) {
        if (isSinglish) {
            System.out.println("************************************************************");
        } else {
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Prints out the greeting message.
     *
     * @param isSinglish Whether the language setting is currently in Singlish
     */
    public static void sayHello(boolean isSinglish) {
        printHorizontalLines(isSinglish);
        if (isSinglish) {
            System.out.println("Hello, my name is Uncle Simon, call me Simon can liao");
            System.out.println("You need my help?");
            System.out.println("(To turn off Singlish mode, type \"change lang\")");
        } else {
            System.out.println("Hello, I'm Duke.");
            System.out.println("What can I do for you?");
            System.out.println("(To turn on Singlish mode, type \"change lang\").");
        }
        printHorizontalLines(isSinglish);
    }

    /**
     * Prints out a message informing the user that it has typed a command with invalid syntax
     *
     * @param isSinglish Whether the language setting is currently in Singlish
     */
    public static void warnWrongSyntax(boolean isSinglish) {
        if (isSinglish) {
            System.out.println("Eh you typed wrongly, can try typing again?");
        } else {
            System.out.println("Invalid syntax, please try again");
        }
    }

    /**
     * Prints out a message informing the user that language has changed
     *
     * @param isSinglish Whether the language setting is currently in Singlish
     */
    public static void sayChangeLanguage(boolean isSinglish) {
        if (isSinglish) {
            System.out.println("You want Duke to help you instead? Can can I go call him now");
            System.out.println("Singlish mode = OFF");
        } else {
            System.out.println("Changing language mode to Singlish...");
            System.out.println("Singlish mode = ON");
        }
        Greeting.sayHello(isSinglish);
    }

    /**
     * Prints out a message informing the user that the index selected does not have a task
     *
     * @param isSinglish Whether the language setting is currently in Singlish
     */
    public static void warnOutOfRange(boolean isSinglish) {
        if (isSinglish) {
            System.out.println("Eh, either you type wrongly or your list dun have a task at that index lah");
        } else {
            System.out.println(("Command inputted has wrong syntax or the list does not have a task of that index"));
        }
    }

    /**
     * Prints out a message informing the user that the task has been sucessfully updated
     *
     * @param isSinglish Whether the language setting is currently in Singlish
     */
    public static void sayUpdatedTask(boolean isSinglish) {
        if (isSinglish) {
            System.out.println("Ok I updated it:");
        } else {
            System.out.println("Updated the following task:");
        }
    }

    /**
     * Prints out a message informing the user that the task description is empty
     *
     * @param isSinglish Whether the language setting is currently in Singlish
     */
    public static void warnEmptyDesc(boolean isSinglish) {
        if (isSinglish) {
            System.out.println("Eh, cannot have empty task la");
        } else {
            System.out.println("Empty task, please try again.");
        }
    }

    /**
     * Prints out a message informing the user that the task has been added to the list
     *
     * @param isSinglish Whether the language setting is currently in Singlish
     */
    public static void sayAddToList(boolean isSinglish) {
        if (isSinglish) {
            System.out.println("Ok, I add in already");
        } else {
            System.out.println("Task has been added to the list.");
        }
    }

    /**
     * Prints out a message informing the user that the task at a specified index has been deleted
     *
     * @param isSinglish Whether the language setting is currently in Singlish
     */
    public static void sayDeleteTaskFromList(boolean isSinglish) {
        if (isSinglish) {
            System.out.println("Ok, I delete it already");
        } else {
            System.out.println(("Task has been removed from the list"));
        }
    }


    /**
     * Toggles the language setting between normal and Singlish mode.
     * Prints to the output the changes made.
     */
    public static void changeLanguage() {
        Duke.isSinglish = !Duke.isSinglish;
        sayChangeLanguage(Duke.isSinglish);
    }
}
