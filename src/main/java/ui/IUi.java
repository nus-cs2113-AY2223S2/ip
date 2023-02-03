package ui;

import java.util.ArrayList;

public interface IUi {
    public void printSystemMessage(String message);
    public <T> void printSystemMessage(ArrayList<T> list);
    public void printSystemErrorMessage(String message);
    public void greet();
    public void bye();
}
