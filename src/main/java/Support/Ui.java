package Support;

import BasisSupport.CatchError;
import BasisSupport.DukeException;

public class Ui {
    public static void initialGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public static void bye() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    public static boolean exceptionCheck(String line) {
        try {
            CatchError.validateMeaningful(line.split(" ")[0]);
        } catch (DukeException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("____________________________________________________________");
            return true;
        }
        try {
            CatchError.validateEmpty(line);
        } catch (DukeException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println("____________________________________________________________");
            return true;
        }
        return false;
    }
}