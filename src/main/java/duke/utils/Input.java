package duke.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import duke.exception.PromptCannotBeEmptyException;

public class Input {
    static Scanner sc = new Scanner(System.in);

    public static String scanWord() {
        String word = sc.next();
        return word;
    }

    public static String scanLine() {
        String line = sc.nextLine();
        return line;
    }

    public static String scanPrompt(String taskType) throws PromptCannotBeEmptyException {
        String prompt = "";
        try {
            prompt = sc.nextLine();
            if (prompt == "") {
                throw new PromptCannotBeEmptyException();
            } else {
                return prompt;
            }
        } catch (PromptCannotBeEmptyException e) {

            Output.printPromptEmptyError(taskType);
            return null;
        }
    }

    public static int scanTaskIndex(int tasksSize) {
        try {
            int indexTask = sc.nextInt() - 1;
            if (indexTask >= tasksSize) {
                throw new IndexOutOfBoundsException();
            } else {
                return indexTask;
            }

        } catch (InputMismatchException e) {
            Output.printTaskIndexNotIntegerError();
            scanLine();
            return -1;
        } catch (IndexOutOfBoundsException e) {
            Output.printTaskIndexOutOfBoundsError();
            return -1;
        }
    }

    public static String[] parseDeadline(String prompt) {
        try {
            int dividerPosition = prompt.indexOf("/by");
            String description = prompt.substring(0, dividerPosition).trim();
            String by = prompt.substring(dividerPosition + 3).trim();
            return new String[]{description, by};
        } catch (StringIndexOutOfBoundsException e) {
            Output.printIncorrectFormatError("event");
            return null;
        }
    }

    public static String[] parseEvent(String prompt) {
        try {
            int divider1Position = prompt.indexOf("/from");
            int divider2Position = prompt.indexOf("/to");
            String description = prompt.substring(0, divider1Position).trim();
            String from = prompt.substring(divider1Position + 5, divider2Position).trim();
            String to = prompt.substring(divider2Position + 3).trim();
            return new String[]{description, from, to};
        } catch (StringIndexOutOfBoundsException e) {
            Output.printIncorrectFormatError("event");
            return null;
        }
    }
}
