package Support;

import BasisSupport.CatchError;
import BasisSupport.DukeException;
import BasisSupport.Spliterator;

public class Ui {
    // This method is to greet the user when the program starts, and it is never used in the following time.
    public static void initialGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Spliterator.printSpliterator();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        Spliterator.printSpliterator();
    }
    // This method is used until the user want to terminate the program.
    public static void bye() {
        System.out.println(" Bye. Hope to see you again soon!");
        Spliterator.printSpliterator();
    }

    /**
     * This method links to the CatchError Class that will deal with some common cases of error
     *
     * @param line Just represents the line that user inputs into the system
     * @return whether there exists error or not
     */
    public static boolean exceptionCheck(String line) {
        try {
            CatchError.validateMeaningful(line.split(" ")[0]);
        } catch (DukeException e) {
            Spliterator.printSpliterator();
            System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            Spliterator.printSpliterator();
            return true;
        }
        try {
            CatchError.validateEmpty(line);
        } catch (DukeException e) {
            Spliterator.printSpliterator();
            System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
            Spliterator.printSpliterator();
            return true;
        }
        return false;
    }
}