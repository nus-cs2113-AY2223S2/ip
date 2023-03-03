# User Guide

## Features

### User Friendliness

Offers a help command that tells you all possible commands you can perform on Dude.

### Task List Storing

Keeps track of tasks you give it in a list in chronological order, following the order you input them.
And also **automatically** saves the list whenever changes are made

### Task Completion Tracking

Able to keep track of tasks which are **completed** and allows you to **mark** them as done or not done.

### Date Format Storing

Able to store a deadline or event dates which are related to the task.

### Task Searching with Keywords

Finds all tasks containing **keywords** that you specify to Dude.

### Task Removal

Allows you to delete certain tasks which you no longer need to keep track of.

### Display Task List

Shows you all tasks currently present in your list.

## Usage

### `help` - Displays all possible commands for Dude

**Example of usage:** 

`help`

**Expected outcome:**

```
NO WORRIES! Help is here! You may find the list of commands below useful. 
 
 
COMMANDS LIST: 
 
 
## List ## 
Description : Displays the current tasks queued in the list. 
Format      : "list" 
Example use : "list" 
 
## Todos ## 
Description : Adds a new task without any time element in the task list. 
Format      : "todo <insert todo task description>"
Example use : "todo eat dinner" 
 
## Deadlines ## 
Description : Adds a new task with ONE time element indicating the due date in the task list. 
Format      : "deadline <insert deadline task description> /by <insert date in "YYYY-MM-DD" format> " 
Example use : "deadline CS2040C Kattis Week 6 /by 2023-03-02" 
 
## Events ## 
Description : Adds a new task with TWO time elements indicating the starting date/time and the ending date/time. 
Format      : "event <insert event description> /from <insert date in "YYYY-MM-DD" format> /to <insert date in "YYYY-MM-DD" format>" 
Example use : "event Recess Week /from 2023-03-02 /to 2023-03-05" 
 
## Find ## 
Description : Finds all tasks containing keywords inputted in the description. Requires keywords as an input. 
Format      : "find <insert keywords>" 
Example use : "find books" 
 
## Delete ## 
Description : Removes a task from the task list, if it exists. Requires the task index on the list as an input. 
Format      : "delete <insert task index number>" 
Example use : "delete 10" 
 
## Mark ## 
Description : Crosses out tasks which are done, requires the task index on the list as an input. 
Format      : "mark <insert task index number>" 
Example use : "mark 10" 
 
## Unmark ## 
Description : Undo the cross of any tasks which are done, requires the task index on the list as an input. 
Format      : "unmark <insert task index number>" 
Example use : "unmark 10" 
 
## Bye ## 
Description : Ends the programme. See you next time! 
Format      : "bye" 
Example use : "bye" 
 
 
Hope this list has been informational to you! 
```

<p></p>

### `list` - List all tasks

**Example of usage:**

`list`

**Expected outcome:**

```
TASKS LIST

1. [T][ ] CS2113 tp 
2. [D][X] EE2026 Graded Lab 3 (by: Mar 2 2023)
3. [E][ ] Orientation (from: Feb 5 2023 to: Feb 10 2023)
```

### `todo` - Adds a basic Todo task to the list

Todo tasks do not have any time element.

**Example of usage:**

`todo <description>`

**Expected outcome:**

Adds Todo task to the list, increasing list size and is saved in the local storage.

```
Got it. I've added this task:
  [T][ ] <description> 
Now you have <size> tasks in the list.
```

### `deadline` - Adds a Deadline task to the list

Deadline tasks have a time element taken with `/by` keyword that refers to the date.
Dates are expected to be in `YYYY-MM-DD` format.

**Example of usage:**

`deadline <description> /by 2023-04-18`

**Expected outcome:**

Adds Deadline task to the list, increasing list size and is saved in the local storage.

```
Got it. I've added this task:
  [D][ ] <description> (by: Apr 18 2023)
Now you have <size> tasks in the list.
```

### `event` - Adds an Event task to the list

Event tasks have two time elements taken with `/from` and `/to` keywords that refers to the dates for the period of the task.
<p>
Dates are expected to be in `YYYY-MM-DD` format.

**Example of usage:**

`event <description> /from 2023-04-18 /to 2023-05-30`

**Expected outcome:**

Adds Event task to the list, increasing list size and is saved in the local storage.

```
Got it. I've added this task:
  [E][ ] <description> (from: Apr 18 2023 to: May 30 2023)
Now you have <size> tasks in the list.
```

### `find` - Searches tasks by keyword(s)

Shows all tasks with descriptions containing the keyword(s) specified.

**Example of usage:**

`find <keyword(s)>`

For the example we use the keyword "Orientation".

**Expected outcome:**

```
Here are the matching tasks in your list:

1. [E][X] Orientation (from: Feb 5 2023 to: Feb 10 2023)
2. [T][ ] Orientation2 
```

### `Delete` - Removes a task from the list

The index of the task to remove must be positive and must exist in the list.

**Example of usage:**

`delete <index>`

**Expected outcome:**

```
Noted. I've removed this task:
  [D][ ] EE2026 Graded Lab 3 (by: Mar 2 2023)
Now you have <size> tasks in the list.
```

### `mark` - Mark a task as completed

The index of the task to remove must be positive and must exist in the list.

**Example of usage:**

`mark <index>`

**Expected outcome:**

```
Nice! I've marked this task as done:
  [T][X] CS2113 tp 
```

### `unmark` - Mark a task as not completed

The index of the task to remove must be positive and must exist in the list.

**Example of usage:**

`mark <index>`

**Expected outcome:**

```
OK, I've marked this task as not done yet:
  [T][ ] CS2113 tp 
```

### `bye` - Exits the program

**Example of usage:**

`bye`

**Expected outcome:**

```
Bye. Hope to see you again soon!
```