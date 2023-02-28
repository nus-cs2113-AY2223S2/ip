# User Guide

DukeRunner is a simple task manager that allows you to add, view, and manage your tasks. You can add todos, deadlines, and events to the list. This program also allows you to save tasks into your local hard drive and read from the file created on startup.. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Duke/DukeRunner.java` file, right-click it, and choose `Run DukeRunner.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

   ```

   ________         __         __________
   \______ \  __ __|  | __ ____\______   \__ __  ____   ____   ___________
   |    |  \|  |  \  |/ // __ \|       _/  |  \/    \ /    \_/ __ \_  __ \
   |    `   \  |  /    <\  ___/|    |   \  |  /   |  \   |  \  ___/|  | \/
   /_______  /____/|__|_ \\___  >____|_  /____/|___|  /___|  /\___  >__|
         \/           \/    \/       \/           \/     \/     \/
   Hello! I'm DukeRunner
   What can I do for you?

   ==============================
   ```

## Features

### Feature - Edit tasks

DukeRunner allows you to add and delete tasks of different types to keep track of them.

### Feature - Mark tasks

DukeRunner allows you to mark tasks as completed or uncompleted.

### Feature - Find tasks

DukeRunner allows you to find tasks using keywords.

### Feature - Save and Load

DukeRunner stores the tasks added upon exiting the program and loads them upon startup.

## Usage

### `todo` - Adds a new Todo task

Adds a Todo task to your list of tasks and displays the number of existing tasks.  
Example of usage: `todo math homework`

Expected outcome:

```
Got it. I've added this task:
[T][ ] math homework

Now you have 1 tasks in the list.

==============================
```

### `deadline` - Adds a new Deadline task

Adds a Deadline task to your list of tasks and displays the number of existing tasks.  
Example of usage: `deadline do science homework /by Monday`

Expected outcome:

```
Got it. I've added this task:
[D][ ] do science homework (by: Monday)

Now you have 2 tasks in the list.

==============================
```

### `event` - Adds a new Event task

Adds an Event task to your list of tasks and displays the number of existing tasks.  
Example of usage: `event school carnival /from Monday 9am /to 12pm`

Expected outcome:

```
Got it. I've added this task:
[E][ ] school carnival (from: Monday 9am to: 12pm)

Now you have 3 tasks in the list.

==============================
```

### `list` - Lists all current tasks

Displays all the tasks in the order they were added.  
Example of usage: `list`

Expected outcome:

```
1. [T][ ] math homework
2. [D][ ] science homework (by: Monday)
3. [E][ ] school carnival (from: Monday 9am to: 12pm)

You have 3 tasks in the list.

==============================
```

### `mark` - Marks a task as completed

Marks a task of certain task number as completed.  
Example of usage: `mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] math homework

==============================
```

### `unmark` - Unmarks a task from being completed

Unmarks a task of certain task number from being completed.  
Example of usage: `unmark 1`

Expected outcome:

```
OK, Ive marked this task as not done yet:
[T][ ] math homework

==============================
```

### `delete` - Deletes a task from the list of tasks

Deletes a task of certain task number from list of tasks.  
Example of usage: `delete 3`

Expected outcome:

```
Noted. I've removed this task:
[E][ ] school carnival (from: Monday 9am to: 12pm)

==============================
```

### `find` - Finds tasks

Find tasks using keywords found in the description of task.  
Example of usage: `find homework`

Expected outcome:

```
1. [T][ ] math homework
2. [D][ ] do science homework (by: Monday)
==============================
```

### `bye` - Exits DukeRunner

Exits the program and saves existing tasks into a text file.  
Example of usage: `bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

## Conclusion

That's it! With DukeRunner, you can easily manage your tasks and never miss a deadline again.
