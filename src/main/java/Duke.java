
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Baymax you personal life assistant \n" + logo);
        System.out.println("What can I do for you today?");
        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye see you again!");
                break;
            }

            if(line.equals("list")){
                for(int i=0;i<list.size();i++){
                    String input = list.get(i);
                    System.out.println(String.format("%d. %s", i+1, input));
                }
            }
            else {
                System.out.println("added: "+ line);
                list.add(line);
            }



        }


    }
}
