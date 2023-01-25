import  java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner io = new Scanner(System.in);
        String inputt = io.nextLine();
        while (!inputt.equals("bye")) {
            System.out.println(inputt);
            inputt = io.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
