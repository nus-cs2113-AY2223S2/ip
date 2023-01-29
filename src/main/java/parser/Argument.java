package parser;

import java.util.HashMap;

public class Argument {
    private Enum<?> command;
    private int index;
    private HashMap<String, String> variableArguments;

    public Argument(Enum<?> command, int index, HashMap<String, String> variableArguments) {
        setCommand(command);
        setIndex(index);
        this.variableArguments = variableArguments;
    }
    public Argument(Enum<?> command, HashMap<String, String> variableArguments) {
        setCommand(command);
        this.variableArguments = variableArguments;
    }
    public Argument(Enum<?> command, int index) {
        this(command, index, null);
    }
    public Argument() {
        this(null,-1, null);
    }
    
    public Enum<?> getCommand() {
        return command;
    }
    public void setCommand(Enum<?> command) {
        this.command = command;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public HashMap<String, String> getVariableArguments() {
        return variableArguments;
    }
    public void setVariableArguments(HashMap<String, String> variableArguments) {
        this.variableArguments = variableArguments;
    }
}
