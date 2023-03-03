# User Guide

Welcome to the HinaBot User Guide! HinaBot is a personal assistant program that helps you 
maintain a to-do list.

## Features 

### Support for multiple task types

HinaBot supports 3 different types of items on a to-do list: regular tasks, deadlines (tasks which have a due-date),
and events (which have a start and end date).

### Search function

HinaBot allows you to search your to-do list for tasks that contain a particular substring, making it easier to keep
track of large numbers of tasks.

### Delete and mark functions

HinaBot allows you to mark tasks as done or not done and also supports deletion of a task.

### Save and load function
All changes you make to your to-do list are stored locally, meaning you can pick up right where you left off.

## Usage

### `todo` - Add a regular task to the to-do list

Adds a regular task (no due date) to the to-do list. By default, the task is set as not done.

Example of usage: 

`todo buy milk`

Expected outcome:

The task is added to your to-do list and HinaBot announces how many tasks are currently on your list

```
Noted! This task has been added:
[T][ ] buy milk
There are 1 items on your list.
```
### `deadline` - Add a deadline to the to-do list

Adds a deadline (with a do-by date in dd-MMM-yyyy HH:mm format) to the to-do list. By default, the task is set as not done.

Example of usage:

`deadline Assignment 1 /by 04-Apr-2023 23:59`

Expected outcome:

The deadline is added to your to-do list and HinaBot announces how many tasks are currently on your list

```
Noted! This task has been added:
[D][ ] Assignment 1  (by: 04-Apr-2023 23:59)
There are 2 items on your list
```
### `event` - Add an event to the to-do list

Adds an event (with start and end times in dd-MMM-yyyy HH:mm format) to the to-do list. By default, the task is set as not done.

Example of usage:

`event Midterms /from 03-Mar-2023 13:00 /to 03-Mar-2023 17:30`

Expected outcome:

The deadline is added to your to-do list and HinaBot announces how many tasks are currently on your list

```
Noted! This task has been added:
[E][ ] Midterms (from: 03-Mar-2023 13:00 to: 03-Mar-2023 17:30)
There are 2 items on your list
```
### `list` - Lists all tasks in the to-do list

Prints a list of all tasks in the to-do list including the task type and done status.

Example of usage:

`list`

Expected outcome:

The deadline is added to your to-do list and HinaBot announces how many tasks are currently on your list

```
1. [T][ ] buy milk
2. [D][ ] Assignment 1  (by: 04-Apr-2023 23:59)
3. [E][ ] Midterms (from: 03-Mar-2023 13:00 to: 03-Mar-2023 17:30)
```
### `mark` - Marks a task as done

Marks an item by its index on the to-do list as done. This is indicated by an 'X' in the checkbox next to 
its description.

Example of usage:

`mark 1`

Expected outcome:

An acknowledgement message and an 'X' is printed in the checkbox next to item 1 on the to-do list.
```
Roger that! This task is marked as done: 
[T][X] buy milk
```

### `unmark` - Marks a task as not done

Marks an item by its index on the to-do list as not done. This is indicated by a blank checkbox next to
its description.

Example of usage:

`unmark 1`

Expected outcome:

An acknowledgement message and a blank checkbox is printed next to item 1 on the to-do list.
```
Roger that! This task is marked as not done: 
[T][ ] buy milk
```

### `delete` - Remove a task from the to-do list

Removes the item at the specified index from the to-do list.

Example of usage:

`delete 3`

Expected outcome:

An acknowledgement message and the description of the task being removed is shown, followed by an announcement
of the size of the remaining list.
```
Got it! This task will be removed:
[E][ ] Midterms (from: 03-Mar-2023 13:00 to: 03-Mar-2023 17:30)
There are 2 items on your list.
```

### `find` - Search for a task

Searches the to-do list for all tasks with a description containing the substring provided by the user.

Example of usage:

`find buy`

Expected outcome:

Any matching tasks, along with their index on the list, are printed.

```
Found a match! Here are the results master!
1. [T][ ] buy milk
```

### `bye` - Exits the program

Terminates the program and prints a goodbye message.

Example of usage:

`bye`

Expected outcome:

The program exits with code 0 and a goodbye message is shown.

```
Goodbye master, let's meet again soon...

Process finished with exit code 0
```