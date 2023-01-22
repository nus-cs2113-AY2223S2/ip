import java.util.Scanner;

public class Duke {
    private static String[] texts = new String[100];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        int counter = 0;

        while (true) {
            String line = in.nextLine();
            if (line.equals("list")) {
                for (int i = 0; i < counter; ++i) {
                    System.out.printf("%d: %s\n", i + 1, texts[i]);
                }
            } else {
                texts[counter] = line;
                counter += 1;
                if (line.equals("bye")) {
                    System.out.println(line);
                    break;
                }
                System.out.println("added: " + line);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }
}
