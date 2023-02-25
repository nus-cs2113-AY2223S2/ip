package keqing;

import keqing.exceptions.IllegalInputException;

import java.io.IOException;
import java.util.Scanner;

public class Keqing {

    public static final String LINE = "____________________________________________________________\n";

    /**
     * Loops the user commands until the user types "bye".
     */
    public static void loopCommand() {
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        while(!text.equals("bye")){
            try {
                KeqingParser.doCommand(text);
            } catch (IllegalInputException e) {
                System.out.println(LINE + System.lineSeparator() + e + System.lineSeparator()
                        + "if you need more instruction, please type 'menu'."+ System.lineSeparator() + LINE);
            }
            text = in.nextLine();
        }
    }


    /**
     * The main method that drives Keqing.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        KeqingUI.printStartingGreet();
        try {
            KeqingArrayList.tasks = KeqingStorage.loadFile();
        } catch (IOException e) {
            System.out.println(LINE + System.lineSeparator() + e + System.lineSeparator());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE + System.lineSeparator() + e + System.lineSeparator());
            KeqingStorage.deleteStorage();
        }
        loopCommand();
        KeqingUI.printExitingGreet();
    }
}
