package userinterface;
import inoutput.Input;

public class UserHandler {
    private static Input inOut;
    public UserHandler(Input inOut){
        this.inOut = inOut;
    }
    public void getUserName() {
        System.out.println("What is your name? (Please enter name)\n");
        String userName = inOut.readInput();
        System.out.println("Hello, " + userName + ". You may enter 'list' to view your current To-Do list.");
    }
}
