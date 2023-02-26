import java.io.IOException;
import java.util.Scanner;
public class Goot {
    public static void main(String[] args) {
    UserInterface.greet();
    Storage storage = new Storage();
    try{
        Storage.loadFileContentsToTaskArray();
    }
    catch (IOException e){
        GootExceptionHandler.fileNotFound();
        Storage.makeNewFile();
    }
    while (true) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if(input.equals("bye")){
            UserInterface.farewellMessage();
            break;
        }
        Parser.interpretInput(input);
    }
    }
}
