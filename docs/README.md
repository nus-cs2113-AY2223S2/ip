# Psyduck User Guide

# Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest version of Psyduck from [here](https://github.com/dendendenden04/ip/releases).
3. Move the file to a location where you want to run Psyduck from.
4. Open a command terminal and navigate to the location where psyduck.jar is. 
4. Use the following command to run the program: `java -jar psyduck.jar`
5. If Psyduck runs successfully, you will see the following welcome message
```
--------------------------------------------------------------------------------
Hi I am Psyduck! How can I help you?
--------------------------------------------------------------------------------
```

## Features 

Add tasks of 3 different types (To-do, Deadline, Event)

Remove a task from the to-do list.

Mark a task from the to-do list as done.

Unmark a task from the to-do list as not done.

List out all the tasks in the to-do list.

Find all the tasks in the list related to a keyword.

## Usage

### `todo` - Adds a to-do task.

Adds a to-do task to the list and shows the number of tasks currently in the list.

Example of usage: 

`todo Visit Mt.Moon`

Expected outcome:

The to-do task is added to the list. Shows the number of tasks currently in the list.

```
--------------------------------------------------------------------------------
Psyduck has added the task: [T][ ] Visit Mt.Moon
You now have: 1 task
--------------------------------------------------------------------------------
```
### `deadline` - Adds a deadline task.

Adds a deadline task to the list and shows the number of tasks currently in the list.

Example of usage:

`deadline Drink the juice in the fridge /by 19th February`

Expected outcome:

The deadline task is added to the list. Shows the number of tasks currently in the list.

```
--------------------------------------------------------------------------------
Psyduck has added the task: [D][ ] Drink the juice in the fridge  (by: 19th February)
You now have: 1 tasks
--------------------------------------------------------------------------------
```

### `event` - Adds an event task.

Adds an event task to the list and shows the number of tasks currently in the list.

Example of usage:

`event Festival at Celadon City /from Monday /to Saturday`

Expected outcome:

The event task is added to the list. Shows the number of tasks currently in the list.

```
--------------------------------------------------------------------------------
Psyduck has added the task: [E][ ] Festival at Celadon City  (from: Monday  to: Saturday)
You now have: 1 tasks
--------------------------------------------------------------------------------
```

### `event` - Adds an event task.

Adds an event task to the list and shows the number of tasks currently in the list.

Example of usage:

`event Festival at Celadon City /from Monday /to Saturday`

Expected outcome:

The event task is added to the list. Shows the number of tasks currently in the list.

```
--------------------------------------------------------------------------------
Psyduck has added the task: [E][ ] Festival at Celadon City  (from: Monday  to: Saturday)
You now have: 2 tasks
--------------------------------------------------------------------------------
```
### `list` - List out all the tasks.

Lists out all the tasks that are in the to-do list.

Example of usage:

`list`

Expected outcome:

Shows the details of all the tasks in the list.  
The first `[T]` denotes that the task is a to-do task.\
The second `[ ]` denotes whether the task is marked done.
> `[]` denotes the task is not done.\
> `[X]` denotes the task is done.

```
--------------------------------------------------------------------------------
Here are the tasks currently in your list: 
1.[T][ ] Visit Mt.Moon
2.[D][ ] Drink the juice in the fridge  (by: 19th February)
3.[E][ ] Festival at Celadon City  (from: Monday  to: Saturday)
--------------------------------------------------------------------------------
```
### `mark` - Marks a task as done.

Marks a task as done. Shows the details of the task that is marked done.

Example of usage:

For the list that is shown below
```
--------------------------------------------------------------------------------
Here are the tasks currently in your list: 
1.[T][ ] Visit Mt.Moon
2.[D][ ] Drink the juice in the fridge  (by: 19th February)
3.[E][ ] Festival at Celadon City  (from: Monday  to: Saturday)
--------------------------------------------------------------------------------
```

`mark 1`

Expected outcome:

The first task should be marked. This can be re-confirmed by calling `list` again.

