import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

public class FindTaskCommand extends Command{
    @Override
    public void executeCommand(TaskList taskList, String input) {
        String keyword = input.split(" ")[1].trim().toLowerCase();
        ArrayList<Task> matchedTasks;
        matchedTasks = (ArrayList<Task>) taskList.stream()                                              // casts list to ArrayList
                .filter(t -> t.getTaskName().trim().toLowerCase().contains(keyword)).collect(toList());
        System.out.println(Messages.DIVIDER);
        if (!matchedTasks.isEmpty()){
            System.out.println("Well, here is the list of tasks matching your keyword!");
        }
        for (Task task : matchedTasks){
            System.out.println(task);
        }
        if (matchedTasks.isEmpty()){
            System.out.println("Oops, there are no tasks matching the keyword! Try again with another keyword");
        }
        System.out.println(Messages.DIVIDER);

    }
}
