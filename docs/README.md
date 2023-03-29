# User Guide

## Features 

### Stores your tasks

Duke stores your tasks so that you don't have to.

### Mark tasks as done

Duke can remember which tasks you have done.

### Delete tasks

Duke can remove tasks you do not want to do anymore.

### Find tasks

Duke can help you find stored tasks with a keyword search.

### Save your tasks

Duke will save your tasks for future use.

### List out all the tasks

Duke can list out all the tasks you have stored.

## Usage

### `todo` - Adds a to-do task to your list.

Adds a to-do task to your list.

Example of usage: 

`todo do homework`

Expected outcome:

`Got it. I've added this task.`

`[T][ ] do homework`

`Now you have 1 task(s) in the list.`

Duke adds todo task into the list.

### `deadline` - Adds a deadline task to your list.

Adds a deadline task to your list. A deadline task has a date by which you should finish the task.

Example of usage:

`deadline submit report /by 4pm 12 March`

Expected outcome:

`Got it. I've added this task.`

`[D][ ] submit report (by:  4pm 12 March)`

`Now you have 2 task(s) in the list.`

Duke adds deadline task into the list.

### `event` - Adds an event task to your list.

Adds an event task to your list. An event task has a start and end date.

Example of usage:

`event walk dog /from 12pm /to 3pm 3 March`

Expected outcome:

`Got it. I've added this task.`

`[E][ ] walk dog (from:  12pm  to:  3pm 3 March)`

`Now you have 3 task(s) in the list.`

Duke adds event task into the list.

### `delete` - removes a task from your list.

Removes a task from your list based on index number on list. 

Example of usage:

`delete 2`

Expected outcome:

`Noted. I've removed this task:`

`[D][ ] submit report `

Duke removes task from the list.

### `mark` - marks a task from your list as done.

Marks a task as from your list as done based on index number on list.

Example of usage:

`mark 2`

Expected outcome:

`Nice! I've marked this task as done.`

`[E][X] walk dog `

Duke puts an 'X' on the second bracket of the task from the list.

### `unmark` - un-marks a task from your list as if not done.

Un-marks a task as from your list as if it is not done based on index number on list.

Example of usage:

`unmark 2`

Expected outcome:

`OK, I've marked this task as not done yet.`

`[E][ ] walk dog `

Duke removes the 'X' on the second bracket of the task from the list.

### `list` - list out all tasks in your list.

Lists out all tasks in your list.

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list:`

`1. [T][ ]do homework `

`2. [E][ ]walk dog /start, end:  12pm , 3pm 3 March`

Duke displays all items in list.

### `find` - find all tasks in your list that contains a keyword.

Finds all tasks in your list that contains a matching keyword.

Example of usage:

`find do`

Expected outcome:

`Here are the matching tasks in your list: `

`1. [T][ ]do homework `

`2. [E][ ]walk dog /start, end:  12pm , 3pm 3 March`

Duke displays all items that contains keyword 'do'. 'do' in 'dog' and 'do' in 'do'.

### `bye` - Exits the program

Exits the program

Example of usage:

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`

Duke exits, saving your task list into a save file.