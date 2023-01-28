package dude.commands;

import java.util.Scanner;

public abstract class Interface {
    public static final String LINE = "__________________________________";
    public static final String DUDE_LOGO = "╔═══╦╗─╔╦═══╦═══╦══╗╔═══╦════╗\n"
            + "╚╗╔╗║║ ║╠╗╔╗║╔══╣╔╗║║╔═╗║╔╗╔╗║\n"
            + " ║║║║║ ║║║║║║╚══╣╚╝╚╣║ ║╠╝║║╚╝\n"
            + " ║║║║║ ║║║║║║╔══╣╔═╗║║ ║║ ║║\n"
            + "╔╝╚╝║╚═╝╠╝╚╝║╚══╣╚═╝║╚═╝║ ║║\n"
            + "╚═══╩═══╩═══╩═══╩═══╩═══╝ ╚╝\n";
    public static void printGreeting(){
        System.out.println("Hello from\n" + DUDE_LOGO);
        System.out.println(LINE);
        System.out.println("Greetings! I am DUDE_BOT, how can i be of service to you?");
        System.out.println(LINE);
        System.out.println();
    }
    public static void printBye(){
        System.out.println(LINE);
        System.out.println("Goodbye, it was a pleasure to be of service to you");
        System.out.println(LINE);
        System.out.println();
    }

    public static void echoCommand(String command){
        System.out.println(LINE);
        System.out.println(command);
        System.out.println(LINE);
        System.out.println();
    }
    public static void readInput(){
        Scanner in = new Scanner(System.in);
        String input;
        while(true){
            input = in.nextLine();
            if(input.equals("bye")){
                break;
            }
            echoCommand(input);
        }
        in.close();
    }
}
