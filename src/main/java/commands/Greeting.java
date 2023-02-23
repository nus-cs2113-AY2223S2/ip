package commands;

import constants.constant;

public class Greeting {
    public static void greeting() {
        System.out.print("Today's Date: ");
        System.out.println(constant.todayDate);
        System.out.println("Hello from\n" + constant.LOGO);
        System.out.println(constant.HORIZONTAL_LINE + "\n");
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?\n");
        System.out.println("If you wish to view your past entries please enter \"list\" ");
        System.out.println(constant.HORIZONTAL_LINE + "\n");
    }
}
