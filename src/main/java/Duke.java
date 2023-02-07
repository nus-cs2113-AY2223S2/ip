import java.util.Scanner;

//TODO: specify error eg. event/ddl timing not found
//TODO: event /from /by wrong order
//TODO: insufficient input -> task still added -> printTask returns stringIndexOutOfBoundsException

public class Duke {
    public static String line = "____________________________________________________________\n";
    public static int countTask = 0;
    public static Task[] tasks = new Task[100];

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String greet = line +
                "Hello! I'm Duke.\n" +
                "To add general tasks to the todo list, simply type in the task and press enter.\n" +
                "You can specify the type of tasks by using " +
                "'todo' / 'deadline' / 'event' keyword before the task description.\n" +
                "You can keep track of deadlines by typing 'deadline' " +
                "followed by task description + '/by' to specify the time of the deadline.\n" +
                "You can also add events to the list by typing 'event' " +
                "followed by task description + '/from' + starting time of event + '/to' ending time of event.\n" +
                "Type 'list' to view the current todo list.\n" +
                "Type 'mark' and task event index to mark the task as done " +
                "and type 'unmark' and index to undo the task.\n" +
                "Type 'bye' to exit Duke.\n" +
                line;
        System.out.println(greet);

        Scanner in = new Scanner(System.in);

        while (true) {
            String userInput = "";
            if (in.hasNextLine()) {
                userInput = in.nextLine().trim();
            } else {
                break;
            }
            try {
                if (userInput.equals("bye")) {
                    System.out.println("Thank you for using Duke. Hope to see you soon!");
                    break;
                } else if (userInput.equals("list")) {
                    printTaskList();
                } else if (userInput.startsWith("mark")) {
                    markTask(userInput);
                } else if (userInput.startsWith("unmark")) {
                    unmarkTask(userInput);
                } else if (userInput.startsWith("todo")) {
                    try {
                        createTodo(userInput);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Description of a todo cannot be empty. Adding todo failed.\n" + line);
                    }
                } else if (userInput.startsWith("deadline")) {
                    try {
                        createDeadline(userInput);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Insufficient input for deadline. Adding deadline failed.\n" + line);
                    }
                } else if (userInput.startsWith("event")) {
                    try {
                        createEvent(userInput);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Insufficient input for event. Adding event failed.\n" + line);
                    }
                } else {
                    throw new TaskTypeException();
                }
            } catch (TaskTypeException e) {
                System.out.println("Sorry, Duke does not recognise the task. " +
                        "Type 'todo'/'deadline'/'event' to add task of respective type.\n" + line);
            }
        }
    }

    //TODO: stringoutofboundexception
    public static void printTaskList() {
        System.out.println(line + "Here are the tasks in your list:");
        for (int i = 0; i < countTask; i++) {
            int taskIndex = i + 1;
            System.out.print(taskIndex + ". " + tasks[i].printTask());
        }
        System.out.println(line);
    }

    public static void markTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(4).trim());
        if (taskIndex >= 1 && taskIndex <= countTask) {
            tasks[taskIndex - 1].markAsDone();
            System.out.println(line + "Task " + taskIndex + " marked as done:\n" +
                    tasks[taskIndex - 1].printTask() + line);
        } else {
            System.out.println("Task " + taskIndex + " not found. Please try again.");
        }
    }

    public static void unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(6).trim());
        if (taskIndex >= 1 && taskIndex <= countTask) {
            tasks[taskIndex - 1].markAsUndone();
            System.out.println(line + "Task " + taskIndex + " marked as not done yet:\n" +
                    tasks[taskIndex - 1].printTask() + line);
        } else {
            System.out.println("Task " + taskIndex + " not found. Please try again.");
        }
    }

//    public static void createTask(String input) {
//        Task t = new Task(input);
//        tasks[countTask] = t;
//        countTask++;
//        System.out.println(line + "added: " + t.getDescription() + '\n' + line);
//    }

    public static void createTodo(String input) throws IndexOutOfBoundsException {
        ToDo todoTask = new ToDo(input.substring(5));
        tasks[countTask] = todoTask;
        countTask++;
        System.out.println(line + "Great! I've added this task:\n" + "   " + todoTask.printTask());
        System.out.println("Now you have " + countTask + " task(s) in the list.\n" + line);
    }

    public static void createDeadline(String input) throws IndexOutOfBoundsException {
//        if (input == null) {
//            throw new IndexOutOfBoundsException();
//        }
        Deadline deadlineTask = new Deadline(input.substring(9));
        tasks[countTask] = deadlineTask;
        countTask++;
        System.out.println(line + "Great! I've added this task:\n" + "   " + deadlineTask.printTask());
        System.out.println("Now you have " + countTask + " task(s) in the list.\n" + line);
    }

    public static void createEvent(String input) throws IndexOutOfBoundsException {
        Event eventTask = new Event(input.substring(6));
        tasks[countTask] = eventTask;
        countTask++;
        System.out.println(line + "Great! I've added this task:\n" + "   " + eventTask.printTask());
        System.out.println("Now you have " + countTask + " task(s) in the list.\n" + line);
    }
}

