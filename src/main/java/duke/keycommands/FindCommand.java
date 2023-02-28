package duke.keycommands;

import duke.common.Common;
public class FindCommand {
    private String keyword;
    private static final String FIND_MESSAGE = "Here are the matching tasks in your list:";
    public FindCommand(String keyword) {
        this.keyword = keyword;
        findTask();
    }

    private void findTask() {
        System.out.println(FIND_MESSAGE);
        for (int i = 0; i < Common.tasks.size(); i++) {
            if (Common.tasks.get(i).getContent().contains(keyword)) {
                System.out.println((i + 1) + ". " + Common.tasks.get(i).printTask());
            }
        }
    }

}
