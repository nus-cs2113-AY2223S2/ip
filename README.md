# Duke project

This is a basic task manager which uses the command line interface

## Features

1. Supports three different types of tasks: `Todo`, `Deadline` and `Event`
2. Supports marking tasks as completed or not completed
3. Displays stored tasks
4. Reminds user about upcoming deadlines
5. Supports searching for previously added tasks

- For usage, do refer to the [user guide](#User-Guide)

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   ------------------------------------------------------------
   Hello! I'm Duke. Hope you are having a wonderful day
   What can I do for you?
   ------------------------------------------------------------
   ```

## User-Guide

### Adding a new Todo
- Syntax: `todo [task-description]`

### Adding a new Deadline
- Syntax: `deadline [task-description] /by [date]`
- Date Format: "yyyy-M-d" or "yyyy-M-d HH:mm"
   - If no time is specified, Duke sets time to 23:59
- Deadline cannot be set in the past, else task will not be added

### Adding a new Event
- Syntax: `event [task-description] /from [date] /to [date]`
- Date Format: "yyyy-M-d" or "yyyy-M-d HH:mm"
   - If no time is specified, Duke sets time to 23:59
- End Date cannot be set in the past, else task will not be added
- Start Date cannot be after End Date, else task will not be added

### Displaying all tasks
- Syntax: `list`

### Searching for tasks
- Syntax: `find [query]`

### Displaying upcoming tasks
- Syntax: `upcoming`
- Returns all tasks with deadline within 3 days

### Marking task as completed
- Syntax: `mark [index]`
- Recommended to use `list` before `mark` to get the task index

### Marking task as not completed
- Syntax: `unmark [index]`
- Recommended to use `list` before `unmark` to get the task index

### Deleting task
- Syntax: `delete [index]`
- Recommended to use `list` before `delete` to get the task index

### Exiting
- Syntax: `bye`