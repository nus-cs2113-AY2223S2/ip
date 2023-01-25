import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
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
                + "Key in a number based on the function\n 1) Echo \n 2) List");
        //To be added to make sure user input is read.
        Task[] actions = new Task[100];
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        int action = Integer.parseInt(line);
        switch (action) {
            case 1:
                line = in.nextLine();
                while (!line.equals("Bye")) {
                    System.out.println(line);
                    line = in.nextLine();
                }
                break;
            case 2:
                int actionCounter = 0;
                System.out.println("You may type anything to add to a list of a 100 things to do.\n"
                        + "Type List to list down everything you have added so far.\n"
                        + "Type Bye to exit.");
                line = in.nextLine();
                while (!line.equals("Bye")) {
                    if (line.equals("List")) {
                        if (actionCounter == 0) {
                            System.out.println("There is nothing to list!");
                            line = in.nextLine();
                            continue;
                        }
                        for (int iterator = 0; iterator < actionCounter; iterator++) {
                            System.out.println(iterator + 1
                                    + ")[" + actions[iterator].getStatusIcon()
                                    + "]"
                                    + actions[iterator].getDescription()
                                    );

                        }
                        System.out.println("You may now mark or unmark your tasks by typing Action followed by the task number! E.g. unmark 1 or mark 2\n"
                                + "To go back to editing your list, type edit");
                        line = in.nextLine();
                        while (!line.equals("edit")) {
                            String[] words = line.split(" ");
                            if (!(words[0].equals("mark") || words[0].equals("unmark") || words[0].equals("edit"))) {
                                System.out.println("Invalid Input!");
                                line = in.nextLine();
                                continue;
                            }
                            int taskNumber = Integer.parseInt(words[1]) - 1;
                            if (words[0].equals("mark")) {
                                actions[taskNumber].mark();
                            } else {
                                // must be unmarked or some weird other mistype
                                actions[taskNumber].unmark();
                            }
                            System.out.println("Done!\n"
                                    + "["
                                    + actions[taskNumber].getStatusIcon()
                                    + "]"
                                    + actions[taskNumber].getDescription()
                            );
                            line = in.nextLine();
                        }
                        // have to edit before exit, might change this in the future
                        if (line.equals("edit")) {
                            System.out.println("Input to add to your list!");
                        }
                        line = in.nextLine();
                    } else {
                        System.out.println("Added: " + line);
                        Task toBeAdded = new Task(line);
                        actions[actionCounter] = toBeAdded;
                        actionCounter++;
                        line = in.nextLine();
                    }
                }
                break;
        }
        System.out.println("That's all from me! Goodbye!");
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
        public void mark() {
            this.isDone = true;
        }
        public void unmark() {
            this.isDone = false;
        }
        public String getDescription() {
            return(this.description);
        }
    }
}
