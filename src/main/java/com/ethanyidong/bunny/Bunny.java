package com.ethanyidong.bunny;

import com.ethanyidong.bunny.arg.InvalidCommandException;
import com.ethanyidong.bunny.cmd.ExecutableCommand;
import com.ethanyidong.bunny.cmd.TokenizedCommand;

import java.util.Scanner;

public class Bunny {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BunnySession bunny = new BunnySession();

        bunny.printMessage("Hello! I'm Bunny.\nWhat can I do for you?");

        while (!bunny.isQuit()) {
            String input = in.nextLine();
            TokenizedCommand inputCommand = new TokenizedCommand(input);
            ExecutableCommand executableCommand;
            try {
                executableCommand = ExecutableCommand.validateAndParse(bunny, inputCommand);
            } catch (InvalidCommandException ice) {
                bunny.printMessage(ice.toString());
                continue;
            }
            executableCommand.execute(bunny);
        }
        bunny.printMessage("Bye. Hope to see you again soon!");
    }
}
