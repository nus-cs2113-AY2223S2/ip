# User Guide

## Features 

### Record tasks

User will be able to add and delete a ToDo, Deadline or Event<p>
User will also be able to mark or unmark the task as done<p>
User will also be able to search recorded tasks using a keyword<p>
User can list all recorded tasks<p>

### Save/Load tasks

User will be able to save and load tasks form a text file, taskData.txt

## Usage

### `list` - List all recorded tasks

Prints an indexed list of all recorded tasks in the order entered,
including description, type and status of each task. <p>
Tasks are printed in on a single line, in the form of:<p>
    index, type of task, status of task, description<p>

Example of usage: 

`list`

Expected outcome:

A list of an unmarked ToDo task 'qwertyui', a marked ToDo task 'get lunch'
and an unmarked Deadline task 'assignment' by 'tomorrow 2359'

```
list
____________________________________________________________

Here are the tasks in your list:

1. [T] [ ] qwertyui
2. [T] [X] get lunch
3. [D] [ ] submit assignment (by: tomorrow 2359)
____________________________________________________________
```

### `mark` - Marks a recorded task

Marks the recorded task, specified using its index, as done.<p>
Marked tasks have their status displayed as 'X'.

Example of usage: 

`mark 1`

Expected outcome:

Mark the first task on the list, an unmarked ToDo task 'qwertyui', as done.

```
mark 1
____________________________________________________________

Nice! I've marked this task as done:
  [T] [X] qwertyui
____________________________________________________________
```

### `unmark` - Unmarks a recorded task

Unmarks the recorded task, specified using its index.<p>
Unmarked tasks have their status displayed as ' '.

Example of usage: 

`unmark 2`

Expected outcome:

Unmark the second task on the list, an marked ToDo task 'get lunch'.

```
unmark 2
____________________________________________________________

Nice! I've unmarked this task as done:
  [T] [ ] get lunch
____________________________________________________________
```

### `add` - Adds a task

Adds a named ToDo, Deadline or Event task and sets its default status to unmarked.<p>
A deadline task records an additional 'by' field, and an event task records additional 'from' and 'to' fields.<p>
Additional fields use the delimiters `/by`, `/from` and `/to` respectively.

Examples of usage: 

adding a todo task: `todo get lunch`<p>
adding a deadline task: `deadline submit assignment /by tomorrow 2359`<p>
adding an event task: `event capstone /from 31/3 /to 24/9`


Expected outcome:

Add an event task named 'capstone' from '31/3' to '24/9'.

```
event capstone /from 31/3 /to 24/9
____________________________________________________________

Got it. I've added this task:
  [E] [ ] capstone  (from:  31/3  to:  24/9)
Now you have 4 tasks in the list.
____________________________________________________________
```

### `delete` - Remove a recorded task

Removes a recorded task, specified using its index. Proceeding tasks in the list will have their index updated. 

Example of usage: 

`delete 1`

Expected outcome:

Removes the recorded task named qwertyui, indexed at 1. 

```
delete 1
____________________________________________________________

Noted. I've removed this task.
____________________________________________________________
```

### `find` - Search for a keyword

Searches through all recorded task, and prints the task if its name matches the keyword. If no such tasks exits, prints `There are no such tasks in the list.`

Example of usage: 

`find assign`

Expected outcome:

Tasks with name containing the keyword 'assign', with its corresponding index. 

```
find assign
____________________________________________________________

Here are the matching tasks in your list:

2. [D] [ ] submit assignment (by: tomorrow 2359)
____________________________________________________________
```

### `bye` - Ends the program

Saves the current recorded list of tasks to taskData.txt, to be saved locally, and exits the program. 

Example of usage: 

`bye`

Expected outcome:

One of three exit messages. 

```
bye
____________________________________________________________

Bye. Hope to see you again soon!

____________________________________________________________
```
