import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class Duke{
    public static void main(String[] args){
        String dash = "__________________________________";
        String greet = dash+"\nHello I'm Duke\nWhat can I do for you?\n"+dash;
        System.out.println(greet);

        ArrayList<String> array =new ArrayList<String>();

        while(true){
            Scanner scan = new Scanner(System.in);
            String message= scan.nextLine();
            if(message.equals("bye")){
                System.out.println(dash+"\nBye. Hope to see you again soon!\n"+dash);
                break;
            }
            else if (message.equals("list")) {
                String[] outputArray = new String[array.size()];
                outputArray = array.toArray(outputArray);
                System.out.println(dash+"\n");
                for(int a =0;a<array.size();a++) {
                    System.out.println(Integer.toString(a+1)+". "+outputArray[a]+"\n");
                }
                System.out.println(dash+"\n");
            }
            else{
                array.add(message);
                System.out.println(dash+"\nadded: "+message+"\n"+dash);
            }
        }
    }
}


