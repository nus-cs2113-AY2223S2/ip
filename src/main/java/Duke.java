
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static ArrayList<String> list = new ArrayList<String>();
    public static void main(String[] args) {
        String lineBreak = "-----------------";
        System.out.println(lineBreak + '\n' + "Hello! I'm Duke" + '\n' + "What can I do for you?" + '\n' + lineBreak);
        String instruction;
        while(true){
            Scanner myObj = new Scanner(System.in);
            instruction = myObj.nextLine();
            if(instruction.equalsIgnoreCase("list")) {
                System.out.println(lineBreak);
                for(int i=0;i<list.size();i++){
                    System.out.println(i+1+". "+list.get(i));
                }
                System.out.println(lineBreak);
            } else if (instruction.equalsIgnoreCase("bye")) {
                System.out.println(lineBreak+'\n'+"Bye. Hope to see you again soon!"+'\n'+lineBreak);
                break;
            } else{
                list.add(instruction);
                System.out.println(lineBreak+'\n'+"added: "+instruction+'\n'+lineBreak);
            }
        }
    }
}
