import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String HorizontalLine = "__________________________\n";
        String command = new String();


        System.out.println(HorizontalLine + "Hello! I'm Duke\n" + "What can I do for you?\n"
        + HorizontalLine );
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        while(!command.equals("bye")){
            if(command.equals("list")){

            }
            System.out.println( HorizontalLine + command+ "\n" + HorizontalLine);
            command = in.nextLine();

        }
        System.out.println(HorizontalLine + "Goodbye!" + "\n" + HorizontalLine);
    }
}

