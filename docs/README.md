```
 ____        _        
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
``` 

# User Guide

## About
Duke is a handy tool to help users to keep track of their busy schedules. Being a command line application,
Duke can be accessed directly from the terminal.

## Features 

### 1. Automatic loading of data from previous sessions
All changes made by the user will be saved in a local document. Each time the user starts the Duke Service, 
data is retrieved from this document. 

### 2. Adding, Finding Task
Users can add and search for various types of Task by
keying in the relevant fields.

### 3. Listing, Deleting, Marking and Un-marking Task
Users can obtain the index for a particular Task through the `$list KEYWORD` command
This index can then be used to delete, mark or un-mark a Task.


## Usage

### `ToDo` - Create a todo Task

Command: `$todo TASK_DESCRIPTION`

Expected output:
```
Got it. I've added this task:
  [T][ ] TASK_DESCRIPTION
Now you have 5 tasks in the list.
```


### `Deadline` - Create a Deadline Task

Command: `$deadline TASK_DESCRIPTION /by YYYY-MM-DD`

Expected output:
```
Got it. I've added this task:
  [D][ ] TASK_DESCRIPTION (by: MMM DD YYYY)
Now you have 6 tasks in the list.
```

### `Event` - Create an Event Task

Command: `$event TASK_DESCRIPTION /from START_TIME /to END_TIME`

Expected output:
```
Got it. I've added this task:
  [E][ ] TASK_DESCRIPTION (from: START_TIME to: END_TIME)
Now you have 7 tasks in the list.
```

### `Find` - Retrieve all task that contains the relevant keyword

Command: `$find KEYWORD`

Expected output:
```
Here are the matching tasks in your list:
1.[T][X] TASK_DESCRIPTION
```


### `List` - List all Task created thus far

Command: `$list`

Expected output:
```
Here are the tasks in your list:
1.[T][X] TASK_DESCRIPTION
2.[D][ ] TASK_DESCRIPTION (by: MMM DD YYYY)
3.[E][ ] TASK_DESCRIPTION (from: START_TIME to: END_TIME)
```

### `Delete` - Delete a particular Task (by index in the List command)

Command: `$delete 3`

Expected output:
```
Noted. I've removed this task:
   [E][ ] TASK_DESCRIPTION (from: START_TIME to: END_TIME)
Now you have 4 tasks in the list.
```

### `Mark` - Mark a particular Task (by index in the List command) as done

Command: `$mark 3`

Expected output:
```
Nice! I've marked this task as done:
  [T][X] TASK_DESCRIPTION
```

### `Un-mark` - Mark a particular Task (by index in the List command) as undone

Command: `$unmark 3`

Expected output:
```
OK, I've marked this task as not done yet:
  [T][ ] TASK_DESCRIPTION
```





