import java.util.Scanner;

public class IPChat {
    public static void main(String[] args) {
        // Level 1: Greet Echo exit
        Scanner input =  new Scanner(System.in);
        System.out.println("Hello I'm IPChat, What can I do for you");
        System.out.println("------------------------------------------");

        String statement = input.next();
        while(!statement.equals("bye")) {
            System.out.println(statement);
            System.out.println("------------------------------------------");
            statement = input.next();
        }
        System.out.println("Bye, Hope to see you soon");
        System.out.println("------------------------------------------");
    }
}
