//import org.eclipse.nebula.paperclips.core.LinePrint;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //LinePrint();

        greet();
    }

    public static void greet () {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}