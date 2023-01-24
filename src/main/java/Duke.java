import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Conversation.line();
        Conversation.greeting();
        Conversation.question();
        Conversation.line();

        String command;
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        int listCount = 0;

        while(true) {
            command = in.nextLine();
            if(command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < listCount; i += 1) {
                    System.out.println((i + 1) + ". " + list[i].getStatusIcon() + list[i].description);
                }
            }
            //else if(command.substring(0, 4).equals("mark")) {
            else if(command.matches("mark \\d")) {
                int number = Integer.parseInt(command.substring(5)) - 1;
                list[number].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list[number].getStatusIcon() + list[number].description);

            }
            //else if(command.substring(0, 6).equals("unmark")) {
            else if(command.matches("unmark \\d")) {
                int number = Integer.parseInt(command.substring(7)) - 1;
                list[number].markAsNotDone();
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(list[number].getStatusIcon() + list[number].description);
            }
            else if(command.equals("bye")) {
                Conversation.farewell();
                break;
            }
            else {
                System.out.print("added: ");
                Conversation.copy(command);
                list[listCount] = new Task(command);
                listCount += 1;
            }
        }
    }
}
