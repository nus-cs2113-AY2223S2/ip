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

            String[] processedInput = input.split(" ", 2); //Split input into [command] and [task]
            String command = processedInput[0];

            //Check if command add is invoked.
            switch (command) {
            case "add":
                String task = processedInput[1];
                taskOrganizer.addTask(task);
                response.printAddTask(task);
                break;
            case "list":  //Check if command list is invoked.
                if (!taskOrganizer.isEmpty()) {
                    response.printTaskList(taskOrganizer.getTaskList());
                } else {
                    response.printEmptyList();
                }
                break;
            case "mark":
                try {
                    int taskID = Integer.parseInt(processedInput[1]);
                    if (!taskOrganizer.isOutOfBounds(taskID)) {
                        taskOrganizer.markTask(taskID);
                        response.printMarkTask(taskOrganizer.getTaskByID(taskID));
                    } else {
                        response.printOutOfBounds();
                    }
                } catch (NumberFormatException e) {
                    response.printInvalidCommand();
                }
                break;
            case "unmark":
                try {
                    int taskID = Integer.parseInt(processedInput[1]);
                    if (!taskOrganizer.isOutOfBounds(taskID)) {
                        taskOrganizer.unmarkTask(taskID);
                        response.printUnmarkTask(taskOrganizer.getTaskByID(taskID));
                    } else {
                        response.printOutOfBounds();
                    }
                } catch (NumberFormatException e) {
                    response.printInvalidCommand();
                }
                break;
            default:  //Else echo user input
                response.printString(input);
                break;
            }
        }
        response.sayBye();
    }
}
