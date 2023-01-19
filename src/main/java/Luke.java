import java.util.Scanner;
public class Luke {
    public static void main(String[] args) {
        Response response = new Response();
        Scanner userInput = new Scanner(System.in);
        TaskOrganizer taskOrganizer = new TaskOrganizer();
        response.sayHi();
        while (true) {
            String input = userInput.nextLine();
            //If the user input is bye, exit the program
            if (input.equalsIgnoreCase("bye")) {
                userInput.close();
                break;
            }

            String[] processedInput = input.split(" ", 2);
            String command = processedInput[0];
            //Check if command add is invoked.
            if (command.equals("add")) {
                String task = processedInput[1];
                taskOrganizer.addTask(task);
                response.printAddTask(task);
            }
            //Check if command list is invoked.
            else if (command.equals("list")) {
                response.printTaskList(taskOrganizer.getTaskList());
            }
            //Else echo user input
            else {
                response.printString(input);
            }
        }
        response.sayBye();
    }
}
