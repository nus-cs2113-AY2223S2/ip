# User Guide

Duke is a helpful command line bot to keep track of your tasks and events!

## Features 

### Feature - Add Tasks

You are able to add 3 types of tasks for Duke to record. These are:
<ul>
  <li> To-do (shown as [T] icon) </li>
  <li> Deadline (shown as [D] icon) </li>
  <li> Event (shown as [E] icon) </li>
</ul>

### Feature - Delete Tasks

You are able to remove tasks that you no longer want to keep track of.

### Feature - List Tasks

You are able to list the current tasks that are in Duke's record.

### Feature - Mark Tasks

You are able to mark existing tasks that are in Duke's record as complete/incomplete.

### Feature - Find Tasks

You are able to search for tasks that are in Duke's record.

### Feature - Local Storage

All tasks that are added to Duke will be automatically saved onto a document on your local device. The data will be read whenever you reboot Duke again.

## Usage

### `todo` - Add a to-do task

Adds a to-do task to Duke's record.

Format: `todo <description>`

Example of usage: `todo CS2113 Week 7 Coursemology`

Expected outcome:

The to-do task of "CS2113 Week 7 Coursemology" will be recorded to Duke's memory.

```
____________________________________________________________
Got it. I've added this task:
[T][ ] CS2113 Week 7 Coursemology
Now you have 1 tasks in the list.
____________________________________________________________
```

***

### `deadline` - Add a deadline task

Adds a deadline task to Duke's record.
Please note that deadline must be in **_dd-mm-yyyy hh:mm_** format.

Format: `deadline <description> /by <deadline>`


Example of usage: `deadline CS2113 IP /by 03-03-2022 23:59`

Expected outcome:

The deadline task of "CS2113 IP" with a deadline of "03-03-2022 23:59" will be recorded to Duke's memory.

```
____________________________________________________________
Got it. I've added this task:
[D][ ] CS2113 IP (by: Mar 03 2022 23:59)
Now you have 2 tasks in the list.
____________________________________________________________
```

***

### `event` - Add a event task

Adds a event task to Duke's record.
Please note that start and end dates must be in **_dd-mm-yyyy hh:mm_** format.

Format: `event <description> /from <start> /to <end>`

Example of usage: `event CG2023 Midterms /from 02-03-2023 16:00 /to 02-03-2023 18:00`

Expected outcome:

The event task of "CG2023 Midterms" with a start time of "02-03-2023 16:00" and end time of "02-03-2023 18:00" will be recorded to Duke's memory.

```
____________________________________________________________
Got it. I've added this task:
[E][ ] CG2023 Midterms (from: Mar 02 2023 16:00 to: Mar 02 2023 18:00)
Now you have 3 tasks in the list.
____________________________________________________________
```


***

### `list` - List tasks.

Lists all tasks in Duke's record.

Format: `list`

Example of usage: `list`

Expected outcome:

All tasks in Duke's memory will be listed.

```
____________________________________________________________
Here are the tasks in your list:
1. [T][ ] CS2113 Week 7 Coursemology
---------------------------------------------
2. [D][ ] CS2113 IP (by: Mar 03 2023 23:59)
---------------------------------------------
3. [E][ ] CG2023 Midterms (from: Mar 02 2023 16:00 to: Mar 02 2023 18:00)
____________________________________________________________
```

***

### `mark` - Mark a task

Marks a task in Duke's record as completed.

Format: `mark <task number>`

Example of usage: `mark 2`

Expected outcome:

The second task in Duke's memory will be marked as completed.

```
____________________________________________________________
Nice! I've marked this task as done:
[X] CS2113 IP (by: Mar 03 2023 23:59)
____________________________________________________________
```

***

### `unmark` - Unmark a task

Unmarks a task in Duke's record.

Format: `unmark <task number>`

Example of usage: `unmark 2`

Expected outcome:

The second task in Duke's memory will be marked as incomplete.

```
____________________________________________________________
Ok, I've marked this task as not done yet:
[ ] CS2113 IP (by: Mar 03 2023 23:59)
____________________________________________________________
```

***

### `delete` - Deletes a task

Deletes a task from Duke's record.

Format: `delete <task number>`

Example of usage: `delete 2`

Expected outcome:

The second task in Duke's memory will be deleted.

```
____________________________________________________________
Got it. I've removed this task:
[D][ ] CS2113 IP (by: Mar 03 2023 23:59)
Now you have 2 tasks in the list.
____________________________________________________________
```

***

### `find` - Search for a task

Searches for tasks matching description in Duke's record.

Format: `find <description>`

Example of usage: `find Midterms`

Expected outcome:

Tasks with descriptions containing "Midterms" in Duke's memory will be displayed.

```
____________________________________________________________
Here are the matching tasks in your list:
1. [E][ ] CG2023 Midterms (from: Mar 02 2023 16:00 to: Mar 02 2023 18:00)
____________________________________________________________
```

***

### `bye` - Exit program

CLoses and exits Duke program.

Format: `bye`

Example of usage: `bye`

Expected outcome:

Duke will close and exit.

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
