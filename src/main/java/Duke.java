import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Greetings.introduction();
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.length() == 0) { 
                input = sc.nextLine(); 
                continue; 
            }
            
            System.out.println("\t------------------------------------------------------------");
            System.out.printf("\t%s\n", input);
            System.out.println("\t------------------------------------------------------------\n");
            input = sc.nextLine();
        }

        Greetings.goodbye();
    }
}
