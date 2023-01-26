
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        String lineBreak = "-----------------";
        System.out.println(lineBreak + '\n' + "Hello! I'm Duke" + '\n' + "What can I do for you?" + '\n' + lineBreak);
        String instruction;
        while(true){
            Scanner myObj = new Scanner(System.in);
            instruction = myObj.nextLine();
            if(instruction.equalsIgnoreCase("list")) {
                System.out.println(lineBreak+'\n'+"Here are the tasks in your list:");
                for(int i=0;i<list.size();i++){
                    System.out.println(i+1+".["+list.get(i).getStatusIcon()+"] "+list.get(i).description);
                }
                System.out.println(lineBreak);
            } else if (instruction.equalsIgnoreCase("bye")) {
                System.out.println(lineBreak+'\n'+"Bye. Hope to see you again soon!"+'\n'+lineBreak);
                break;
            } else if (instruction.toLowerCase().contains("mark")){
                String[] split = instruction.split("\\s+");
                int toMark=Integer.parseInt(split[1]);
                if(split[0].equalsIgnoreCase("mark")){
                    list.get(toMark-1).markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                }else{
                    list.get(toMark-1).markAsUnDone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                }
                System.out.println("["+list.get(toMark-1).getStatusIcon()+"] "+list.get(toMark-1).description+'\n'+lineBreak);
            } else{
                Task t =new Task(instruction);
                list.add(t);
                System.out.println(lineBreak+'\n'+"added: "+t.description+'\n'+lineBreak);
            }
        }
    }
}
