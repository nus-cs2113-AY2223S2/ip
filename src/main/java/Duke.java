import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String invalidCommandPrinter = "One hour of lifespan has been deducted, in accordance with our Terms and Services.";
        String validCommandPrinter = "Command acknowledged. Reducing user lifespan by 30 minutes.";
        String logo = "    // | |     //   ) )  // | |  \\\\ / / \\\\    / / //   ) ) \n"
                + "   //__| |    //___/ /  //__| |   \\  /   \\\\  / / ((        \n"
                + "  / ___  |   / ___ (   / ___  |   / /     \\\\/ /    \\\\      \n"
                + " //    | |  //   | |  //    | |  / /\\\\     / /       ) )   \n"
                + "//     | | //    | | //     | | / /  \\\\   / / ((___ / /    ";
        System.out.println(line+'\n'+logo+'\n'+line);
        System.out.println("Welcome to Araxys Systems, the only system powered by LifeForce™");
        System.out.println("How may I help you today?");
        System.out.println(line);
        Scanner in = new Scanner(System.in);
        String command="";
        ArrayList<Task>taskList = new ArrayList<Task>();
        while(true){
            command = in.nextLine();
            command = command.trim();
            if(command.equals("bye")){
                System.out.println(line+'\n'+"Goodbye. To reach customer service, just look outside your window."+'\n'+line);
                return;
            }
            if(command.equals("list")){
                System.out.println(line);
                System.out.println(validCommandPrinter);
                for(int i=0;i<taskList.size();i++){
                    System.out.print(Integer.toString(i+1)+":["+taskList.get(i).getStatusIcon()+"]///////");
                    System.out.println(taskList.get(i).getDescription());
                }
                System.out.println(line);
            }else{
                String[] commandList = command.split(" ");
                if(commandList[0].equals("mark")){
                    if (commandList.length!=2){
                        System.out.println(line);
                        System.out.println("Please only input \"mark\" followed by an integer.");
                        System.out.println(invalidCommandPrinter);
                        System.out.println(line);
                        continue;
                    }
                    int index;
                    try{
                        index = Integer.parseInt(commandList[1]);
                    } catch (NumberFormatException numberFormatException){
                        System.out.println(line+'\n'+"Sorry, index is not numeric."+'\n'+line);
                        System.out.println(invalidCommandPrinter);
                        continue;
                    }
                    index--;
                    if(index<0||index>=taskList.size()){
                        System.out.println(line+'\n'+"Sorry, index is invalid."+'\n'+line);
                        System.out.println(invalidCommandPrinter);
                    }else if(taskList.get(index).isDone()){
                        System.out.println(line+'\n'+"The task specified is already marked."+'\n'+line);
                        System.out.println(invalidCommandPrinter);
                    }else{
                        taskList.get(index).setDone(true);
                        System.out.println(line);
                        System.out.println(validCommandPrinter);
                        System.out.println("Task has been marked as: completed");
                        System.out.println("[X] "+taskList.get(index).getDescription());
                        System.out.println(line);
                    }
                }else if(commandList[0].equals("unmark")){
                    if (commandList.length!=2){
                        System.out.println(line);
                        System.out.println("Please only input \"unmark\" followed by an integer.");
                        System.out.println(invalidCommandPrinter);
                        System.out.println(line);
                        continue;
                    }
                    int index;
                    try{
                        index = Integer.parseInt(commandList[1]);
                    } catch (NumberFormatException numberFormatException){
                        System.out.println(line+'\n'+"Sorry, index is not numeric."+'\n'+line);
                        System.out.println(invalidCommandPrinter);
                        continue;
                    }
                    index--;
                    if(index<0||index>=taskList.size()){
                        System.out.println(line+'\n'+"Sorry, index is invalid."+'\n'+line);
                        System.out.println(invalidCommandPrinter);
                    }else if(!taskList.get(index).isDone()){
                        System.out.println(line+'\n'+"The task specified is already unmarked."+'\n'+line);
                        System.out.println(invalidCommandPrinter);
                    }else{
                        taskList.get(index).setDone(false);
                        System.out.println(line);
                        System.out.println(validCommandPrinter);
                        System.out.println("Task has been marked as: not completed");
                        System.out.println("[ ] "+taskList.get(index).getDescription());
                        System.out.println(line);
                    }
                }else{
                    taskList.add(new Task(command));
                    System.out.println(validCommandPrinter);
                    System.out.println(line+'\n'+"New task has been added: "+command+'\n'+line);
                }
            }
        }
    }
}
