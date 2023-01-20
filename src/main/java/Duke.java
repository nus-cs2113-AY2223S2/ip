import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        System.out.println(line+'\n'+logo+'\n'+line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n"+line);
        //System.out.println("Bye. Hope to see you again soon!\n"+line);
        Scanner in = new Scanner(System.in);
        String command="";
        while(true){
            command = in.nextLine();
            if(command.equals("bye")){
                System.out.println(line+'\n'+"Bye. Hope to see you again soon!"+'\n'+line);
                return;
            }
            System.out.println(line+'\n'+command+'\n'+line);
        }
    }
}
