import java.util.Scanner;

public class Duke {
    static int limit = 100;
    static String[] task = new String[limit];
    static int listCount = 0;
    public static void listInput(){
        int idxCount = 1;
        System.out.println("____________________________________________________________");
        for (int i=0 ;i < listCount;i++){
            System.out.println(idxCount + ". " + task[i]);
            idxCount++;
        }
        System.out.println("____________________________________________________________");
    }
    public static void addList(String input){
        if(listCount == limit ){
            System.out.println("____________________________________________________________");
            System.out.println("Too much tasks");
            System.out.println("____________________________________________________________");
        }else{
            task[listCount] = input;
            listCount++;
            System.out.println("____________________________________________________________");
            System.out.println("added: " + input);
            System.out.println("____________________________________________________________");
        }

    }
    public static void startBot(){
        Scanner in = new Scanner((System.in));
        while(true){
            String input  = in.nextLine();
            switch(input){
                case "bye":
                    return;
                case "list":
                    listInput();
                    break;
                default:
                    addList(input);

            }
        }
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n");

        startBot();
        System.out.println( "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");

    }
}
