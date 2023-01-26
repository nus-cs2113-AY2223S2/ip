import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "___________________________________________";

        System.out.println("\t" + line);
        System.out.println("\t Hello I'm Duke, your personal chatbot.");
        System.out.println("\t Is there anything I can do for you");
        System.out.println("\t" + line);

        String input = "";
        Scanner in = new Scanner(System.in);

        while(true) {
            input = in.nextLine().trim();
           
            if (input.equals("bye")) {
                System.out.println("\t" + line);
                System.out.println("Bye! Do let me know if you need any further assistance");
                System.out.println("\t" + line);
                break;
            }

            System.out.println("\t" + line);
            System.out.println("\t" + input);
            System.out.println("\t" + line);
        }
        
       
    }
}
