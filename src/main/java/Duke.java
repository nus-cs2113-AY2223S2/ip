import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        String line = "";

        //Array to store user inputs. maximum 100 inputs.
        String[] userInputs = new String[100];
        int countInputs = 0;
        while(!line.equals("bye")) {
            line = in.nextLine();

            if(line.equals("list")) {
                for(int i = 0; i < countInputs; i++) {
                    System.out.println(i+1 + ". " + userInputs[i]);
                }
            }else{
                userInputs[countInputs] = line;
                System.out.println("added: " + line);
                countInputs += 1;
            }



        }
        System.out.println("Bye. Hope to see you again soon!");


    }
}
