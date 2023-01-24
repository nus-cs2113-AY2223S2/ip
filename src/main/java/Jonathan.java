import java.util.Objects;
import java.util.Scanner;

public class Jonathan {
    public static void main(String[] args) {
        Scanner input = new Scanner((System.in));
        String command;
        String line = "    ____________________________________________________________";
        String welcome = line + "\n    Hello! I'm Jonathan\n" +
                "    What can I do for you?\n" + line + "\n";
        String goodbye = "    Bye. Hope to see you again soon!\n" + line;

        Task[] list = new Task[100];
        int taskCounter = 0;

        System.out.println(welcome);

        while(true) {
            command = input.nextLine();

            if(Objects.equals(command, "bye")) {
                System.out.println(goodbye);
                break;

            } else if(Objects.equals(command, "list")) {
                lookupTask(list, taskCounter);

            } else if(command.split(" ")[0].equals("mark") || command.split(" ")[0].equals("unmark")) {
                boolean isDone = command.split(" ")[0].equals("mark");
                int taskNum = Integer.parseInt(command.split(" ")[1]);
                Task task = list[taskNum-1];

                changeStatus(task, isDone);

            } else {
                list[taskCounter] = new Task(command);
                taskCounter += 1;
                System.out.println(line + "\n    added: " + command + "\n" + line + "\n");
            }

        }
    }

    public static void lookupTask(Task[] list, int counter) {
        System.out.println("    ____________________________________________________________");
        for(int i=0; i < counter; i++) {
            System.out.println("    " + (i+1) + ". "  + list[i].stringRepresentation());
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void changeStatus(Task task, boolean isDone){
        task.setDone(isDone);
        if(isDone) {
            System.out.println("    ____________________________________________________________\n" +
                    "     Nice! I've marked this task as done:\n" +
                    "       " + task.stringRepresentation() + "\n" +
                    "    ____________________________________________________________");
        } else {
            System.out.println("    ____________________________________________________________\n" +
                    "     OK, I've marked this task as not done yet:\n" +
                    "       " + task.stringRepresentation() + "\n" +
                    "    ____________________________________________________________");
        }
    }
}

