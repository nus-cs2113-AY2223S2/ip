import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("--------------------------------");
        System.out.println("Hello! I'm Douph");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------");
        Task[] lists=new Task[100];
        int index=0;
        while(true){
            String line=in.nextLine();
            if(line.equals("bye")){
                System.out.println("--------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------------");
                break;
            }else if(line.substring(0,4).equals("mark")){
                String number=line.substring(5,6);
                int markIndex=Integer.parseInt(number);
                lists[markIndex-1].isDone=true;
            }else if(line.contains("unmark")){
                String number=line.substring(7,8);
                int unMarkIndex=Integer.parseInt(number);
                lists[unMarkIndex-1].isDone=false;
            } else if(line.equals("list")) {
                System.out.println("list:");
                for(int i=0;i<index;i++){
                    System.out.println("\t"+(i+1)+".["+lists[i].getStatusIcon()+"]"+lists[i].description);
                }
            } else{
                lists[index]=new Task(line);
                index++;
            }

        }
    }
}
