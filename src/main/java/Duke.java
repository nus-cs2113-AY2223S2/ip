import java.util.Scanner;

public class Duke
{
    public static final String GREETING = " Hello! I'm Duke\n"
            + " What can I do for you?";

    public static final String GOODBYE = " Bye. Hope to see you again soon!";

    public static final String LINE_DIVIDER = "____________________________________________________________";

    public static void lineBreak ()
    {
        System.out.println (LINE_DIVIDER);
    }

    public static void greet ()
    {
        lineBreak ();
        System.out.println (GREETING);
    }

    public static void goodbye ()
    {
        System.out.println (GOODBYE);
        lineBreak ();
    }

    public static void main (String[] args)
    {
        greet ();

        int index = 1;
        String line;
        Task[] tasks = new Task[102];
        Scanner in = new Scanner (System.in);
        lineBreak ();
        line = in.nextLine ();

        while (!line.equals ("bye")) {


            if (line.equals ("list")) {
                for (int i = 1; i < index; i++) {
                    System.out.println (i + ": " + tasks[i].getTaskStatus ());
                }
            } else if (line.length () > 5 && line.startsWith ("mark")) {
                int pointer = Integer.parseInt (line.substring (5));
                tasks[pointer].setDone ();
                System.out.println ("Nice! I've marked this task as done:\n" + tasks[pointer].getTaskStatus ());

            } else if (line.length () > 7 && line.startsWith ("unmark")) {
                int pointer = Integer.parseInt (line.substring (7));
                tasks[pointer].setNotDone ();
                System.out.println ("Yikes! I've marked this task as not done:\n" + tasks[pointer].getTaskStatus ());

            } else {
                System.out.println ("added: " + line);
                tasks[index] = new Task (line);
                index += 1;
            }

            lineBreak ();
            line = in.nextLine ();


        }

        goodbye ();

    }
}