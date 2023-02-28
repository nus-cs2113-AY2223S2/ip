# User Guide
Duke is a Command Line Interface (CLI) application that allows you to keep track of your tasks!
If you can type fast, this is the perfect application for you.
## Features 

### Adding a todo
Add a todo task to the list.

### Adding a deadline
Adds a deadline to the list.

### Adding a event
Adds a event task to the list.

### Mark a task
Mark a task on the list as done.

### Unmark a task
Mark a done task as not done.

### List all the task
List all the task in the list.

### Find task
Find a task in the list.

### Delete a task
Delete a task in the list.

### Exits application
Exits the CLI application.

### Saving data to a file
List of Tasks created is saved to a file.

## Usage

### `todo` - Add a todo task

Adds a todo task to the list.

Format: 

`todo <description>`

Example of usage: 

`todo play a game`

Expected outcome:

Duke will tell you that the task has been added and marked it with `T`.

```
Got it. I've added this task:
[T][ ] play a game
Now you have 1 tasks in the list.
```

### `deadline` - Add a deadline task

Adds a deadline task to the list.

Format:

`deadline <description> /by <specify by when>`

Example of usage:

`deadline finish math homework /by 1st March`

Expected outcome:

Duke will tell you that the task has been added and marked it with `D`.

```
Got it. I've added this task:
[D][ ] finish math homework (by: 1st March)
Now you have 2 tasks in the list.
```

### `event` - Add a event task

Adds a event task to the list.

Format:

`event <description> /from <specify from when> /to <specify to when>`

Example of usage:

`event attend Open House /from 3rd March 10am /to 4th March 4pm`

Expected outcome:

Duke will tell you that the task has been added and marked it with `E`.

```
Got it. I've added this task:
[E][ ] attend Open House (from: 3rd March 10am to: 4th March 4pm)
Now you have 3 tasks in the list.
```

### `mark` - mark a task

Mark a task as done.

Format:

`mark <task number>`

Example of usage:

`mark 1`

Expected outcome:

Duke will mark the task as done and mark the 2nd `[ ]` with `X`.

```
Nice! I've marked this task as done:
[T][X] play a game
```

### `unmark` - unmark a task

unmark a marked task.

Format:

`unmark <task number>`

Example of usage:

`unmark 1`

Expected outcome:

Duke will unmark the task as done and remove the `X` from the 2nd `[ ]`.

```
OK, I've marked this task as not done yet:
[T][ ] play a game
```

### `list` - list all the task

List all the task currently in the list.

Format:

`list`

Example of usage:

`list`

Expected outcome:

Duke will list all the task in the list.

```
Here are the tasks in your list:
1.[T][ ] play a game
2.[D][ ] finish math homework (by: 1st March)
3.[E][ ] attend Open House (from: 3rd March 10am to: 4th March 4pm)
```

### `find` - find the tasks

Find the task in the list.

Format:

`find <keyword>`

Example of usage:

`find Open House`

Expected outcome:

Duke will iterate through the list to find all the task matching the keyword.

```
Here are the matching tasks in your list:
3.[E][ ] attend Open House (from: 3rd March 10am to: 4th March 4pm)
```

### `delete` - delete a task

Delete a task from the list.

Format:

`delete <integer>`

Example of usage:

`delete 2`

Expected outcome:

Duke will delete the task based on the index specified and remove it from the list. It will also inform you what task was deleted.

```
Noted. I've removed this task:
[D][ ] finish math homework (by: 1st March)
Now you have 2 tasks in the list.
```

### `bye` - Exit the program

Exit out of the program.

Format:

`bye`

Example of usage:

`bye`

Expected outcome:

Duke terminate itself.

```
Bye. Hope to see you again soon!
```

## Saving data to a file
Duke will save all the tasks in the list into a file everytime a command 
modifying the list is executed. The data will be reloaded into the program when
Duke is started up again.

The data is stored under `data/tasklist.txt`  
The data is stored in the following format:
```
T|0| play game 
E|0| attend Open House |from: 3rd March 10am to: 4th March 4pm
```
