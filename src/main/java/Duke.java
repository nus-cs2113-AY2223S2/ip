import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Hi, I'm bob");
        System.out.println("What's up");
        String word = reader.nextLine();
        while (!word.equals("bye")) {
            System.out.println(word);
            word = reader.nextLine();
        }
        System.out.println("Bye");
    }
}
