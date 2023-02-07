package commands;

import constants.constant;

public class Bye extends Command {
    public static void bye() {
        System.out.println(constant.HORIZONTAL_LINE + "\n");
        System.out.println("Bye! See you next time!");
        System.out.println(constant.HORIZONTAL_LINE + "\n");
    }
}
