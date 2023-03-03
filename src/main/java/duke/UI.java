package duke;

import duke.commands.Task;
import java.util.ArrayList;

public class Ui {
    protected static final String LINE = "---------------------------------------------------------";
    protected static final String KEQING = """
                    ....,***//*,,,,,,,**,,,,,,,,,,,,.......................
                    .,*%&&,.......,...
                    ..,%%&&&%.    ..                            .
                    .,,%%&&&&&&#,.. ../((/....       /     .####(
                      .#%%&&&&&&%%%%#############(#,..##########(
                       /#%%%&%%%%#####################%%%#%%%##(.
                       .############################%###&%%%%##*
                       (#((((##############((((######(####%####
                      ,/((((####(###((((((((((((##(((#########.
                     ./(((((#(#####(((((((((/(((#(/((///(((##//
                   .*//((((((((((#((((((********#//////(//(##//.    .
                ,,** */#((((((((#(/***/*********,%/**//#//////.     .
                     //#(((((((#/***%%********/...,****(/////.
                     //((((//(.***,/.*,,,,,/,*.*,...***(*///(.
                    ////***.##(###..,*,,, (*.........****///%#
                   ////*(*/(, ((##,*(,      (/.#####..**///(##
                   /(#***(**   **            //(/  /.,*/***&##
                  /(((**,****                      (******(#((.
                 /(((#%*#,##.                      **,***((((((
                ////###/,,%# .                      ,,,*#((((((
               /////####,*,*((((/             *    .,,,/#((((((.
            ..//////(###(&*.*.*((((/     */((((*   ,,/%(((((((((.
            ./(//////###/((,.  ,#(#../(..((*.     ,,,#(/////////(     .
            /(////////##//.   ,####% , %##((*....,...///////////(/..,,.
                """;

    public Ui() {}

    public static void printHello() {
        System.out.println(LINE);
        System.out.println(KEQING);
        System.out.println("Hello! I'm keqing" + System.lineSeparator() + "What can I do for you?");
        System.out.println(LINE);
    }

    public static void printBye() {
        System.out.println(LINE);
        System.out.println("    > Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void printNoCommand() {
        System.out.println(LINE);
        System.out.print("   > Sorry, command not found" + System.lineSeparator() + "enter again: ");
        System.out.println(LINE);
    }

    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println(LINE);
        if (tasks.size() == 0) {
            System.out.println("No task found");
            System.out.println(LINE);
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("   > " + Integer.toString(i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(LINE);
    }

    public static void showMark(String task) {
        System.out.println(LINE);
        System.out.println("   > Nice! I've marked this task as done:");
        System.out.println("   > " + task);
        System.out.println(LINE);
    }

    public static void showUnmark(String task) {
        System.out.println(LINE);
        System.out.println("   > Noted. I've unmarked this task:");
        System.out.println("   > " + task);
        System.out.println(LINE);
    }

    public static void showDelete(String task, int size) {
        System.out.println(LINE + System.lineSeparator() + "Noted. I've removed this task:");
        System.out.println("   > " + task);
        System.out.println("Now you have " + Integer.toString(size-1) + " tasks in the list."
                + System.lineSeparator() + LINE);
    }

    public static void showAddTask(String taskDiscription, int size) {
        System.out.println(LINE + System.lineSeparator() + "Got it. I've added this task:");
        System.out.println("   > " + taskDiscription);
        System.out.println("Now you have " + Integer.toString(size-1) + " tasks in the list."
                + System.lineSeparator() + LINE);
    }
}
