package util;

import java.util.ArrayList;

/**
 * provides methods for creating a list of tasks and initializing it.
 */
public class TaskList {
    public static ArrayList<Task> createList() {
        ArrayList<Task> commands = new ArrayList<Task>();
        return commands;
    }
    public static ArrayList<Integer> find(String keyword,ArrayList<Task> commands){
        ArrayList<Integer> relatedIndexes= new ArrayList<Integer>();
        for (int i=0;i<commands.size();i++){
            Task task=commands.get(i);
            if (task.description.contains(keyword)){
                relatedIndexes.add(i);
            }
        }
        return relatedIndexes;
    }
    public TaskList() {
    }

}
