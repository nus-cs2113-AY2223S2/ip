# User Guide

Duke keeps track of the tasks that you have in your daily
lives by serving as a to-do list with various functions that can
cater to different tasks with different requirements.

## Features 

### Accepts various types of user inputs

Duke accepts 3 main types of user tasks that caters to the user's 
needs for that task, namely (1.) to-do, (2.) deadline and (3.) event.

To-do only requires a description of the task, while the deadline requires
an additional input in the form of the task deadline. Event requires
two additional inputs, the start and the end timing of the event.

### List of inputs are accessible the next time Duke is called

The previous inputs stored by the user can be reaccessed, and thus the tasks
would not disappear after Duke is shut down.

## Usage

### `todo` - Adds a todo task into the list

todo TASK_DESCRIPTION

Example of usage: 

`todo ice-skating`

Expected outcome:

Adds a todo task with the label T to indicate todo and an empty [ ]
which indicates the status.

```
Got it. I've added this task: 
[T][ ] ice-skating
Now you have 1 tasks in the list.
```

### `deadline` - Adds a deadline task into the list

deadline TASK_DESCRIPTION /by DEADLINE

Example of usage:

`deadline record ice-skating experience /by 21 Feb`

Expected outcome:

Adds a deadline task with the label D to indicate a deadline task.
There is also a [ ] which indicates the status of the task.
```
Got it. I've added this task: 
[D][ ] record ice-skating experience (by: 21 Feb)
Now you have 2 tasks in the list.
```
### `event` - Adds an event into the list

event TASK_DESCRIPTION /from START DATE /to END DATE.
The start and end date indicates the duration of the event.

Example of usage:

`event school film festival /from 1 Mar 0900H /to 2 Mar 1700H`

Expected outcome:

Adds an event into the list with an E to indicate event,
[ ] indicates the status of the event.

```
Got it. I've added this task: 
[E][ ] school film festival (from: 1 Mar 0900H to: 2 Mar 1700H)
Now you have 3 tasks in the list.
```

### `list` - Shows the items in the list

list

Example of usage:

`list`

Expected outcome:

Shows all the existing items in the list. In this test case,
the 3 tasks added prior to calling the command list will be shown.

```
Here are the tasks in your list:
1.[T][ ] ice-skating
2.[D][ ] record ice-skating experience (by: 21 Feb)
3.[E][ ] school film festival (from: 1 Mar 0900H to: 2 Mar 1700H)
```
### `mark` - Marks an item of the user's choice

mark POS_OF_TASK

Example of usage:

`mark 2`

Expected outcome:

This marks the second item in the list as complete, which will replace
the empty space within the status indicator [ ] with a X.
Task can be marked again even if it was previously marked.
```
Nice! I've marked this task as done: 
[D][X] record ice-skating experience (by: 21 Feb)
```
### `unmark` - Unmark an item of the user's choice

unmark POS_OF_TASK

Example of usage:

`unmark 2`

Expected outcome:

This unmarks the second item in the list as complete, which will replace
the X within the status indicator [ ] with an empty space.
Task can be unmarked again even if it was previously unmarked.
```
OK, I've marked this task as not done yet: 
[D][ ] record ice-skating experience (by: 21 Feb)
```
### `find` - Find tasks that contains the keyword

find KEYWORD

Example of usage:

`find ice-skating`

Expected outcome:

A list showing all the tasks with the keyword will be returned.
Do note that the position of task does not reflect the actual location
of the task in the list. The `list` command has to be called again to find
the exact position of the task. 
```
Here are the matches in your list!
1.[T][ ] ice-skating
2.[D][ ] record ice-skating experience (by: 21 Feb)
```
### `delete` - Delete an item of the user's choice

delete POS_OF_TASK

Example of usage:

`delete 3`

Expected outcome:

This deletes the third item in the list.
```
Noted. I've removed this task: 
[E][ ] school film festival (from: 1 Mar 0900H to: 2 Mar 1700H)
Now you have 2 tasks in the list. 
```
If the position of task is outside of the list size, a reminder will be 
sent to the user that the value is not in the list.
```
Item to delete is not in the list!
```
### `bye` - Shuts down Duke

bye

Example of usage:

`bye`

Expected outcome:

The program ends and Duke is shut down.
```
Bye. Hope to see you again soon!
```