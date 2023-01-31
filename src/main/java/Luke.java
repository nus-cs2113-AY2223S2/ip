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

            //Split input into [command] and [task]
            String[] processedInputs = input.split(" ", 2);
            String command = processedInputs[0];

            //Check if command add is invoked.
            switch (command) {
            case "add":
                String taskInfo = processedInputs[1];
                String taskName = taskOrganizer.addTask(taskInfo);
                response.printAddTask(taskName);
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
                    int taskID = Integer.parseInt(processedInputs[1]);
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
                    int taskID = Integer.parseInt(processedInputs[1]);
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
