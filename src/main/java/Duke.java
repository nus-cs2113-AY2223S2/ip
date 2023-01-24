import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        //Initialisation
        String line = "start";
        String[] list = new String[100];
        int listSize = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        //Get commands.
        while (!line.equals("bye")){
            line = in.nextLine();
            switch(line) {
            //To list out everything in the array.
            case "list":
                for (int x = 0; x < listSize; x += 1) {
                    System.out.println((x + 1) + ". " + list[x]);
                }
            //To terminate program.
            case "bye":
                break;
            //Add normal user input to the list array.
            default:
                list[listSize] = line;
                listSize += 1;
                System.out.println("added: " + line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
