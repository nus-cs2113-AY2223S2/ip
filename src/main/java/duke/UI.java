package duke;

import duke.TaskList;

public class Ui {
    protected static String LINE = "---------------------------------------------------------";
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
        System.out.println("   > Sorry, command not found");
        System.out.println(LINE);
    }

    public static void listTasks(TaskList tasks) {
        System.out.println(LINE);
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println("   > " + Integer.toString(i + 1) + "." + tasks.getDescription(i));
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
        System.out.println(
                "Now you have " + Integer.toString(size) + " tasks in the list." + System.lineSeparator() + LINE);
    }
}
