import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input;
        String[] lst = new String[100];
        int index = 0;
        while (true) {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            if (input.equals("bye")) {
                // terminate
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                for (int i = 0; i < index; i++){
                    System.out.println(i + 1 +". " + lst[i] );
                }
            }

            else {
                // echo
                System.out.println("added: "+ input);
                lst[index] = input;
                index += 1;
            }
        }
    }
}
