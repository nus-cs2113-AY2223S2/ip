import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        greetings();
        echoText();
        goodbye();
    }

    private static void listTasks(int counter, Task[] storedTask) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            if (storedTask[i].getIsDone()) {
                System.out.println(i + 1 + ". [x] " + storedTask[i].getText());
            }
            else {
                System.out.println(i + 1 + ". [ ] " + storedTask[i].getText());
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void markingAsDone(String echoInput, int counter, Task[] storedTask) {
        try {
            String stringListNumber = echoInput.substring(5, echoInput.length());
            int listNumber = Integer.parseInt(stringListNumber);

            storedTask[listNumber-1].setIsDone(true);
            listTasks(counter, storedTask);
        }
        catch (NumberFormatException ex){
            System.out.println("____________________________________________________________");
            System.out.println("*DID NOT ENTER A VALID NUMBER*");
            System.out.println("____________________________________________________________");
        }
        catch (ArrayIndexOutOfBoundsException x) {
            System.out.println("____________________________________________________________");
            System.out.println("*DID NOT ENTER A NUMBER ON LIST*");
            System.out.println("____________________________________________________________");
        }
        catch (NullPointerException x) {
            System.out.println("____________________________________________________________");
            System.out.println("*DID NOT ENTER A NUMBER ON LIST*");
            System.out.println("____________________________________________________________");
        }
    }

    private static void echoText() {
        Scanner scanner = new Scanner(System.in);
        String echoInput = "";
        Task[] storedTask = new Task[100];
        int counter = 0;

        while (!echoInput.equals("bye")) {
            echoInput = scanner.nextLine();
            if (echoInput.equals("bye")) {
                break;
            }
            else if (echoInput.equals("list")) {
                listTasks(counter, storedTask);
            }
            else if (echoInput.startsWith("mark ")) {
                markingAsDone(echoInput, counter, storedTask);
            }
            else {
                Task tempTask = new Task(echoInput, false);
                storedTask[counter] = tempTask;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + echoInput);
                System.out.println("____________________________________________________________");
                counter = counter + 1;
            }
        }
    }

    private static void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void goodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
