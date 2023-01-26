import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String Horizontal_line = "____________________________________________________________";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(Horizontal_line+"\n");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        System.out.println(Horizontal_line+"\n");
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        int counter = 0;
        boolean op = true;
        while(op){
            String ins = in.nextLine();
            switch (ins){
                case "list":
                    if (counter == 0) System.out.println("Your day is clear!");
                    else {
                        System.out.println(Horizontal_line+"\n");
                        for (int i = 0; i < counter; i++){
                            System.out.println((i+1) + ".[" + list[i].getStatusIcon() +"]" + list[i].description);
                        }
                        System.out.println(Horizontal_line+"\n");
                    }
                    break;

                case "bye":
                    op = false;
                    System.out.println(Horizontal_line+"\n");
                    System.out.println("Bye! See you next time!");
                    System.out.println(Horizontal_line+"\n");
                    break;
                default:
                    if (ins.contains("unmark")){
                        int idx = 5;
                        System.out.println(Horizontal_line + "\n");
                        int num = Integer.parseInt(ins.substring(idx+2));
                        System.out.println("Okay, I have unmarked Task " + num);
                        System.out.println(Horizontal_line + "\n");
                        list[num - 1].isDone = false;
                        break;
                    }
                    else if (ins.contains("mark")){
                        System.out.println(Horizontal_line + "\n");
                        int idx = 3;
                        int num = Integer.parseInt(ins.substring(idx+2));
                        System.out.println("Nice! You have done Task " + num);
                        list[num - 1].isDone = true;
                        System.out.println(Horizontal_line + "\n");
                        break;
                    }
                    else {
                        System.out.println(Horizontal_line + "\n");
                        list[counter] = new Task(ins,counter + 1);
                        counter++;
                        System.out.println("Added: " + ins);
                        System.out.println(Horizontal_line + "\n");
                        break;
                    }
            }
        }
    }
}
