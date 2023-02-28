package Duke;

import Duke.Exceptions.DukeException;

import java.util.Scanner;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        Ui.printWelcome();
        TaskList.initializeTaskNumber(TaskList.taskList);
        run();
    }

    private static void run() {
        Scanner in = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                if (Parser.handleInput(in.nextLine())) {
                    isExit = true;
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Wrong index");
            } catch (NumberFormatException e) {
                System.out.println("Number only for argument");
            } catch (DukeException e) {
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
}
