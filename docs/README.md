# User Guide

Duke is personal chatbot help the user to keep track task, such as todo, deadline, event

## Features 

### Add new Tasks: ```todo``` ```deadline``` ```event```

There are three task options:

1. ```todo```: is a task without any start date or end date.

##### Command

```
todo <description>
```

##### Usage 

```
todo clean room
	____________________________________________________________
	Got it. I've added this task:
	[T][ ] clean room
	Now you have 1 tasks in the list.
	____________________________________________________________
```

2. ```deadline```: is a task with a deadline (end date)

##### Command

```
deadline <description> /by <end datetime>
Notes: the format of datetime should be dd-MM-yyyy HH:mm
```

##### Usage 

```
deadline submit assignment /by 03-03-2023 23:59
	____________________________________________________________
	Got it. I've added this task:
	[D][ ] submit assignment (by: Mar 03 2023 11:59 PM)
	Now you have 2 tasks in the list.
	____________________________________________________________
```

3. ```event```: is a task with a start date and a end date

##### Command

```
event <description> /from <start datetime> /to <end datetime>
Notes: the format of datetime should be dd-MM-yyyy HH:mm
```

##### Usage 

```
event team project meeting /from 06-03-2023 19:00 /to 06-03-2023 21:00
	____________________________________________________________
	Got it. I've added this task:
	[E][ ] team project meeting (from: Mar 06 2023 07:00 PM to: Mar 06 2023 09:00 PM)
	Now you have 3 tasks in the list.
	____________________________________________________________
```

### List all tasks: ```list```

##### Command

```
list
```

##### Usage 

```
list
	____________________________________________________________
	Here are the tasks in your list
	1. [T][ ] buy errands
	2. [D][ ] submit assignment (by: Mar 03 2023 11:59 PM)
	3. [E][ ] team project meeting (from: Mar 06 2023 07:00 PM to: Mar 06 2023 09:00 PM)
	____________________________________________________________
```

### Find tasks: ```find```

This feature will show all task that contain <keyword> in the description of the task

##### Command

```
find <keyword>
```

##### Usage 

```
find team
	____________________________________________________________
	Here are the tasks in your list
	3. [E][ ] team project meeting (from: Mar 06 2023 07:00 PM to: Mar 06 2023 09:00 PM)
	4. [T][ ] create new team repo
	____________________________________________________________
```

### Find date: ```find-date```

This feature will show all task that contain <date> in the start date / end date of the task

##### Command

```
find-date <date>
Notes: the format of date should be dd-MM-yyyy
```

##### Usage 

```
find-date 03-03-2023
	____________________________________________________________
	Here are the tasks in your list
	1. [D][ ] submit assignment (by: Mar 03 2023 11:59 PM)
	____________________________________________________________
```

### Delete task: ```delete```

##### Command

```
delete <index_task>
Notes: index_task must be within the range of task list
```

##### Usage

```
list
	____________________________________________________________
	Here are the tasks in your list
	1. [T][ ] buy errands
	2. [D][ ] submit assignment (by: Mar 03 2023 11:59 PM)
	3. [E][ ] team project meeting (from: Mar 06 2023 07:00 PM to: Mar 06 2023 09:00 PM)
	4. [T][ ] create new team repo
	____________________________________________________________
delete 1
	____________________________________________________________
	Noted. I've removed this task:
	[T][ ] buy errands
	Now you have 3 tasks in the list.
	____________________________________________________________
```

### Mark task: ```mark```

##### Command

```
mark <index_task>
Notes: index_task must be within the range of task list
```

##### Usage

```
list
	____________________________________________________________
	Here are the tasks in your list
	1. [D][ ] submit assignment (by: Mar 03 2023 11:59 PM)
	2. [E][ ] team project meeting (from: Mar 06 2023 07:00 PM to: Mar 06 2023 09:00 PM)
	3. [T][ ] create new team repo
	____________________________________________________________
mark 2
	____________________________________________________________
	Nice! I've marked this task as done:
	[E][X] team project meeting (from: Mar 06 2023 07:00 PM to: Mar 06 2023 09:00 PM)
	____________________________________________________________
list
	____________________________________________________________
	Here are the tasks in your list
	1. [D][ ] submit assignment (by: Mar 03 2023 11:59 PM)
	2. [E][X] team project meeting (from: Mar 06 2023 07:00 PM to: Mar 06 2023 09:00 PM)
	3. [T][ ] create new team repo
	____________________________________________________________
```

### Unmark task: ```unmark```

##### Command

```
unmark <index_task>
Notes: index_task must be within the range of task list
```

##### Usage

```
list
	____________________________________________________________
	Here are the tasks in your list
	1. [D][X] submit assignment (by: Mar 03 2023 11:59 PM)
	2. [E][X] team project meeting (from: Mar 06 2023 07:00 PM to: Mar 06 2023 09:00 PM)
	3. [T][X] create new team repo
	____________________________________________________________
unmark 3
	____________________________________________________________
	OK, I've marked this task as not done yet:
	[T][ ] create new team repo
	____________________________________________________________
list
	____________________________________________________________
	Here are the tasks in your list
	1. [D][X] submit assignment (by: Mar 03 2023 11:59 PM)
	2. [E][X] team project meeting (from: Mar 06 2023 07:00 PM to: Mar 06 2023 09:00 PM)
	3. [T][ ] create new team repo
	____________________________________________________________
```

### Exit: ```bye```

##### Command

```
bye
```

##### Usage 

```
bye
    ____________________________________________________________
    Bye. Hope to see you again soon!
    ____________________________________________________________
```