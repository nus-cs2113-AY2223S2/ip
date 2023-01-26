import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        DukeMessage.greet();

        int index = 1;
        String line;
        String[] storedLines = new String[102];
        Scanner in = new Scanner(System.in);
        DukeMessage.lineBreak();
        line = in.nextLine();

            while (!line.equals("bye")){

                if(line.equals("list")){
                    for (int i = 1; i < index; i++){
                        System.out.println(i + ": " + storedLines[i]);
                    }
                }
                else {
                    System.out.println("added: " + line);
                    storedLines[index] = line;
                    index +=1;
                }

                    DukeMessage.lineBreak();
                    line = in.nextLine();


            }

        DukeMessage.goodbye();

    }
}