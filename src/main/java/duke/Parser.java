package duke;

import java.io.IOException;

public class Parser {
    private AddCommand add;
    private DeleteCommand delete;
    private MarkCommand mark;
    private UnMarkCommand unmark;
    private FindCommand find;
    private Ui ui;
    private Storage store;
    private TaskList arrayLL;
    private int type;

    public Parser(Ui ui, Storage store, TaskList arrayLL) {
        this.ui = ui;
        this.store = store;
        this.arrayLL = arrayLL;
    }

    /**
     * This redirects the code depending on the input by the user
     * @param line Depending on the line input by the user and checks what function it wants to be carried out
     * @throws IOException so that the requirements are meant by user input and there is no error
     */

    public void checkText(String line) throws IOException {

        if (line.equals("list")) {
            store.Retrievedata();
        }
        if (line.contains("unmark")) {
            this.unmark = new UnMarkCommand(ui, store, arrayLL);
            unmark.complete(line);
        }
        if (line.contains("mark")) {
            this.mark = new MarkCommand(ui, store, arrayLL);
            mark.complete(line);
        }
        if (line.contains("delete")) {
            this.delete = new DeleteCommand(ui, store, arrayLL);
            delete.complete(line);
        }
        if (line.contains("deadline")) {
            type = 0;
            this.add = new AddCommand(ui, store, arrayLL, type);
            add.complete(line);
        }
        if (line.contains("event")) {
            type = 1;
            this.add = new AddCommand(ui, store, arrayLL, type);
            add.complete(line);
        }
        if (line.contains("find")){
            this.find = new FindCommand(ui, store, arrayLL);
            find.complete(line);
        }
        if (line.contains("todo")) {
            type = 2;
            this.add = new AddCommand(ui, store, arrayLL, type);
            add.complete(line);
        }
    }
}
