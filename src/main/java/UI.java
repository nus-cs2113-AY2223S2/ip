/**
 * Represents UI elements a Duke User will see.
 * It also contains a <code>greet</code> and <code>bye</code> function that are executed
 * upon start up and shut down.
 */
public class UI {
    static String LINE = "____________________________________________________________\n";
    public UI() {
    }

    public static void greet(){
        System.out.println(LINE +" Hello! I'm Duke\n" + " What can I do for you?\n" +LINE);
    }


    public static void bye(){
        System.out.println(LINE + " Bye. Hope to see you again soon!\n" + LINE);
        Duke.continueRunning = false;
    }
}
