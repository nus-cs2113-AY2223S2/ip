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

            if(userInput[0].equals("list")){
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
            }

            else if (userInput[0].equals("mark")){
                Integer index = Integer.parseInt(userInput[1]);
                tasks[index-1].setStatus("mark");
                System.out.println("Nice! I've marked this task as done: \n");
                System.out.println(tasks[index-1] + "\n");
                printBorder();
                entry = input.nextLine();
            }

            else if (userInput[0].equals("unmark")){
                Integer index = Integer.parseInt(userInput[1]);
                tasks[index-1].setStatus("unmark");
                System.out.println("OK, I've marked this task as not done yet: \n");
                System.out.println(tasks[index-1] + "\n");
                printBorder();
                entry = input.nextLine();
            }

            else {
                tasks[listCount] = new Task(entry);
                listCount ++;
                printBorder();
                System.out.println("added: " + entry + "\n");
                printBorder();
                entry = input.nextLine();
            }
        }

        printBorder();
        printExit();
        printBorder();
    }
}
