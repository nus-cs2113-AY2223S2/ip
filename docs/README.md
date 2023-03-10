# User Guide

## Features 

### Feature

#### Add Normal Task

User can add a normal task without specify the task type. 

Format:`add [description]`

#### Add Todo Task

User can add a todo task.

Format:`todo [description]`

#### Add Deadline Task

User can add a deadline task including a deadline time.

Format: `deadline [description] /by [time]`

#### Add Event Task

User can add an event task including an interval.

Format: `event [description] /from [time] /to [time]`

#### List Tasks

User can list all the tasks in the list.

Format: `list`

#### Mark Tasks

User can mark a specified object according to index.

Format: `mark [index]`

#### Unmark Tasks

User can unmark a specified object according to index.

Format: `unmark [index]`

#### Find Tasks

User can find the tasks according to the keyword.

Format: `find [keyword]`

#### Load and Save

User can load and save the tasks in the disk.



## Usage

### `add` - add a normal task

Add a normal task to the list.

Example of usage: 

`add meeting`

Expected outcome:

A normal task is added to the list.

```
------------------------------------------------------------
Got it. I've added this task:
[ ] meeting
Now you have 1 tasks in the list
------------------------------------------------------------
```



### `todo` - add a todo task

Add a todo task to the list.

Example of usage: 

`todo homework`

Expected outcome:

A todo task is added to the list.

```
------------------------------------------------------------
Got it. I've added this task:
[T][ ] homework
Now you have 2 tasks in the list
------------------------------------------------------------
```



### `deadline` - add a deadline task

Add a deadline task to the list. Date format YYYY-MM-DD is encouraged.

Example of usage: 

`deadline submit final report /by 2023-04-01`

Expected outcome:

A deadline task is added to the list. 

```
------------------------------------------------------------
Got it. I've added this task:
[D][ ] submit final report (Date: Apr 1 2023)
Now you have 3 tasks in the list
------------------------------------------------------------
```



### `event` - add an event task

Add an event task to the list. Date format YYYY-MM-DD is encouraged.

Example of usage: 

`event CS4221 project /from 2023-03-01 /to 2023-03-20`

Expected outcome:

An event task is added to the list.

```
------------------------------------------------------------
Got it. I've added this task:
[E][ ] CS4221 project(Date: Mar 1 2023 ~ Mar 20 2023)
Now you have 4 tasks in the list
------------------------------------------------------------
```



### `list` - list the task list

Show all the tasks in the list.

Example of usage: 

`list`

Expected outcome:

All tasks' information is listed.

```
------------------------------------------------------------
1.[T][ ] homework
2.[D][ ] submit final report(Date: Apr 1 2023)
3.[E][ ] CS4221 project(Date: Mar 1 2023 ~ Mar 20 2023)
------------------------------------------------------------
```



### `mark` - mark a task

Mark a specified object according to index.

Example of usage: 

`mark 1`

Expected outcome:

Task 1 is marked with [X].

```
------------------------------------------------------------
Nice! I've marked this task as done:
[T][X] homework
------------------------------------------------------------
```



### `unmark` - unmark a task

Unmark a specified object according to index.

Example of usage: 

`unmark 1`

Expected outcome:

Task 1 is unmarked. 

```
------------------------------------------------------------
OK, I've marked this task as not done yet:
[T][ ] homework
------------------------------------------------------------
```



### `delete` - delete a task

Delete a specified object according to index.

Example of usage: 

`delete 1`

Expected outcome:

Task 1 is deleted.

```
------------------------------------------------------------
Noted. I've removed this task:
[T][ ] homework
Now you have 3 tasks in the list.
------------------------------------------------------------
```



### `find` - find the tasks

Find the tasks according to the given keyword. Return a list of tasks containing the keyword.

Example of usage: 

`find meeting`

Expected outcome:

The tasks containing 'meeting' is shown.

```
------------------------------------------------------------
Here are the matching tasks in your list:
1.[ ] meeting
2.[T][ ] attend a lab meeting
3.[D][ ] write a report after the meeting (Date: 3月 5 2023)
------------------------------------------------------------
```



### `bye` - end program

Send 'bye' and end the program.

Example of usage: 

`bye`

Expected outcome:

The program exited.

```
------------------------------------------------------------
See you again soon!
------------------------------------------------------------
```



### `help` - get a description of all the commands

Send 'help' to get a description of all the commands.

Example of usage: 

`help`

Expected outcome:

The program exited.

```
------------------------------------------------------------
For help, here is a description for all the commands:
> list
List all the tasks in the list.

> todo [description]
Add a todo task to the list.

...

> bye
End the program.
------------------------------------------------------------
```

