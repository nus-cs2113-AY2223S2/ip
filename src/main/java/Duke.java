import java.util.Scanner;
import java.util.ArrayList;
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
        Scanner in = new Scanner(System.in);
        String command="";
        ArrayList<String>textList = new ArrayList<String>();
        while(true){
            command = in.nextLine();
            if(command.equals("bye")){
                System.out.println(line+'\n'+"Bye. Hope to see you again soon!"+'\n'+line);
                return;
            }
            if(command.equals("list")){
                System.out.println(line);
                for(int i=0;i<textList.size();i++){
                    System.out.println(Integer.toString(i+1)+". "+textList.get(i));
                }
                System.out.println(line);
            }else{
                textList.add(command);
                System.out.println(line+'\n'+"added: "+command+'\n'+line);
            }
        }
    }
}
