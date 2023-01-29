package dude.commands;

public class ListManager {
    private static final String[] list = new String[100];
    private static int index = 0;

    public static void addToList(String input){
        if (index == 100){
            Interface.listFullMessage();
            return;
        }
        list[index] = input;
        index += 1;
        Interface.addedMessage(input);
    }

    public static void printList(){
        System.out.println(Interface.LINE);
        for (int i = 0; i < index; i += 1){
            System.out.println((i+1) + ". " + list[i]);
        }
        System.out.println(Interface.LINE);
    }
}
