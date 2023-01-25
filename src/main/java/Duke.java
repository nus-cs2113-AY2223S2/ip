import java.util.Scanner;
import java.util.Random;

public class Duke {
    public static void main(String[] args) {
        Random rand = new Random();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String spacer = "__________________________________________\n";
        System.out.println("Hello from\n" + logo + spacer);
        
        // print hello greeting
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?\n" + spacer);

        // print input and exit condition
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        while (!word.equals("bye")) {
            System.out.println(spacer);
            System.out.println(word + "\n" + spacer);
            word = sc.nextLine();
        }
        System.out.println(spacer);
        sc.close();

        //print exit greeting
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
