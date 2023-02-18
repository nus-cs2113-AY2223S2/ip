import enums.DialogueTypes;
import managers.InputManager;
import managers.OutputDialogueManager;

import java.util.Scanner;
public class Main {
    private InputManager inputManager;
    private OutputDialogueManager display;
    public Main () {
        inputManager = new InputManager();
        display = new OutputDialogueManager();
    }

    public void run() {
        display.printInteraction(DialogueTypes.GREETINGS);
        boolean isDone = false;
        do {
            isDone = inputManager.processOneInput();
        } while (!isDone);
    }
    public static void main(String[] args) {
        new Main().run();
    }

}
