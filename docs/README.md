# Duke individual project

This is a project for a task organiser app. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.


## User Guide
1. [Setting up](#setting-up)
2. [Features](#features)
    1. [Adding a task```todo```](#adding-a-tasktodo),[Adding a task```deadline```](#adding-a-task-deadline),[Adding a task```event```](#adding-a-task-event)
    2. [Deleting a task```delete```](#deleting-a-task-delete)
    3. [Marking a task as done```mark```](#Marking-a-task-as-done-mark)
    4. [Unmarking a task as done```unmark```](#Unmarking-a-task-as-done-unmark)
    5. [Finding tasks```find```](#Finding-tasks-find)
    6. [Displaying all tasks```list```](#Displaying-all-tasks-list)
    7. [Exit Duke`bye`](#exit-duke-bye)

## Setting Up
Prerequisites: JDK 11, update Intellij to the most recent version.

When run correctly, you should see something like this below as the output:
   ````
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ````

## Features
This section includes the functionalities of Duke, as well as the commands' formats.
### Adding a task `todo`

The ```todo``` command allows you to add in tasks that do not have a deadline or duration.

Format: ```todo [TASK_NAME]```

Example: ```todo sleep```

### Adding a task```deadline```

The `deadline` command allows you to add in tasks that have a specific deadline. Separate the `[TASK_NAME]` and `[DEADLINE]` with a `/`.

Format: `deadline [TASK_NAME] /[DEADLINE]`

Example: `deadline sleep /by 10pm`

### Adding a task```event```

The `event` command allows you to add in tasks that occurs within a period of time. Separate the `[TASK_NAME]`,`[FROM]` and `[TO]` with `/`.

Format: `event [TASK_NAME] /[FROM] /[TO]`

Example: `event sleep /from 10pm /to 8am`

### Deleting a task```delete```

The `delete` command allows you to delete any current task specified by `[INDEX]` in the list.

Format: `delete [INDEX]`

Example: `delete 1` will remove task *sleep* from the list.

*Note: `delete` command could not be undo once called.*

### Marking a task as done```mark```

The `mark` command allows you to mark any task specified by the`[INDEX]` as done. Marking the task as done does not delete the task from the list.

Format: `mark [INDEX]`

Example: `mark 1` will mark task *sleep* as done, indicated by `[x]`.

### unmarking a task as done```unmark```

The `unmark` command allows you to unmark any previously marked task. The task to be unmarked is specified by the`[INDEX]`.

Format: `unmark [INDEX]`

Example: `unmark 1` will unmark task *sleep*, indicated by `[ ]`.

### Finding tasks```find```

The `find` command allows you to find any tasks containing the substring `[NAME]`. It will return a list of tasks containing `[NAME]`.

Format: `find [NAME]`

Example: `find sleep` or `find lee` will return task *sleep*.

### Displaying all tasks```list```

The `list` command displays a list of current tasks.

Format: `list`

Example: `list` will display `1. [T][X] sleep`

### Exit duke```bye```

The `bye` command will exit the program.
