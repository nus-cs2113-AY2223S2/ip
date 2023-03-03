# User Guide

This is a short user guide for CS2113 IP by Samueltansw

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/BtB.java` file, right-click it, and choose `Run BtB.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

```
 --------------------------------------------------
     ___     _       ___
    | _ )   | |_    | _ )
    | _ \   |  _|   | _ \
    |___/   _\__|   |___/
  _|"""""|_|"""""|_|"""""|
  "`-0-0-'"`-0-0-'"`-0-0-'
  Hello! I'm Bob the Bot, aka BtB.
  Please enter some tasks to the todo list.
 --------------------------------------------------
  ./data does not exist.
  The directory ./data has been created.
 --------------------------------------------------
  tasks.txt does not exists.
  The file tasks.txt has been created.
 --------------------------------------------------
  help.txt does not exists.
  The file help.txt has been created.
 --------------------------------------------------
  Here are the commands that are available in this application:
     1. list: lists all the tasks in the todo list
     2. todo <task>: to add a todo task in the task list
     3. deadline <task> /by <end date>: to add a deadline task that is to be completed by <end date>
     4. event <task> /from <start time> /to <end date>: add an event task that is from <start time> to <start date>
     5. mark <task number>: to indicate that a task indicated by <task number> is completed
     6. unmark <task number>: to indicate a previously completed task indicated by <task number> as incomplete
     7. delete <task number>: to delete task indicated by <task number> in the todo list
     8. bye: terminate the program
     9. help: prints the help messages
     10. find <keyword>: finds tasks from the list that contains the keyword.
     11. disableHelp: stop showing the help messages everytime the program boots up
     12. enableHelp: shows the help messages everytime the program boots up
 --------------------------------------------------
```

## Features

### Feature - Greet

Upon startup, BtB greets the user with a friendly welcome message

### Feature - Goodbye

On exit, BtB sends the user off with a kind goodbye message

### Feature - Tasks

BtB will keep track of your various tasks and display them so that you will not forget them

### Feature - Mark

You can tell BtB to mark tasks when you are done or unmark tasks when you need to complete them in the future

### Feature - Save and Load

BtB is capable of saving and loading data form previous interactions!

### Feature - Enable or Disable Help Messages

BtB will load help messages when it first starts. However, it can be disabled or enabled depending on the user's preference.

## Usage

### `list` - Lists the tasks stored

Displays the tasks stored thus far.

Example of usage:
`list`

Expected outcome:

```
 --------------------------------------------------
  Here are the tasks in your list:
     1.[T][ ] assignment 1
     2.[D][ ] assignment 2 (by: Sunday 2359H)
     3.[E][ ] group project meeting (from: Wed 3pm to: 5pm)
 --------------------------------------------------
```

### `todo` - Adds a new Todo task

Creates a new Todo task and stores it in the task list. Also displays the number of tasks currently stored in your list.

Example of usage:
`todo {description}`

Expected outcome:

```
 --------------------------------------------------
  Got it. I've added this task:
     [T][ ] jog at night
  Now you have 4 tasks in the list.
 --------------------------------------------------
```

### `deadline` - Adds a new Deadline task

Creates a new Deadline task and stores it in the task list. The date can be string as well.

Example of usage:
`deadline {description} /by {date}`

Expected outcome:

```
 --------------------------------------------------
  Got it. I've added this task:
     [D][ ] return book (by: Sunday)
  Now you have 5 tasks in the list.
 --------------------------------------------------
```

### `event` - Adds a new Event task

Creates a new Event task and stores it in the task list. The date can be a string as well.

Example of usage:
`event {description} /by {time}`

Expected outcome:

```
 --------------------------------------------------
  Got it. I've added this task:
     [E][ ] project meeting (from: wed 3pm to: 5pm)
  Now you have 6 tasks in the list.
 --------------------------------------------------
```

### `mark` - Marks a task

Mark the task number. Requires an input of a valid task number.

Example of usage:
`mark {task number}`

Expected outcome:

```
 --------------------------------------------------
  Great! I've marked this task as done:
     [D][X] assignment 2 (by: Sunday 2359H)
 --------------------------------------------------
```

### `unmark` - Unmarks a task

Unmarks the task number. Requires an input of a valid task number.

Example of usage:
`unmark {task number}`

Expected outcome:

```
 --------------------------------------------------
  OK, I've marked this task as not done yet:
     [D][ ] assignment 2 (by: Sunday 2359H)
 --------------------------------------------------
