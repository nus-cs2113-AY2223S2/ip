import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________\n"
                            + logo
                            + "Hello! I'm Duke!\n"
                            + "What I can do for you?\n"
                            + "____________________________________________________________\n"
                            );
        String inputText;
        String outputMessage;
        String[] inputList = new String[100];
        int inputCount = 0;
        boolean exit = false;
        while (true) {
            inputText = scanner.nextLine();
            switch(inputText) {
                case "list":
                    outputMessage = getListInputs(inputList, inputCount);
                    break; 
                case "bye":
                    exit = true;
                    outputMessage = "Bye. Hope to see you again soon!";
                    break;
                default: 
                    addInput(inputText, inputList, inputCount++);
                    outputMessage = "added: " + inputText;
            }
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + outputMessage);
            System.out.println("\t____________________________________________________________");
            if (exit) {
                break;
            }
        }

    }
    public static String getListInputs (String[] inputList, int inputCount){
        String listInputs = "";
        for (int i = 0; i < inputCount; i++) {
            listInputs +=  String.format("%3d. ", (i+1)) + inputList[i] + "\n\t";
        }
        return listInputs;
    }
    public static void addInput (String inputText, String[] inputList, int inputCount){
        inputList[inputCount] = inputText;
    }


}
