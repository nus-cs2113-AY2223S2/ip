package duke.ui;

import java.util.Scanner;
import java.util.List;

import duke.command.CommandResult;
import duke.common.Messages;

public class TextUi {
    private static final Scanner scanner = new Scanner(System.in);
    public TextUi() {
    }

    public void printDivider() {
        System.out.println(Messages.DIVIDER);
    }

    public void printWelcomeMsg(String version) {
        System.out.println(Messages.SPACER + Messages.MSG_GREETINGS);
        System.out.println(Messages.SPACER + "Version: " + version);
    }

    public void printIntroMsg() {
        System.out.println(Messages.SPACER + Messages.MSG_INTRODUCTION);
    }

    public void printGoodByeMsg() {
        System.out.println(Messages.SPACER + Messages.MSG_GOODBYE);
    }

    public void printMessage(String msg) {
        System.out.println(Messages.SPACER + msg);
    }

    public void printMultiMessage(String... messages) {
        for (String msg : messages) {
            System.out.println(Messages.SPACER + msg);
        }
    }

    public void printResult(CommandResult result) {
        List<String> resultMessages = result.getResultMessages();
        for (String msg : resultMessages) {
            System.out.println(Messages.SPACER + msg);
        }
    }

    public String getUserCommand() {
        System.out.println(Messages.SPACER + "Enter command: ");
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println(Messages.SPACER + "Input is empty!");
            input = scanner.nextLine().trim();
        }
        return input;
    }
}
