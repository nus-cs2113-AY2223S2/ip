# User Guide

## Features 

### Add tasks

Add 3 different kinds of tasks to your list
1. todo tasks 
2. deadline tasks
3. event tasks

### Display tasks

Display all the tasks currently in the task list

### Mark/Unmark tasks

Mark completed tasks with a 'X'
ability to unmark the 'X' if there was a mistake

### Command validation

Informs users if commands are invalid

### Save/Load tasks

Duke remembers tasks saved from previous sessions and will load them up whenever a new session is started

### Filter tasks

Filter the task list to display only tasks containing a specific keyword

## Usage

### `list` - Lists all tasks currently stored

Example of list:

```
Here are the tasks in your list:
1. [T][ ] cs1010 tutorial
2. [D][ ] cs1010 lab1 (by: Tuesday)
3. [E][ ] cs2101 project meeting (from: 2pm to: 5pm)
```

### `todo` - creates a basic task

Example of usage: 

`todo CS1231s Assignment 1`

### `deadline` - creates a basic task with a deadline

Use `/by` to indicate the deadline

Example of usage:

`deadline CS1231s Assignment 1 /by Sunday`

### `event` - creates a basic task with a start and end time

Use `/from` to indicate the start time and `/to` to indicate end time

Example of usage:

`deadline CS1231s Assignment 1 /from 2pm /to 6pm`

### `mark` - checks task as completed

use the command along with the index of the item to be marked

Example of usage:

`mark 1` - marks the first item in the list with 'X'


### `unmark` - unchecks tasks as uncompleted

use the command along with the index of the item to be marked

Example of usage:

`unmark 1` unmark any 'X' from the first item on the list

### `delete` - deletes a task from the list

use the command along with the index of the item to be deleted

Example of usage:

`delete 1` - deletes the first item on the list

### `find` - filters the list to see only tasks containing a certain keyword

use the command along with the queried keyword

Example of usage:

`find cs1010` 

Standard list:

```
Here are the tasks in your list:
1. [T][ ] cs1010 tutorial
2. [D][ ] cs1010 lab1 (by: Tuesday)
3. [E][ ] cs2101 project meeting (from: 2pm to: 5pm)
```


Expected output for `find cs1010`:

```
Here are the matching tasks in your list:
1: [T][ ] cs1010 tutorial
2: [D][ ] cs1010 lab1    (by: Tuesday)
```

### `bye` - Ends the current session of Duke

