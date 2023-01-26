import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String[] items = new String[100];
        int counter = 0;
        System.out.println("Hi, I'm bob");
        System.out.println("What's up");
        String word = reader.nextLine();
        while (!word.equals("bye")) {
            if (word.equals("list") && counter > 0) {
                for (int i = 1; i <= counter; i++) {
                    System.out.println(i + ". " + items[i-1]);
                }
                word = reader.nextLine();
            } else {
                System.out.println("added: " + word);
                items[counter] = word;
                counter += 1;
                word = reader.nextLine();
            }
        }
        System.out.println("Bye");
    }
}
