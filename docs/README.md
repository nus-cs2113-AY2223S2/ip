# User Guide

## Features 

### List

List all tasks in the task list.

### Mark & Unmark

Mark or unmark a task at given index as done.

### ToDos, Events & Deadlines

Add tasks with types of ToDos, Events and Deadlines.

### Find

Find tasks with a given keyword.

### Delete

Delete a task at given index.

### Save

Save the task list in a txt file.


## Usage

### `list`

List all tasks in the task list.

Example of usage: 

`list`

Expected outcome:

List the tasks in the task list

```
[T][ ] homework 1
[D][ ] homework 1 (by: Mar 05 2023 1259)
[E][ ] meeting (from: Mar 12 2023 1300 to: Mar 12 2023 1345)
```
### `todo`

Add a todo task.

Example of usage:

`todo homework 1`

Expected outcome:

Add todo homework 1 to the task list.

```
Got it. I've added this task:
[T][ ] homework 1
Now you have 1 tasks in the list.
```
### `deadline`

Add a deadline task.

Example of usage:

`deadline homework 1 /by 05/03/2023 1259`

Expected outcome:

Add deadline homework 1 /by 05/03/2023 1259, reformatting the time into MMM dd yyyy HHmm.

```
Got it. I've added this task:
[D][ ] homework 1 (by: Mar 05 2023 1259)
Now you have 2 tasks in the list.
```
### `event`

Add an event task.

Example of usage:

`event meeting /from 12/03/2023 1300 /to 12/03/2023 1345`

Expected outcome:

Add event meeting /from 12/03/2023 1300 /to 12/03/2023 1345, reformatting the time into MMM dd yyyy HHmm.

```
Got it. I've added this task:
[E][ ] meeting (from: Mar 12 2023 1300 to: Mar 12 2023 1345)
Now you have 3 tasks in the list.
```
### `delete`

Delete a task at given index from the task list.

Example of usage:

`delete 1`

Expected outcome:

The task at index 1 is deleted.

```
Noted. I've removed this task:
[T][ ] homework 1
Now you have 2 tasks in the list.
```
### `mark`

Mark a task at given index in the task list.

Example of usage:

`mark 1`

Expected outcome:

The task at index 1 is marked as done.

```
Nice! I've marked this task as done:
[X] homework 1
```
### `unmark`

Unmark a task at given index in the task list.

Example of usage:

`unmark 1`

Expected outcome:

The task at index 1 is unmarked as done.

```
OK, I've marked this task as not done yet:
[T][ ] homework 1
```
### `find`

Find the task with a given keyword.

Example of usage:

`find 1`

Expected outcome:

The task with the given keyword "1".

```
Here are the matching tasks in your list:
1. [T][ ] homework 1
```
