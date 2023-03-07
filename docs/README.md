# User Guide

## Vivy bot

Vivy is a Command Line Interface (CLI) program that is based off the Duke bot. It allows users to record down different types of tasks, keeping track of what to do and what has or has not been done.

## Features

### Adding tasks: `add`

Input according to the following formats for recording down the different types of tasks.

Format for tasks:
```
   Deadlines: <description> /by <deadline>
   	      (eg. Eat bread /by Thursday)
	      
      Events: <description> /from <start date/time> /to <end date/time>
      	      (eg. Meeting /from March 3 8pm /to 9pm)
	      
	Todo: <description>	
	      (eg. Water the plants)
```

<br />

### Viewing list of tasks: `list`
Displays all the current tasks in your list in the terminal.

Input: `list`

Output:
```
____________________________________________________________
 Here are the tasks in your list:
 1. TASK 1
 2. TASK 2
____________________________________________________________
```

<br />

### Marking a task as done: `mark`
Marks a specified task in your list as done.

Input: `mark TASK_NUMBER`

Output:
```
____________________________________________________________
 Understood. I've marked this task as done:
 [T][X] TASK DESCRIPTION 
____________________________________________________________
```

<br />

### Marking a task as not done: `unmark`
Marks a specified task in your list as not done.

Input: `unmark TASK_NUMBER`

Output:
```
____________________________________________________________
 Understood. I've marked this task as not done yet:
 [T][ ] TASK DESCRIPTION
____________________________________________________________
```

<br />

### Deleting a task: `delete`
Deletes a task from your list.

Input: `delete TASK_NUMBER`

Output:
```
____________________________________________________________
 Understood. I have removed this task:
	[T][ ] TASK DESCRIPTION
 You now have NUMBER_OF_TASKS tasks in your list.
____________________________________________________________
```

<br />

### Finding tasks containing a keyword: `find`
Finds the tasks in your list that contain a specified keyword.

Input: `find KEYWORD`

Output:
```
____________________________________________________________
 Here are the matching tasks in your list:
 1.[T][ ] TASK DESCRIPTION
 2.[T][ ] TASK DESCRIPTION 
____________________________________________________________
```

<br />

### Closing the program: `bye`
Shuts down the Vivy bot.

Input: `bye`

Output:
```
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________

```
