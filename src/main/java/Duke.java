import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner io = new Scanner(System.in);
        ArrayList<String> textByUser = new ArrayList<String>();
        System.out.println("Hello! I am Duke");
        System.out.println("What can I do for you?");

        String inputt = io.nextLine();
        while (!inputt.equals("bye")) {
            //if list is the inputt, list all inputs thus far
            if (inputt.equals("list")) {
                for (int i = 0; i < textByUser.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + textByUser.get(i));
                }
                inputt = io.nextLine();
            } else { //else return a response and add the input to the list
                System.out.println("added: " + inputt);
                textByUser.add(inputt);
                inputt = io.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
