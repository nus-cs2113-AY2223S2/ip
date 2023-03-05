# User Guide

Jarvis is a **Command Line Application for managing tasks, optimised for use via the command-line interface (CLI).**

## Jarvis's Features 

### Feature - add task

Allows user to add 3 different types of tasks:
1. Todo
2. Deadline
3. Event

### Feature - mark/unmark

Allows the user to mark the task as done/not done. 

### Feature - delete

Allows the user to delete a task from their list of tasks.

### Feature - list

Prints the user's entire list of tasks.

### Feature - find

Finds matching tasks within the user's task list according to the keyword specified by the user.

### Feature - bye

Terminates the current program and saves the user's current task list.

## Usage

#### Take Note:
- All commands are case-sensitive, eg. 'list' is not the same as 'List'. Enter all commands as lower-case letters.


### `todo` - Adds tasks of type 'todo'.

Explanation: A todo is a task that contains a description with no time-related attribute associated with it.

Example of usage: 
`todo DESCRIPTION`

Expected outcome:

```
todo read book
______________________________
Got it. I've added this task: 
[T][ ] read book
Now you have 7 task(s) in the list.
______________________________
```

### `deadline` - Adds tasks of type 'deadline'.

Explanation: A deadline is a task that contains a description as well as a deadline for that particular task.

Example of usage:
`deadline DESCRIPTION /by DEADLINE`

Expected outcome:

```
deadline return book /by Sunday
______________________________
Got it. I've added this task: 
[D][ ] return book (by: Sunday)
Now you have 8 task(s) in the list.
______________________________
```

### `event` - Adds tasks of type 'event'.

Explanation: A event is a task that contains a description as well as a start and end time for that particular task.

Example of usage:
`deadline DESCRIPTION /from EVENT_START /to EVENT_END`

Expected outcome:

```
event project meeting /from Monday 2pm /to 4pm
______________________________
Got it. I've added this task: 
[E][ ] project meeting (from: Monday 2pm to 4pm)
Now you have 9 task(s) in the list.
______________________________
```

### `mark` - Marks a task in your task-list as complete.

Explanation: Marking a task indicates that a task has been completed by the user.

Example of usage:
`mark TASK_NUMBER`

Expected outcome:
```
mark 1
Nice! I've marked this task as done:
______________________________
[D][X] finish cs2113 assignment (by: 2359)
______________________________
```

### `unmark` - Marks a task in your task-list as incomplete.

Explanation: Unmarking a task indicates that a task has not been completed by the user.

Example of usage:
`unmark TASK_NUMBER`

Expected outcome:
```
unmark 1
OK, I've marked this task as not done:
______________________________
[D][ ] finish cs2113 assignment (by: 2359)
______________________________
```

### `delete` - Deletes a task from the user's task-list.

Example of usage:
`delete TASK_NUMBER`

Expected outcome:
```
delete 4
______________________________
Noted. I've removed this task: 
[E][ ] project meeting (from: Monday 2pm to 4pm)
Now you have 3 tasks in the list.
______________________________
```

### `list` - Outputs the user's entire task-list.

Example of usage:
`list`

Expected outcome:
```
list
______________________________
Here are the tasks in your list:
1.[D][ ] finish cs2113 assignment (by: 2359)
2.[T][ ] read book
3.[D][ ] return book (by: Sunday)
______________________________
```

### `find` - Outputs all tasks containing the keyword which the user specifies.

Example of usage:
`find KEYWORD`

Expected outcome:
```
______________________________
Here are the tasks in your list:
1.[D][ ] finish cs2113 assignment (by: 2359)
2.[T][ ] read book
3.[D][ ] return book (by: Sunday)
______________________________
find book
______________________________
1.[T][ ] read book
2.[D][ ] return book (by: Sunday)
______________________________
```

### `bye` - Terminates the current instance of Jarvis.

Example of usage:
`bye`

Expected outcome:
```
bye
______________________________
Bye. Hope to see you again soon!
______________________________
```

## Saving your list of tasks
Your data is saved each time you terminate the program. There is no need to save manually. :D

## Data Saving format
Your data is saved within a txt file called duke.txt. If this file is not present, it will be created once the current instance of Jarvis terminates. The data is saved in the following format:
  
For Todos - [TODO_TASK_TYPE]:[COMPLETION]:[DESCRIPTION]
  
For Deadlines - [DEADLINE_TASK_TYPE]:[COMPLETION]:[DESCRIPTION] /by [DEADLINE]
  
For Events - [EVENT_TASK_TYPE]:[COMPLETION]:[DESCRIPTION] /from [EVENT_START] /to [EVENT_END]
  
where TASK_TYPE is denoted by 'T', 'D' or 'E' and COMPLETION is denoted as 0 for not marked and 1 for marked.

```
D:0: finish cs2113 assignment /by 2359
T:0: read book
D:0: return book /by Sunday
E:0: go to the gym /from Sunday 1pm /to 3pm
```