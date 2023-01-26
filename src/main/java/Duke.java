import java.util.Scanner;

public class Duke {

    private static String[] list = new String[100];
    private static int counter = 0;
    private static String line = "___________________________________________";

    public static void printList() {
        System.out.println("\t" + line);
        System.out.println("\t Here are the tasks for your list: ");
        for(int i=0; i< counter; i++) {
            System.out.println("\t" + (i+1) + ". " + list[i]);
        }
        System.out.println("\t" + line);
    }

    public static void main(String[] args) {
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
                System.out.println("\t Bye! Do let me know if you need any further assistance");
                System.out.println("\t" + line);
                break;
            }

            else if (input.equals("list")) {
                printList();
                continue;
            }

            list[counter] = input;
            counter++;

            System.out.println("\t" + line);
            System.out.println("\t" + "added: " + input);
            System.out.println("\t" + line);
        }
        
       
    }
}
