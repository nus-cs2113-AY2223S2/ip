package commands;

import constants.constant;

public class Help {

    /**
     * provide help message to user
     */
    public static void getHelpMessage(){
        System.out.println("If you wish to view all tasks -Enter: \n" +
                "\nlist");
        System.out.println(constant.DASH_LINE);
        System.out.println("If you wish to add a ToDo task -Enter: \n" +
                "\ntodo <task description>");
        System.out.println(constant.DASH_LINE);
        System.out.println("If you wish to add a Deadline task -Enter: \n" +
            "\ndeadline <task description> /by <deadline date DD/MM/YYYY>");
        System.out.println(constant.DASH_LINE);
        System.out.println("If you wish to add a Event task -Enter: \n" +
                "\nevent <task description> /from <start date> /to <end date> ");
        System.out.println(constant.DASH_LINE);
        System.out.println("If you wish to mark a task as Done -Enter: \n" +
                "\nmark <task number>");
        System.out.println(constant.DASH_LINE);
        System.out.println("If you wish to undo a mark -Enter: \n" +
                "\nunmark <task number>");
        System.out.println(constant.DASH_LINE);
        System.out.println("If you wish to delete a task -Enter: \n" +
                "\ndelete <task number>" + "\nOR delete all (delete all tasks)");
        System.out.println(constant.DASH_LINE);
        System.out.println("If you wish to find tasks -Enter: \n" +
                "\nfind <task description>");
        System.out.println(constant.DASH_LINE);
        System.out.println("If you wish to exit the program -Enter: \n" +
                "\nbye");
        System.out.println(constant.HORIZONTAL_LINE);
    }
}
