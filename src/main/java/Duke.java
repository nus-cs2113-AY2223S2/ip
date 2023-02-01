import java.util.Scanner;

public class Duke
{
    /* immutable Strings below
     *
     *
     */
    public static final String GREETING = " Hello! I'm Duke! What can I do for you?";

    public static final String INVALID = " Invalid command!";
    public static final String GOODBYE = " Bye. Hope to see you again soon!";
    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static final int INT = 101;

    /* Storage and variables below
     *
     *
     */
    private static int indexBaseOne = 1;
    private static Task[] tasks;
    private static final Scanner SCANNER = new Scanner (System.in);


    public static void main (String[] args)
    {
        greet ();
        String line;
        initTasks ();
        line = SCANNER.nextLine ();

        while (true) {

            if (line.equals ("bye"))
            {
                goodbye ();
                System.exit (0);
            }

            if (line.equals ("list"))
            {
                for (int i = 1; i < indexBaseOne; i++) {
                    System.out.println (i + ": " + tasks[i].getTaskStatus ());
                }

            }
            else if (line.length () > 5 && line.startsWith ("mark"))
            {
                int pointer = Integer.parseInt (line.substring (5));
                tasks[pointer].setDone ();
                System.out.println ("Nice! I've marked this task as done:\n"
                        + tasks[pointer].getTaskStatus ());

            }
            else if (line.length () > 7 && line.startsWith ("unmark"))
            {
                int pointer = Integer.parseInt (line.substring (7));
                tasks[pointer].setNotDone ();
                System.out.println ("Yikes! I've marked this task as not done:\n"
                        + tasks[pointer].getTaskStatus ());

            }
            else if (line.length () > 5 && line.startsWith ("todo"))
            {
                System.out.println ("added: " + line);
                tasks[indexBaseOne] = new ToDo (line.substring (5));
                indexBaseOne += 1;
            }
            else if (line.length () > 9 && line.startsWith ("deadline"))
            {
                System.out.println ("added: " + line);
                final int indexOfBy = line.indexOf ("/by");
                tasks[indexBaseOne] = new DeadLine (line.substring (9, indexOfBy), line.substring (indexOfBy+4));
                indexBaseOne += 1;
            }
            else if (line.length () > 6 && line.startsWith ("event"))
            {
                System.out.println ("added: " + line);
                final int indexOfFrom = line.indexOf ("/from");
                final int indexOfTo = line.indexOf ("/to");
                tasks[indexBaseOne] = new Event (line.substring (6, indexOfFrom),
                        line.substring (indexOfFrom+6, indexOfTo),
                        line.substring (indexOfTo+4));
                indexBaseOne += 1;
            }
            else
            {
                System.out.println (INVALID);
            }

            lineBreak ();
            line = SCANNER.nextLine ();


        }
    }

    /* Methods below
     * note: can declare method after calling them as long as they are in same class
     * since calling any method technically does Class.method()
     *
     */
    public static void lineBreak ()
    {
        System.out.println (LINE_DIVIDER);
    }

    public static void greet ()
    {
        lineBreak ();
        System.out.println (GREETING);
        lineBreak ();
    }

    public static void goodbye ()
    {
        System.out.println (GOODBYE);
        lineBreak ();
    }

    public static void initTasks ()
    {
        tasks = new Task[INT];
    }


}