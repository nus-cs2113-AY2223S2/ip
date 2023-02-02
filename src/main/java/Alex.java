import java.util.Scanner;

public class Alex {
    public static void main(String[] args) {
        Task[] allTasks = new Task[100];
        int numberOfTasks = 0;
        // Create a Scanner object
        Scanner myObj = new Scanner(System.in);
        greeting();
        while(true) {
            String userInput = myObj.nextLine();
            String[] words = userInput.split(" ");
            if(words[0].toLowerCase().equals("list")) {
                for(int i = 1; i <= numberOfTasks; i++) {
                    System.out.print(i);
                    System.out.println(". " + allTasks[i-1]);
                }
                printLine();

            }
            else if(words[0].toLowerCase().equals("bye")) {
                exit();
                break;
            }
            else if(words[0].toLowerCase().equals("mark")) {
                printLine();
                System.out.println("Good job! I have marked this task as completed:");
                int number = Integer.parseInt(words[1]);
                allTasks[number - 1].markAsDone();
                System.out.println(allTasks[number - 1]);
                printLine();

            }
            else if(words[0].toLowerCase().equals("unmark")) {
                printLine();
                System.out.println("Got it! I have marked this task as not yet completed:");
                int number = Integer.parseInt(words[1]);
                allTasks[number - 1].unmark();
                System.out.println("[" + allTasks[number - 1].getStatusIcon() + "]" + " " + allTasks[number - 1].getDescription());
                printLine();


            }
            else {
                String action = words[0].toLowerCase();
                String activity = "";
                for (int i = 1; i < words.length; i++) {
                    if(words[i].charAt(0) == '/' ) {
                        break;
                    }
                    else {
                        activity += " " + words[i];
                    }

                }
                if(action.equals("todo")) {
                    Task t = new Todo(activity);
                    allTasks[numberOfTasks] = t;
                    numberOfTasks += 1;
                    printLine();
                    echoResponse(t, numberOfTasks);
                }
                else if(action.equals("deadline")) {
                    String by = "";
                    int byIndex = 0;
                    for(int i = 0; i < words.length; i++) {
                        if(words[i].charAt(0) == '/') {
                            byIndex = i;
                        }
                    }
                    for(int i = byIndex + 1; i < words.length; i++) {
                        by += " " + words[i];
                    }

                    Task deadline = new Deadline(activity, by);
                    allTasks[numberOfTasks] = deadline;
                    numberOfTasks += 1;
                    printLine();
                    echoResponse(deadline, numberOfTasks);
                }
                else if(action.equals("event")) {
                    String from = "";
                    String to = "";
                    int fromIndex = 0;
                    int toIndex = 0;
                    for(int i = 0; i < words.length; i++) {
                        if(words[i].toLowerCase().equals("/from")) {
                            fromIndex = i;
                        }
                        if(words[i].toLowerCase().equals("/to")) {
                            toIndex = i;
                        }
                    }
                    for(int i = fromIndex + 1; i < toIndex; i++) {
                        from += " " + words[i];
                    }
                    for(int i = toIndex + 1; i < words.length; i++) {
                        to += " " + words[i];
                    }
                    Task event = new Event(activity, from, to);
                    allTasks[numberOfTasks] = event;
                    numberOfTasks += 1;
                    printLine();
                    echoResponse(event, numberOfTasks);
                }

            }


        }


    }
    public static void echoResponse(Task task, int number) {
        System.out.println("Got it. I've added this task:" + "\n " + task + "\n" +
                "Now you have " + number + " tasks in the list" );
        printLine();
    }
    public static void printLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }
    public static void greeting() {
        printLine();
        String greet = "Hello! I'm Alex\nWhat can I do for you today?";
        //String question = "What can I do for you today?";
        System.out.println(greet);
        //System.out.println(question);
        printLine();
    }
    public static void exit() {
        String exit = "Bye. Hope to chat with you again soon!";
        System.out.println(exit);
        printLine();
    }
}
