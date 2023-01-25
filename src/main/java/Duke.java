import java.util.Scanner;
import java.util.Random;

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
        String[] list = new String[100];
        // chatbot active
        while (!word.equals("bye")) {
            System.out.println(spacer);
            if (word.equals("list")) {
                DukeCommands.listTasks(list);
            } else {
                DukeCommands.addToList(word, list);
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
            System.out.println("Nooo. Free me..." + spacer);
            break;
        }
    }
}
