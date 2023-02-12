package utility;

import tasks.Task;

public class Methods {
    public static void printGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "__________________________\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! Do you need anything from me?\n"
                + "I have only been trained to greet, echo and list you so far.\n"
                + "Once my owner is more proficient in what he does, he will give me more functions!\n"
                + " 1)echo \n 2)todo\n 3)mark\n 4)unmark\n 5)deadline\n 6)event\n 7)delete\n"
                + "When you wish to exit, do tell me by typing : bye");
    }

    public static void print(String input) {
        System.out.println(input);
    }

    public static void printListElement(int iterator, Task action) {
        Methods.print(iterator + 1
                + ")"
                + action.toString());
    }

    public static void printDoneMarkingTasks(Task action) {
        Methods.print("Done!\n" + action.toString());
    }

    public static String findTaskDetails(String line) {
        return line.substring(line.indexOf(" ") + 1);
    }

    public static void printAcknowledgement(Task action, int actionCounter) {
        System.out.println("Got it. I've added this task:\n" + action.toString() + System.lineSeparator() + "Now you have " + actionCounter + " tasks in the list.");
    }

    public static void printCurrentSupportedActions() {
        Methods.print("I am currently only able to do: \n 1)echo \n 2)todo\n 3)mark\n 4)unmark\n 5)deadline\n 6)event\n 7)delete\n");
    }
    public static void printDeleteAcknowledgement(Task action, int actionCounter) {
        print("Got it. I've removed this task" + action.toString() + System.lineSeparator() + "Now you have " + actionCounter + " tasks in the list.");
    }
}
