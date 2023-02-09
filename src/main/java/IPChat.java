import java.util.Scanner;

public class IPChat {
    public static void main(String[] args) {

        // Level 2: Add, List
        System.out.println("Hello I'm IPChat, What can I do for you");
        System.out.println("------------------------------------------");

        String[] items = new String [100]; // array to store the items in the list
        Scanner input =  new Scanner(System.in);
        String statement = input.nextLine();
        int count = 0;

        while(!statement.equals("bye")) {
            System.out.println("------------------------------------------");
            if (statement.equals("list")) {
                for (int i = 0; i < count; i += 1) {
                    System.out.println((i + 1) + ". " + items[i]);
                }
                System.out.println("------------------------------------------");
            } else {
                items[count] = statement;
                count += 1;
                System.out.println("added: " + statement);
                System.out.println("------------------------------------------");
            }
            statement = input.nextLine();

        }
        System.out.println("------------------------------------------");
        System.out.println("Bye, Hope to see you soon");
        System.out.println("------------------------------------------");
    }
}