```

### `delete` - Deletes a task

Deletes the task at given task number. Also displays the number of tasks remaining after deletion. Requires an input of a valid task number.

Example of usage:
`delete {task number}`

Expected outcome:

```
 --------------------------------------------------
  Noted, I've removed this task:
     [T][X] jog at night
  Now you have 4 tasks in the list.
 --------------------------------------------------
```

### `find` - Find keyword

Search keyword within description of all tasks in task list. Displays a list of tasks with the given keyword in the description.

Example of usage:
`find {keyword}`

Expected outcome:

```
 --------------------------------------------------
  Here are the matching tasks in your list:
     1.[T][ ] assignment 1
     2.[D][ ] assignment 2 (by: Sunday 2359H)
 --------------------------------------------------
```

### `help` - Prints help messages

Prints the help messages for all the features of BtB.

Example of usage:
`help`

Expected outcome:

```
 --------------------------------------------------
  Here are the commands that are available in this application:
     1. list: lists all the tasks in the todo list
     2. todo <task>: to add a todo task in the task list
     3. deadline <task> /by <end date>: to add a deadline task that is to be completed by <end date>
     4. event <task> /from <start time> /to <end date>: add an event task that is from <start time> to <start date>
     5. mark <task number>: to indicate that a task indicated by <task number> is completed
     6. unmark <task number>: to indicate a previously completed task indicated by <task number> as incomplete
     7. delete <task number>: to delete task indicated by <task number> in the todo list
     8. bye: terminate the program
     9. help: prints the help messages
     10. find <keyword>: finds tasks from the list that contains the keyword.
     11. disableHelp: stop showing the help messages everytime the program boots up
     12. enableHelp: shows the help messages everytime the program boots up
 --------------------------------------------------
```

### `disableHelp` - Disable help messages

Disable the printing of help messages everytime the program starts.

Example of usage:
`disableHelp`

Expected outcome:

```
 --------------------------------------------------
  You have disabled help messages from showing every time application boots.
 --------------------------------------------------
```

The next time you open the program after disabling the help message, you should get:

```
 --------------------------------------------------
     ___     _       ___
    | _ )   | |_    | _ )
    | _ \   |  _|   | _ \
    |___/   _\__|   |___/
  _|"""""|_|"""""|_|"""""|
  "`-0-0-'"`-0-0-'"`-0-0-'
  Hello! I'm Bob the Bot, aka BtB.
  Please enter some tasks to the todo list.
 --------------------------------------------------
  tasks.txt is found.
  loading data...
 --------------------------------------------------
  Here are the tasks in your list:
     1.[T][ ] assignment 1
     2.[D][ ] assignment 2 (by: Sunday 2359H)
     3.[E][ ] group project meeting (from: Wed 3pm to: 5pm)
     4.[E][ ] project meeting (from: wed 3pm to: 5pm)
 --------------------------------------------------
```

### `enableHelp` - Enable help messages

Enable the printing of help messages everytime the program starts.

Example of usage:
`enableHelp`

Expected outcome:

```
    --------------------------------------------------
	 You have enabled help messages from showing every time application boots.
    --------------------------------------------------
```

The next time you open the program after enabling the help message, you should get:

```
 --------------------------------------------------
     ___     _       ___
    | _ )   | |_    | _ )
    | _ \   |  _|   | _ \
    |___/   _\__|   |___/
  _|"""""|_|"""""|_|"""""|
  "`-0-0-'"`-0-0-'"`-0-0-'
  Hello! I'm Bob the Bot, aka BtB.
  Please enter some tasks to the todo list.
 --------------------------------------------------
  tasks.txt is found.
  loading data...
 --------------------------------------------------
  Here are the tasks in your list:
     1.[T][ ] assignment 1
     2.[D][ ] assignment 2 (by: Sunday 2359H)
     3.[E][ ] group project meeting (from: Wed 3pm to: 5pm)
     4.[E][ ] project meeting (from: wed 3pm to: 5pm)
 --------------------------------------------------
  Here are the commands that are available in this application:
     1. list: lists all the tasks in the todo list
     2. todo <task>: to add a todo task in the task list
     3. deadline <task> /by <end date>: to add a deadline task that is to be completed by <end date>
     4. event <task> /from <start time> /to <end date>: add an event task that is from <start time> to <start date>
     5. mark <task number>: to indicate that a task indicated by <task number> is completed
     6. unmark <task number>: to indicate a previously completed task indicated by <task number> as incomplete
     7. delete <task number>: to delete task indicated by <task number> in the todo list
     8. bye: terminate the program
     9. help: prints the help messages
     10. find <keyword>: finds tasks from the list that contains the keyword.
     11. disableHelp: stop showing the help messages everytime the program boots up
     12. enableHelp: shows the help messages everytime the program boots up
 --------------------------------------------------
```