```
--------------------------------------------------------------------------------
Psyduck has marked the task: [T][X] Visit Mt.Moon
--------------------------------------------------------------------------------
```
```
list
```
```
--------------------------------------------------------------------------------
Here are the tasks currently in your list: 
1.[T][X] Visit Mt.Moon
2.[D][ ] Drink the juice in the fridge  (by: 19th February)
3.[E][ ] Festival at Celadon City  (from: Monday  to: Saturday)
--------------------------------------------------------------------------------
```
### `unmark` - Unmarks a task as undone.

Unmarks a task as undone. Shows the details of the task that is unmarked.

Example of usage:

For the list that is shown below
```
--------------------------------------------------------------------------------
Here are the tasks currently in your list: 
1.[T][X] Visit Mt.Moon
2.[D][ ] Drink the juice in the fridge  (by: 19th February)
3.[E][ ] Festival at Celadon City  (from: Monday  to: Saturday)
--------------------------------------------------------------------------------
```

`unmark 1`

Expected outcome:

The first task should be unmarked. This can be re-confirmed by calling `list` again.

```
--------------------------------------------------------------------------------
Psyduck has unmarked the task: [T][ ] Visit Mt.Moon
--------------------------------------------------------------------------------
```
```
list
```
```
--------------------------------------------------------------------------------
Here are the tasks currently in your list: 
1.[T][ ] Visit Mt.Moon
2.[D][ ] Drink the juice in the fridge  (by: 19th February)
3.[E][ ] Festival at Celadon City  (from: Monday  to: Saturday)
--------------------------------------------------------------------------------
```
### `remove` / `delete` - Remove a selected task.

Removes a task in the to-do list.

Example of usage:

For the example list below:
```
--------------------------------------------------------------------------------
Here are the tasks currently in your list: 
1.[T][ ] Visit Mt.Moon
2.[D][ ] Drink the juice in the fridge  (by: 19th February)
3.[E][ ] Festival at Celadon City  (from: Monday  to: Saturday)
4.[T][ ] Battle at the Gym
--------------------------------------------------------------------------------
```

`remove 4`

Expected outcome:

The fourth task is removed. This can be re-confirmed by calling `list` again.

```
--------------------------------------------------------------------------------
Psyduck has removed the task: [T][ ] Battle at the Gym
--------------------------------------------------------------------------------
```
```
list
```
```
--------------------------------------------------------------------------------
Here are the tasks currently in your list: 
1.[T][ ] Visit Mt.Moon
2.[D][ ] Drink the juice in the fridge  (by: 19th February)
3.[E][ ] Festival at Celadon City  (from: Monday  to: Saturday)
--------------------------------------------------------------------------------
```

### `find` - Find all tasks related to a specific keyword.

Lists out all the tasks that are related to the keyword.

Example of usage:

For the given list below: 
```
--------------------------------------------------------------------------------
Here are the tasks currently in your list: 
1.[T][ ] Visit Mt.Moon
2.[D][ ] Drink the juice in the fridge  (by: 19th February)
3.[E][ ] Festival at Celadon City  (from: Monday  to: Saturday)
4.[T][ ] Drink bubble tea
5.[D][ ] drink 2l of water  (by: end of the day)
--------------------------------------------------------------------------------
```
`find Drink`

Expected outcome:

List out all the tasks related to `Drink`. Do note that the keyword is case-sensitive.

```
--------------------------------------------------------------------------------
Here are some matching tasks: 
1.[D][ ] Drink the juice in the fridge  (by: 19th February)
2.[T][ ] Drink bubble tea
3.[D][ ] Drink 2l of water  (by: end of the day)
--------------------------------------------------------------------------------
```

### `bye` / `exit` - Exits the program.

Exit out of the program.

Example of usage:

`bye`

Expected outcome:

The exit message will be shown below.

```
--------------------------------------------------------------------------------
Bye see you soon! :) 
--------------------------------------------------------------------------------
```