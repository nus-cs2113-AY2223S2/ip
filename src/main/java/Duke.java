import java.util.Scanner;

public class Duke
{
    public static void main (String[] args)
    {
        DukeMessage.greet ();

        int index = 1;
        String line;
        Task[] tasks = new Task[102];
        Scanner in = new Scanner (System.in);
        DukeMessage.lineBreak ();
        line = in.nextLine ();

        while (!line.equals ("bye")) {


            if (line.equals ("list")) {
                for (int i = 1; i < index; i++) {
                    System.out.println (i + ": " + tasks[i].getTaskStatus ());
                }
            } else if (line.length () > 5 && line.substring (0, 4).equals ("mark")) {
                int pointer = Integer.parseInt (line.substring (5));
                tasks[pointer].setDone ();
                System.out.println ("Nice! I've marked this task as done:\n" + tasks[pointer].getTaskStatus ());

            } else if (line.length () > 7 && line.substring (0, 6).equals ("unmark")) {
                int pointer = Integer.parseInt (line.substring (7));
                tasks[pointer].setNotDone ();
                System.out.println ("Yikes! I've marked this task as not done:\n" + tasks[pointer].getTaskStatus ());

            } else {
                System.out.println ("added: " + line);
                tasks[index] = new Task (line);
                index += 1;
            }

            DukeMessage.lineBreak ();
            line = in.nextLine ();


        }

        DukeMessage.goodbye ();

    }
}