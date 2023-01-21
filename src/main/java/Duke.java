import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("\u2500".repeat(50));
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("\u2500".repeat(50));

        Scanner myObj = new Scanner(System.in);

        String bye = "bye";
        boolean isSame = false;

        while (!isSame) {
            String task = myObj.nextLine();
            if (task.equals(bye)){
                System.out.println("\u2500".repeat(50));
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("\u2500".repeat(50));
                isSame = true;
            }
            else {
                System.out.println("\u2500".repeat(50));
                System.out.println(task); // Output user input
                System.out.println("\u2500".repeat(50));
            }
        }
    }
}
