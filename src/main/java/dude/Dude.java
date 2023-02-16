package dude;

import dude.commands.Interface;
import dude.commands.SaveData;
import dude.task.ListManager;
import exception.EmptyInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Dude {

    public static void main(String[] args){
        Interface.printGreeting();
        SaveData.loadSaved();
        Interface.readInput();
        SaveData.writeToFile();
        Interface.printBye();
    }
}
