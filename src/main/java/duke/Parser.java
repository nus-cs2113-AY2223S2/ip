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

    public void checkText(String line) throws IOException {

        if (line.equals("list")) {
            store.Retrievedata();
        }
        String arr[];
        arr = line.split(" ", 2);
        if (arr.length > 1 && arr[0].equals("mark")) {
            this.mark = new MarkCommand(ui, store, arrayLL);
            mark.complete(arr[1]);
        }
        if (arr.length > 1 && arr[0].equals("unmark")) {
            this.unmark = new UnMarkCommand(ui, store, arrayLL);
            unmark.complete(arr[1]);
        }
        if (arr.length > 1 && arr[0].equals("delete")) {
            this.delete = new DeleteCommand(ui, store, arrayLL);
            delete.complete(arr[1]);
        }
        if (arr.length > 1 && arr[0].equals("deadline")) {
            type = 0;
            String tempi = arr[1];
            this.add = new AddCommand(ui, store, arrayLL, type);
            add.complete(tempi);
        }
        if (arr.length > 1 && arr[0].equals("event")) {
            type = 1;
            String tempi = arr[1];
            this.add = new AddCommand(ui, store, arrayLL, type);
            add.complete(tempi);
        }
        if (arr.length>1 && arr[0].equals("find")){
            this.find = new FindCommand(ui, store, arrayLL);
            find.complete(arr[1]);
        }
        if (arr.length > 1 && arr[0].equals("todo")) {
            type = 2;
            String tempi = arr[1];
            this.add = new AddCommand(ui, store, arrayLL, type);
            add.complete(tempi);
        }
    }
}
