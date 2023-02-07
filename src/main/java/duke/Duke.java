package duke;

import commands.Greeting;
import commands.HandleUserCommand;
import exceptions.InvalidTaskException;
import tasks.Task;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws InvalidTaskException {
        Greeting.greeting();
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        int counter = 1; //start from 1
        String userCommand = "";
        HandleUserCommand.handleCommand(userCommand, list, counter);
    }
}