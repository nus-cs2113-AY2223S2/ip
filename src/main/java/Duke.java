import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon! \n";
        String border = "____________________________________________________________ \n";

        String[] arr;
        arr = new String[100];
        int listCount = 0;

        System.out.println(logo + border + greeting + border);

        Scanner input = new Scanner(System.in);
        String entry = input.nextLine();


        while (!entry.equals("bye")){
            if(entry.equals("list")){
                System.out.println(border);
                for(int i = 0; i < listCount; i++){
                    System.out.println(i+1 + ". " + arr[i]);
                }
                System.out.println(border);
                entry = input.nextLine();
            }
            else {
                arr[listCount] = entry;
                listCount ++;
                System.out.println(border + "added: " + entry + "\n" + border);
                entry = input.nextLine();
            }
        }

        System.out.println(border + exit + border);
    }
}
