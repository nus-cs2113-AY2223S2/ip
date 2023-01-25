import java.util.Scanner;

public class IO {
    Scanner scanner;
    static final int BOX_WIDTH = 80;

    IO() {
        this.scanner = new Scanner(System.in);
    }

    static void printLineWithDelim(char leftDelim, char rightDelim) {
        System.out.print(leftDelim);
        for (int i = 0; i < BOX_WIDTH - 2; i++) {
            System.out.print(BoxDrawingCharacter.HORIZONTAL_LINE);
        }
        System.out.println(rightDelim);
    }

    public static void printBoxTopBorder() {
        printLineWithDelim(BoxDrawingCharacter.TOP_LEFT_CORNER, BoxDrawingCharacter.TOP_RIGHT_CORNER);
    }

    public static void printBoxBottomBorder() {
        printLineWithDelim(BoxDrawingCharacter.BOTTOM_LEFT_CORNER, BoxDrawingCharacter.BOTTOM_RIGHT_CORNER);
    }

    static void printRightBorder(int unusedSpace) {
        // = since we also have one space as padding
        for (int i = 0; i <= unusedSpace; i++) {
            System.out.print(' ');
        }
        System.out.println(BoxDrawingCharacter.VERTICAL_LINE);
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
    public static void printf(String format, Object... args) {
        // Two chars for the borders, two chars for the padding, hence four chars gone.
        int maxStringWidth = BOX_WIDTH - 4;

        String input = String.format(format, args);
        String[] words = input.split(" ");
        int currentLineLength = 0;

        System.out.print(BoxDrawingCharacter.VERTICAL_LINE + " ");
        for (String word : words) {
            if (word.length() > maxStringWidth) {
                // I should probably handle this too, to cater for ridiculously long words that
                // chemists manage to come up with.
                throw new IllegalArgumentException("Word too long to fit in box: " + word);
            }
            if (currentLineLength + word.length() > maxStringWidth) {
                printRightBorder(maxStringWidth - currentLineLength);
                System.out.print(BoxDrawingCharacter.VERTICAL_LINE + " ");
                currentLineLength = 0;
            }
            System.out.print(word + ' ');
            currentLineLength += word.length() + 1;
        }
        printRightBorder(maxStringWidth - currentLineLength);
    }

    /**
     * As the name suggests, this reads the user input until the end of the line and
     * returns the input as string. However this function also prints a prompt
     * character to make it looks hackerish.
     * 
     * @return The user input as a string.
     */
    public String readUserInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }
}
