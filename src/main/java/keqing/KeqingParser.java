package keqing;

import keqing.exceptions.IllegalInputException;

import java.io.IOException;

public class KeqingParser {
    /**
     * Interprets the given command typed by the user.
     *
     * @param text the one-line command that the user typed in
     * @throws IllegalInputException
     */
    public static void doCommand(String text) throws IllegalInputException {
        String[] splittedText = text.split(" ", 2);
        String command = splittedText[0];
        String content = splittedText[splittedText.length - 1];
        switch (command) {
        case "list":
            KeqingArrayList.printTaskList();
            break;
        case "menu":
            KeqingUI.printMenu();
            break;
        case "mark":
            //Fallthrough
        case "unmark":
            int currentID = Integer.parseInt(text.substring(text.length() - 1)) - 1;
            boolean isDone;
            isDone = !text.contains("unmark");
            KeqingArrayList.markTask(currentID, isDone);
            break;
        case "todo":
            KeqingArrayList.addToDo(content);
            break;
        case "deadline":
            KeqingArrayList.addDeadline(content);
            break;
        case "event":
            KeqingArrayList.addEvent(content);
            break;
        case "delete":
            KeqingArrayList.deleteTask(content);
            break;
        case "find":
            KeqingArrayList.findTask(content);
            break;
        default:
            throw new IllegalInputException("Keqing doesn't understand your input...?");
        }
        try {
            KeqingStorage.updateFile(KeqingArrayList.tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * To check whether a given string is numeric.
     *
     * @param str the given string
     * @return a boolean variable indicating whether the string is numeric
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}