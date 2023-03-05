# User Guide

## Features 

### Add, mark, unmark, list and delete task in your task list

You can add mark, unmark, list and delete task in your task list as needed with command lines.

### Realtime storing updated task into your drive

Whenever a command is issued, the updated task list will be stored to your disk.

## Usage

### `list`

List all the tasks in your task list.

Example of usage: 

`list`

Expected outcome:

All the tasks in your task list.

```
    ____________________________________________________________
    Here are the tasks in your list:
    1. [T][ ] daf
    2. [T][ ] fdjkasdafdfjkaslfj
    3. [T][ ] www
    ____________________________________________________________
```
### `todo / deadline / event`

Add a todo/ deadline/ event task to your task list.

Example of usage:

`todo abc`

Expected outcome:

todo task 'abc' is added to your task list.

```
    ____________________________________________________________
    Got it. I've added this task:
    [T][ ] abc
    Now you have 4 tasks in the list.
    ____________________________________________________________
```

### `mark/ unmark`

Mark a task in your task list as Done or Not Done.

Example of usage:

`mark 3`

Expected outcome:

task with index 3 is marked as Done.

```
    ____________________________________________________________
    Nice! I've marked this task as done:
    [T][X] www
    ____________________________________________________________
```

### `delete`

Delete a task in your task list.

Example of usage:

`delete 2`

Expected outcome:

task with index 2 is deleted.

```
    ____________________________________________________________
    Noted. I've removed this task:
    [T][ ] fdjkasdafdfjkaslfj
    Now you have 3 tasks in the list.
    ____________________________________________________________
```

### `find`

Find all the tasks in your task list containing a string of characters you specify.

Example of usage:

`find daf`

Expected outcome:

tasks containing string of 'daf' are displayed.

```
    ____________________________________________________________
    Here are the matching tasks in your list:
    1. [T][ ] daf
    4. [T][ ] dfjsdaffjdk
    5. [T][ ] jjjjdaf
    ____________________________________________________________
```

### `bye`

Exit the program.

Example of usage:

`bye`

Expected outcome:

Program Duke is exited.

```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```