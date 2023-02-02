import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        System.out.println("Good day. YodaBot, I am.");
        System.out.println("Assistance, you need?");
        Scanner scanner = new Scanner(System.in);
        Task[] taskArray = new Task[100];
        int taskIndex = 0;
        boolean isEnd = false;
        String done;
        while (true) {
            String command = scanner.nextLine(); //reads in input
            switch (command) {
            //if command is list, either display empty or display list
            case "list":
                if (taskIndex == 0) {
                    System.out.println("Empty, list is.");
                } else {
                    System.out.println("As shown, list is:");
                    for (int j = 0; j < taskIndex; ++j) {
                        if (taskArray[j] instanceof ToDo) {
                            System.out.println(j + ". " + ((ToDo) taskArray[j]).getToDo() + " " + taskArray[j].getDoneStatus() + " " + taskArray[j].getDescription());
                        }
                        if (taskArray[j] instanceof Deadline) {
                            System.out.println(j + ". " + ((Deadline) taskArray[j]).getDeadline() + " " + taskArray[j].getDoneStatus() + " " + taskArray[j].getDescription() + " (" + ((Deadline) taskArray[j]).getDate() + ")");
                        }
                        if (taskArray[j] instanceof Event) {
                            System.out.println(j + ". " + ((Event) taskArray[j]).getEvent() + " " + taskArray[j].getDoneStatus() + " " + taskArray[j].getDescription() + " (" + ((Event)taskArray[j]).getStartAndEnd() + ")");
                        }
                    }
                }
                break;
            //if command is mark, mark the appropriate entry in the list
            case "mark":
                System.out.println("Mark what, I ask?");
                String listIndex = scanner.nextLine();
                int indexToMark = Integer.parseInt(listIndex);
                if (taskIndex == 0 || indexToMark > taskIndex) {
                    System.out.println("In list, it is not.");
                } else {
                    taskArray[indexToMark].isDone = true;
                    System.out.println("Marked it, I have:");
                    if (taskArray[indexToMark] instanceof ToDo) {
                        System.out.println(indexToMark + ". " + ((ToDo) taskArray[indexToMark]).getToDo() + " " + taskArray[indexToMark].getDoneStatus() + " " + taskArray[indexToMark].getDescription());
                    }
                    if (taskArray[indexToMark] instanceof Deadline) {
                        System.out.println(indexToMark + ". " + ((Deadline) taskArray[indexToMark]).getDeadline() + " " + taskArray[indexToMark].getDoneStatus() + " " + taskArray[indexToMark].getDescription() + "(" + ((Deadline) taskArray[indexToMark]).getDate() + ")");
                    }
                    if (taskArray[indexToMark] instanceof Event) {
                        System.out.println(indexToMark + ". " + ((Event) taskArray[indexToMark]).getEvent() + " " + taskArray[indexToMark].getDoneStatus() + " " + taskArray[indexToMark].getDescription() + " (" + ((Event)taskArray[indexToMark]).getStartAndEnd() + ")");
                    }
                }
                break;
            //if command is unmark, unmark the appropriate entry in the list
            case "unmark":
                System.out.println("Unmark what, I ask?");
                String listIndex2 = scanner.nextLine();
                int indexToUnmark = Integer.parseInt(listIndex2);
                if (taskIndex == 0 || indexToUnmark > taskIndex) {
                    System.out.println("In list, it is not.");
                    break;
                } else {
                    taskArray[indexToUnmark].isDone = false;
                    System.out.println("Unmarked it, I have:");
                    if (taskArray[indexToUnmark] instanceof ToDo) {
                        System.out.println(indexToUnmark + ". " + ((ToDo) taskArray[indexToUnmark]).getToDo() + " " + taskArray[indexToUnmark].getDoneStatus() + " " + taskArray[indexToUnmark].getDescription());
                    }
                    if (taskArray[indexToUnmark] instanceof Deadline) {
                        System.out.println(indexToUnmark + ". " + ((Deadline) taskArray[indexToUnmark]).getDeadline() + " " + taskArray[indexToUnmark].getDoneStatus() + " " + taskArray[indexToUnmark].getDescription() + " (" + ((Deadline) taskArray[indexToUnmark]).getDate() + ")");
                    }
                    if (taskArray[indexToUnmark] instanceof Event) {
                        System.out.println(indexToUnmark + ". " + ((Event) taskArray[indexToUnmark]).getEvent() + " " + taskArray[indexToUnmark].getDoneStatus() + " " + taskArray[indexToUnmark].getDescription() + " (" + ((Event)taskArray[indexToUnmark]).getStartAndEnd() + ")");
                    }
                }
                break;
            //if command is todo, prompt for task and add into list
            case "Todo":
                System.out.println("To do what, I ask?");
                String todoTask = scanner.nextLine();
                taskArray[taskIndex] = new ToDo(todoTask, false, "[T]");
                System.out.println(taskIndex + ". " + ((ToDo) taskArray[taskIndex]).getToDo() + " " + taskArray[taskIndex].getDoneStatus() + " " + taskArray[taskIndex].getDescription() + " - Added, I have");
                ++taskIndex;
                break;
            //if command is Deadline, prompt for task then deadline and add into list
            case "Deadline":
                System.out.println("Deadline for what, I ask?");
                String deadlineTask = scanner.nextLine();
                System.out.println("Deadline is what, I ask?");
                String date = scanner.nextLine();
                taskArray[taskIndex] = new Deadline(deadlineTask, false, "[D]", date);
                System.out.println(taskIndex + ". " + ((Deadline) taskArray[taskIndex]).getDeadline() + " " + taskArray[taskIndex].getDoneStatus() + " " + taskArray[taskIndex].getDescription() + " " + ((Deadline) taskArray[taskIndex]).getDate() + " - Added, I have");
                ++taskIndex;
                break;
                //if command is Event, prompt for event, then prompt for start and end date
            case "Event":
                System.out.println("What event, I ask?");
                String eventTask = scanner.nextLine();
                System.out.println("When is it, I ask?");
                String timing = scanner.nextLine();
                taskArray[taskIndex] = new Event(eventTask,false,"[E]",timing);
                System.out.println(taskIndex + ". " + ((Event) taskArray[taskIndex]).getEvent() + " " + taskArray[taskIndex].getDoneStatus() + " " + taskArray[taskIndex].getDescription() + " " + ((Event) taskArray[taskIndex]).getStartAndEnd() + " - Added, I have");
                ++taskIndex;
                break;
            //if command is bye, end program
            case "bye":
                System.out.println("See you soon, I hope. Goodbye.");
                isEnd = true;
                break;
            }
            if (isEnd) {
                break;
            }
        }
    }
}
