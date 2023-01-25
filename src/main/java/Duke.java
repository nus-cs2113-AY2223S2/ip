import java.util.Scanner;
import java.util.Random;
// import java.util.Arrays;

public class Duke extends DukeCommands{
    public static void main(String[] args) {
        // Boot sequence
        Random rand = new Random();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + spacer);
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?\n" + spacer);
        Scanner sc = new Scanner(System.in);

        String word = sc.nextLine();
        // chatbot active
        while (!word.equals("bye")) {
            System.out.println(spacer);
            if (word.equals("list")) {
                DukeCommands.listTasks();
            } else if (word.startsWith("mark ") && word.substring(5).matches("[0-9]{1,3}")) {
                markTask(Integer.parseInt(word.substring(5)));
            } else if (word.startsWith("unmark ") && word.substring(7).matches("[0-9]{1,3}")) {
                unmarkTask(Integer.parseInt(word.substring(7)));
            } else {
                DukeCommands.addToList(word);
            }
            word = sc.nextLine();
        }
        System.out.println(spacer);
        sc.close();

        // Exit sequence
        switch (rand.nextInt() % 2) {
        case 0:
            System.out.println(" Bye. Hope to see you again soon!\n" + spacer);
            break;

        case 1:
            System.out.println("Argh. I don\'t feel so good...\n" + spacer);
            break;
        
        default:
            System.out.println("Nooo. Free me...\n" + spacer);
            break;
        }
    }
}
