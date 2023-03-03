package Duke;

import java.util.Scanner;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        Ui.printWelcome();
        TaskList.initializeTaskNumberAndList();
        run();
    }
    /***
     * Continuously runs and takes in commands until "bye" command has been issued.
     * @throws IndexOutOfBoundsException when illegal index of task was issued or insufficient command arguments.
     * @throws NumberFormatException when non-number strings were issued for number only arguments.
     * @throws DukeException
     */
    private static void run() {
        Scanner in = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                if (Parser.handleInput(in.nextLine())) {
                    isExit = true;
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Command used wrongly");
            } catch (NumberFormatException e) {
                System.out.println("Number only for argument");
            } catch (DukeException e) {
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
}
