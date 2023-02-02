import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Task[] items = new Task[100];
        int counter = 0;
        System.out.println("Hi, I'm bob");
        System.out.println("What's up");
        String word = reader.nextLine();
        while (!word.equals("bye")) {
            if (word.equals("list") && counter > 0) {
                for (int i = 1; i <= counter; i++) {
                    System.out.println(i + ".[" + items[i-1].getStatusIcon() + "] " + items[i-1].description);
                }
            } else if (word.contains("mark")) {
                String[] markArgs = word.split(" ");
                int taskNo = Integer.parseInt(markArgs[1]);
                if (markArgs[0].equals("unmark")) {
                    items[taskNo-1].unmarkAsDone();
                    System.out.println("This task is not done: " + "[" + items[taskNo-1].getStatusIcon() +
                        "] " + items[taskNo-1].description);
                } else {
                    items[taskNo-1].markAsDone();
                    System.out.println("This task is done: " + "[" + items[taskNo-1].getStatusIcon() + "] " +
                        items[taskNo-1].description);
                }
            } else {
                System.out.println("added: " + word);
                items[counter] = new Task(word);
                counter += 1;
            }
            word = reader.nextLine();
        }
        System.out.println("Bye");
    }
}
