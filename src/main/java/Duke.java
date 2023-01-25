import java.util.Scanner;
public class Duke {
    public static void printGreeting(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printFarewell(){

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addToList(String[] userTexts, int currentIndex, String userInput){
        if(currentIndex >= userTexts.length){
            System.out.println("Storage is full, cannot store user input.");
        }
        userTexts[currentIndex] = userInput;
        System.out.println("added: " + userInput);
    }

    public static void printList(String[] userTexts){
        for(int i = 0; i < userTexts.length; ++i) {
            if(userTexts[i] == null)break;
            System.out.println(Integer.toString(i + 1) + ". " + userTexts[i]);
        }
    }

    public static void main(String[] args) {
        String[] userTexts = new String[100];
        int currentIndex = 0;
        Scanner in = new Scanner(System.in);
        printGreeting();
        while(true)
        {
            String userInput = in.nextLine();
            if(userInput.equals("bye")){
                break;
            }
            else if(userInput.equals("list")) {
                printList(userTexts);
            }
            else {
                addToList(userTexts,currentIndex,userInput);
                ++currentIndex;
            }
        }
        printFarewell();
    }
}
