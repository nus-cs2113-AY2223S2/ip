import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        String line;
        String[] texts = new String[100];
        int textsIndex = 0;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        while (!line.equals("bye")) {
            if (!line.equals("list")) {
                // store the text
                texts[textsIndex] = line;
                textsIndex++;

                // let the user know that we stored the text
                System.out.println("added: " + line);
            } else { // if line is list, we need to list out what there iss
                for (int i = 0; i < textsIndex; i++) {
                    System.out.print(i + 1);
                    System.out.println(". " + texts[i]);
                }
            }


            // read the next line
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        return;
    }
}
