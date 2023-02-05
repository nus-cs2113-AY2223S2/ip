package dev.joulev.archduke.io;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.UnknownException;
import dev.joulev.archduke.tasks.Store;
import dev.joulev.archduke.tasks.Task;

public class Out {
    private static final int BOX_WIDTH = 80;

    private static void printLineWithDelim(char leftDelim, char rightDelim) {
        System.out.print(leftDelim);
        for (int i = 0; i < BOX_WIDTH - 2; i++) {
            System.out.print(BoxDrawingCharacter.HORIZONTAL_LINE);
        }
        System.out.println(rightDelim);
    }

    private static void printBoxTopBorder() {
        printLineWithDelim(BoxDrawingCharacter.TOP_LEFT_CORNER,
                BoxDrawingCharacter.TOP_RIGHT_CORNER);
    }

    private static void printBoxBottomBorder() {
        printLineWithDelim(BoxDrawingCharacter.BOTTOM_LEFT_CORNER,
                BoxDrawingCharacter.BOTTOM_RIGHT_CORNER);
        System.out.println("");
    }

    private static void printBoxBottomBorder(boolean withBottomMargin) {
        printLineWithDelim(BoxDrawingCharacter.BOTTOM_LEFT_CORNER,
                BoxDrawingCharacter.BOTTOM_RIGHT_CORNER);
        if (withBottomMargin) {
            System.out.println("");
        }
    }

    private static void printBoxRightBorder(int unusedSpace) {
        // '<=' not '<' since we also have one space as padding
        for (int i = 0; i <= unusedSpace; i++) {
            System.out.print(' ');
        }
        System.out.println(BoxDrawingCharacter.VERTICAL_LINE);
    }

    /**
     * Print the logo with padding and all.
     * 
     * @see {@link https://patorjk.com/software/taag/#p=display&f=Slant&t=archduke}
     */
    private static void printLogo() {
        String left = BoxDrawingCharacter.VERTICAL_LINE + " ";
        String right = " " + BoxDrawingCharacter.VERTICAL_LINE + "\n";
        String[] lines = { "                   __        __      __      ",
                "  ____ ___________/ /_  ____/ /_  __/ /_____ ",
                " / __ `/ ___/ ___/ __ \\/ __  / / / / //_/ _ \\",
                "/ /_/ / /  / /__/ / / / /_/ / /_/ / ,< /  __/",
                "\\__,_/_/   \\___/_/ /_/\\__,_/\\__,_/_/|_|\\___/ ",
                "                                             " };
        int lineLength = lines[0].length();
        int availableSpace = BOX_WIDTH - lineLength - 4;
        int leftPadding = availableSpace / 2;
        int rightPadding = availableSpace - leftPadding;

        for (String line : lines) {
            System.out.print(left);
            for (int j = 0; j < leftPadding; j++) {
                System.out.print(' ');
            }
            System.out.print(line);
            for (int j = 0; j < rightPadding; j++) {
                System.out.print(' ');
            }
            System.out.print(right);
        }
    }

    /**
     * Prints a string inside a box, with box left and right borders at the start
     * and end of the line. The line automatically wraps around if necessary. This
     * assumes the box top and bottom borders are already drawn using
     * {@code printBoxTopBorder} and {@code printBoxBottomBorder}.
     * 
     * The parameters of this method is similar to {@code System.out.printf}.
     * 
     * @param format The string to print, with optional format specifiers.
     * @param args   The arguments to be formatted and substituted.
     */
    public static void printf(String format, Object... args) throws ArchdukeException {
        // Two chars for the borders, two chars for the padding, hence four chars gone.
        int maxStringWidth = BOX_WIDTH - 4;

        String input;
        try {
            input = String.format(format, args);
        } catch (Exception e) {
            throw new UnknownException("Out.printf; code = formatInput");
        }
        String[] words = input.split(" ");
        int currentLineLength = 0;

        System.out.print(BoxDrawingCharacter.VERTICAL_LINE + " ");
        for (String word : words) {
            if (word.length() > maxStringWidth) {
                word = word.substring(0, maxStringWidth - 3) + "...";
            }
            if (currentLineLength + word.length() > maxStringWidth) {
                printBoxRightBorder(maxStringWidth - currentLineLength);
                System.out.print(BoxDrawingCharacter.VERTICAL_LINE + " ");
                currentLineLength = 0;
            }
            System.out.print(word + ' ');
            currentLineLength += word.length() + 1;
        }
        printBoxRightBorder(maxStringWidth - currentLineLength);
    }

    public static void printBox(String format, Object... args) throws ArchdukeException {
        printBoxTopBorder();
        printf(format, args);
        printBoxBottomBorder();
    }

    public static void greet() throws ArchdukeException {
        printBoxTopBorder();
        printLogo();
        printf("Hello! I'm Archduke. What do you want to do?");
        printBoxBottomBorder();
    }

    public static void bye() throws ArchdukeException {
        printBoxTopBorder();
        printf("Bye. Hope to see you again soon!");
        printBoxBottomBorder(false);
    }

    public static void printError(String format, Object... args) throws ArchdukeException {
        printBox("ERROR: %s", String.format(format, args));
    }

    public static void printTasks(Store store) throws ArchdukeException {
        printBoxTopBorder();
        printf("Here are your tasks:");
        store.listTasks();
        printBoxBottomBorder();
    }

    public static void printQueriedTasks(Store store, String query) throws ArchdukeException {
        printBoxTopBorder();
        printf("Here are the tasks I found:");
        store.queryTasks(query);
        printBoxBottomBorder();
    }

    public static void printTaskAddition(Task task, int storeSize) throws ArchdukeException {
        printBoxTopBorder();
        printf("Added task:");
        printf("  %s", task.toString());
        printf("You now have %d task(s) in the list.", storeSize);
        printBoxBottomBorder();
    }

    public static void printTaskCompleteness(Store store, int index) throws ArchdukeException {
        Task task = store.getTask(index);
        printBoxTopBorder();
        printf("The following task has been marked as %s", task.isCompleted() ? "done" : "undone");
        printf("  %s", task.toString());
        printBoxBottomBorder();
    }

    public static void printTaskDeletion(Task task, int storeSize) throws ArchdukeException {
        printBoxTopBorder();
        printf("The following task has been deleted:");
        printf("  %s", task.toString());
        printf("You now have %d task(s) in the list.", storeSize);
        printBoxBottomBorder();
    }
}
