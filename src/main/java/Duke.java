import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    Hello from\n" + logo);
        System.out.println("    _________________________________________");

        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    _________________________________________");
        System.out.println("     ");

        //Read in input from user
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        //Set up list to store user inputs
        String[] list = new String[100];
        int[] status = new int[100];
        int counter = 0;


        //Processing inputs such as list, complete, & unmark
        while (!line.equalsIgnoreCase("bye")) {

            //Marking tasks as done
            if (line.contains("complete")) {
                char[] chars = line.toCharArray();
                StringBuilder sb = new StringBuilder();
                for(char c : chars){
                    if(Character.isDigit(c)){
                        sb.append(c);
                    }
                }
                int numberIntegerFormat = Integer.parseInt(String.valueOf(sb));

                status[numberIntegerFormat - 1] = 1;
                System.out.println("    _________________________________________");
                System.out.println("    " + (numberIntegerFormat) + "." + "[X] " + list[numberIntegerFormat - 1]);
                System.out.println("    _________________________________________");
                in = new Scanner(System.in);
                line = in.nextLine();
            }

            //Marking tasks as not done
            if (line.contains("unmark ")) {
                char[] chars = line.toCharArray();
                StringBuilder sb = new StringBuilder();
                for(char c : chars){
                    if(Character.isDigit(c)){
                        sb.append(c);
                    }
                }
                int numberIntegerFormat = Integer.parseInt(String.valueOf(sb));

                status[numberIntegerFormat - 1] = 0;
                System.out.println("    _________________________________________");
                System.out.println("    " + (numberIntegerFormat) + "." + "[ ] " + list[numberIntegerFormat - 1]);
                System.out.println("    _________________________________________");
                in = new Scanner(System.in);
                line = in.nextLine();
            }

            //Adding user input to the list
            if (!line.equalsIgnoreCase("list")) {
                list[counter] = line;
                counter++;
                status[counter] = 0;
                System.out.println("    _________________________________________");
                System.out.println("    " + "added: " + line);
                System.out.println("    _________________________________________");
                System.out.println("    ");
            }
            //Recalling list items
            if (line.equalsIgnoreCase("list")) {
                System.out.println("    _________________________________________");
                for (int i = 0; i < counter; ++i) {
                    if (status[i] == 0) {
                        System.out.println("    " + (i + 1) + "." + "[ ] " + list[i]);
                    } else {
                        System.out.println("    " + (i + 1) + "." + "[X] " + list[i]);
                    }
                }
                System.out.println("    _________________________________________");
                System.out.println("     ");
            }
            in = new Scanner(System.in);
            line = in.nextLine();
        }

        //When user types "bye"
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _________________________________________");
        System.out.println("     ");
    }
}
