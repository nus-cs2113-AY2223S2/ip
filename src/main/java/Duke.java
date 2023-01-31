import java.util.Scanner;
public class Duke {

    public static void printBorder() {
        String border = "____________________________________________________________ \n";
        System.out.println(border);
    }

    public static void printGreeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String border = "____________________________________________________________ \n";
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(logo + border+ greeting);
    }

    public static void printExit(){
        String exit = "Bye. Hope to see you again soon! \n";
        System.out.println(exit);
    }

    public static void main(String[] args) {

        Task[] tasks = new Task[100];
        Integer listCount = 0;


        printGreeting();
        printBorder();


        Scanner input = new Scanner(System.in);
        String entry = input.nextLine();


        while (!entry.equals("bye")){

            String[] userInput = entry.split(" ", 2);

            switch(userInput[0]){
                case "list":
                    printBorder();

                    int counter = 1;
                    for (Task t: tasks){
                        if (t== null){
                            break;
                        }
                        else{
                            System.out.println(counter + ". " + t);
                            counter++;
                        }

                    }
                    printBorder();
                    entry = input.nextLine();
                    break;


                case "mark":
                    Integer index = Integer.parseInt(userInput[1]);
                    tasks[index-1].setStatus("mark");
                    System.out.println("Nice! I've marked this task as done: \n");
                    System.out.println(tasks[index-1] + "\n");
                    printBorder();
                    entry = input.nextLine();
                    break;


                case "unmark":
                    Integer index_um = Integer.parseInt(userInput[1]);
                    tasks[index_um-1].setStatus("unmark");
                    System.out.println("OK, I've marked this task as not done yet: \n");
                    System.out.println(tasks[index_um-1] + "\n");
                    printBorder();
                    entry = input.nextLine();
                    break;

                case "deadline":
                    String[] info = userInput[1].split("/by",2);
                    tasks[listCount] = new Deadline(info[0], info[1]);

                    printBorder();
                    System.out.println("added: " + info[0] + "\n");
                    listCount ++;
                    printBorder();
                    entry = input.nextLine();
                    break;

                case "todo":
                    tasks[listCount] = new Todo(userInput[1]);

                    printBorder();
                    System.out.println("added: " + userInput[1] + "\n");
                    listCount ++;
                    printBorder();
                    entry = input.nextLine();
                    break;

                case "event":
                    String[] info_e = userInput[1].split("/",3);
                    tasks[listCount] = new Event(info_e[0], info_e[1], info_e[2]);

                    printBorder();
                    System.out.println("added: " + info_e[0] + "\n");
                    listCount ++;
                    printBorder();
                    entry = input.nextLine();
                    break;


//                default:
//                    tasks[listCount] = new Task(userInput[1]);
//                    listCount ++;
//                    printBorder();
//                    System.out.println("added: " + userInput[1] + "\n");
//                    printBorder();
//                    entry = input.nextLine();

            }

        }

        printBorder();
        printExit();
        printBorder();
    }
}
