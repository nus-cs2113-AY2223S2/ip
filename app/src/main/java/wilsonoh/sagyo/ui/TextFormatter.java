package wilsonoh.sagyo.ui;

import static wilsonoh.sagyo.ui.ColorCodes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import wilsonoh.sagyo.tasks.Task;

/**
 * Helper class for printing out formatted text
 */
public class TextFormatter {

    public static final int DEFAULT_MAX_WIDTH = 50;
    private int numIndent;
    private int maxWidth;

    public TextFormatter(int indentation, int maxWidth) {
        this.numIndent = indentation;
        this.maxWidth = maxWidth;
    }

    public TextFormatter(int indentation) {
        this.numIndent = indentation;
        this.maxWidth = DEFAULT_MAX_WIDTH;
    }

    /**
     * Clear screen method which only works on unix systems for now
     */
    private void clearScreen() {
        System.out.print("\033\143");
    }

    public static String[] getFormattedTaskStrings(ArrayList<Task> tasks) {
        return IntStream.rangeClosed(1, tasks.size())
            .mapToObj(idx -> String.format("%d: %s", idx, tasks.get(idx - 1)))
            .toArray(String[] ::new);
    }

    /**
     * Prints lines of text and prints them in a nice little box
     *
     * Partially adapted from https://stackoverflow.com/a/58780542 with
     * a little python to java translation (java string formatting isn't the best...)
     *
     * @param lines Variable argument. Takes in any number of lines to be printed in a box
     */
    public void printLines(String... lines) {
        clearScreen();
        System.out.println(BLUE);
        String indentation = " ".repeat(numIndent);
        // Stream and map the lines into their respectives lengths, and then get the max length
        // while making sure it does not exceed the maxWidth set
        int textWidth = Math.min(Arrays.stream(lines).mapToInt(String::length).max().orElse(0), maxWidth);
        StringBuilder toPrint = new StringBuilder();
        toPrint.append(String.format("┌%s┐\n", "─".repeat((numIndent * 2) + textWidth)));
        for (String line : lines) {
            if (line.length() > maxWidth - 3) {
                line = line.substring(0, maxWidth - 4);
                line += "...";
            }
            String format = String.format("│%%s%%-%ds%%s│\n", textWidth);
            toPrint.append(String.format(format, indentation, line, indentation));
        }
        toPrint.append(String.format("└%s┘\n", "─".repeat((numIndent * 2) + textWidth)));
        System.out.println(toPrint);
        System.out.println(RESET);
    }
}
