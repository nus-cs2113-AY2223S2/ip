import java.util.Scanner;

public class Duke {
    public static void main(String arguments[]) {
        int counter = 0;
        System.out.println("Hi there! My name is Coffee");
        System.out.println("How can I help you today?");

        Scanner command = new Scanner(System.in); // reading in inputs
        String input_Command = command.nextLine();

        String string_Array[];
        string_Array = new String[110];
        while (!"bye".equals(input_Command)) { // means if bye as input, immediately go to I look forward to seeing you again
                if ("list".equals(input_Command)) { // if list is an input
                    for (int i = 0; i < counter; i = i + 1) {
                        System.out.println((i + 1) + ". " + string_Array[i]);
                    }
                    command = new Scanner(System.in);
                    input_Command = command.nextLine();
                } else {
                    string_Array[counter] = input_Command; // read book
                    System.out.println("added: " + input_Command); // added: read book
                    command = new Scanner(System.in); // re-read inputs again
                    input_Command = command.nextLine(); // return book
                    counter = counter + 1;
                }
            }
        System.out.println("I look forward to seeing you again! Goodbye!");
        }
    }
