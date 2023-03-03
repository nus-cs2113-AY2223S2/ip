# Duke - User Guide

## About:
Duke is an application that uses a command-line interface(CLI) to interact with the user. Duke can be used by users to keep track of various types of tasks. These types of tasks are *ToDo* , *Deadline* and *Event*.

## Quick Start:
1. Ensure that **Java 11** or above is installed on your computer.
2. Download the latest [duke.jar](https://github.com/matthew-liu-zhenjie/ip/releases).
3. Transfer the duke.jar file to a preferred location.
4. Launch the terminal on your computer and navigate to the folder holding the duke.jar file.
5. Type the command `Java -jar duke.jar` to launch the Duke application.

## Features 

### Add Tasks

Add a task into the task list.

### Delete Tasks

Delete specific task within the task list.

### Mark task as done/not done

Mark a specified task as done or not done.

### Find Tasks

Filters tasks in the task list based on the task description matching a provided query.

### Save Tasks

Saves the task list to a file.

### Load Tasks

Loads the task list from a file.


## Usage

### `help` - Displays commands and their usages
Displays all the commands recognized by Duke for the user as well as sample usages

**Format: `help`**

### `todo` - Add a todo task to the task list

Adds a _Task_ of type _ToDo_ to the task list. The task description is provided through user input. 

**Format: `todo <task description>`**

Example of usage: 

```
Enter Command: todo go for a run
[Command entered: todo go for a run]
_______________________________________________________________
Got it. I've added this to duke: 
    [T][ ] go for a run
_______________________________________________________________
```

### `deadline` - Add a Deadline task to the task list

Adds a _Task_ of type _Deadline_ to the task list. The task description and due date is provided through user input. 

**Format: `deadline <task description> /by <due date>`**

Example of usage: 

```
Enter Command: deadline Finish school report /by 13 March
[Command entered: deadline Finish school report /by 13 March]
_______________________________________________________________
Got it. I've added this to duke: 
    [D][ ] Finish school report  (by: 13 March)
_______________________________________________________________
```

### `event` - Add a Event task to the task list

Adds a _Task_ of type _Deadline_ to the task list. The task description and due date is provided through user input. 

**Format: `event <task description> /from <start date> /to <end date>`**

Example of usage: 

```
Enter Command: event Chess competition /from tomorrow 3pm /to tomorrow 6pm
[Command entered: event Chess competition /from tomorrow 3pm /to tomorrow 6pm]
_______________________________________________________________
Got it. I've added this to duke: 
    [E][ ] Chess competition  (From: tomorrow 3pm to: tomorrow 6pm)
_______________________________________________________________
```

### `mark` - Mark a task as done

Marks a _Task_ at the _specified index_ as done in the task list. 

**Format: `mark <task index>`**

Example of usage: 

```
Enter Command: mark 1
[Command entered: mark 1]
_______________________________________________________________
Nice! I've marked this task as done: 
[D][X] Finish school report  (by: 13 March)
_______________________________________________________________
```
### `unmark` - Mark a task as not done

Marks a _Task_ at the _specified index_ as not done in the task list. 

**Format: `mark <task index>`**

Example of usage: 

```
Enter Command: unmark 1
[Command entered: unmark 1]
_______________________________________________________________
Nice! I've marked this task as not done yet: 
[D][ ] Finish school report  (by: 13 March)
_______________________________________________________________
```

### `list` - List all tasks
Lists all tasks that are in the task list. Each row indicates the index of the task followed by its type and completion status as well as its desription.

**Format: `list`**

Example of usage:
```
Enter Command: list
[Command entered: list]
_______________________________________________________________
Here are the tasks that you have currently:
1.[T][ ] go for a run
2.[D][ ] Finish school report  (by: 13 March)
3.[E][ ] Chess competition  (From: tomorrow 3pm to: tomorrow 6pm)
_______________________________________________________________
```

### `find` - Filters task list
Filters the task list and returns results whose description matches the query provided by the user.

**Format: `find <query>`**

Example of usage:
```
Enter Command: find school
[Command entered: find school]
_______________________________________________________________
Here are the matching tasks in your list:
   1. [D][ ] Finish school report  (by: 13 March)
_______________________________________________________________
```

### `delete` - deletes a task 
Deletes a task at the specified index from the task list.

**Format: `delete <task index>`**

Example of usage:
```
Enter Command: delete 3
[Command entered: delete 3]
_______________________________________________________________
Noted. I've removed this task: 
    [E][ ] Chess competition  (From: tomorrow 3pm to: tomorrow 6pm)
Now you have 2 tasks in the list
_______________________________________________________________
```

### Save Tasks:
The task list is saved automatically into a **_save.json_** file in the same folder as the _duke.jar_ file.

### Load Tasks:
Loads the task list from **_save.json_** provided it exists and is in the same folder as the _duke.jar_ file. If the file is corrupted or does not exist, the application will initialize an empty task list. 

### `bye` - Exits the application
Exits the application.

**Format: `bye`**

Example of usage:
```
Enter Command: bye
[Command entered: bye]
_______________________________________________________________
Bye! Hope to see you again!
_______________________________________________________________
_______________________________________________________________
   ______                     __    __                 
  / ____/  ____   ____   ____/ /   / /_    __  __  ___ 
 / / __   / __ \ / __ \ / __  /   / __ \  / / / / / _ \
/ /_/ /  / /_/ // /_/ // /_/ /   / /_/ / / /_/ / /  __/
\____/   \____/ \____/ \__,_/   /_.___/  \__, /  \___/ 
                                        /____/         
_______________________________________________________________
_______________________________________________________________
```
