package userinterface;

import commandhandler.Command;
import commandhandler.InputParser;
import inoutput.Input;
import inoutput.Output;
import tasktype.Event;
import tasktype.Task;
import tasktype.Stash;
import tasktype.Deadline;
import tasktype.Todo;

import java.util.Scanner;

import timer.Clock;
import timer.Break;
import timer.WorkTask;
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
