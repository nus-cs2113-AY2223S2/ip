# Elzi Your Personal Pet Dog User Guide

Assisting you to keep track of your tasks through our CLI command

## Quick Start
1. Download Java 11 or above on your computer.
2. Download `ip.jar`.
3. Navigate to the path of `ip.jar`, right click, open in terminal
4. Run `java -jar ip.jar`
5. You should see  ` Hello! I'm Elzi, your personal pet dog!
   What is my task, master?` greeeting you
## Features

### Adding Todo task: `todo`
Adds Todo to the task list.

Format: `todo DESCRIPTION`

Example:
`todo Do My Reading`

Expected output:
```
Got it. I've added this task:
	[T][ ] Do My Reading
Now you have 1 task in the list.
```

### Adding Deadline task: `deadline`
Adds Deadline to the task list.

Format: `deadline DESCRIPTION /by TIME`

Example:
`deadline Do homework /by tomorrow`

Expected output:
```
Got it. I've added this task:
	[D][ ] Do homework (by: tomorrow)
Now you have 2 tasks in the list.
```

### Adding a new Event task: `event`
Adds Event to the task list.

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

Example:
`event Beach Day /from Sat 9am /to Sat 3pm`

Expected output:
```
Got it. I've added this task:
	[E][ ] Beach Day (from: Sat 9am  to: Sat 3pm)
Now you have 3 tasks in the list.
```

### Listing all tasks: `list`
Lists all of the tasks.

Expected output:
```
Your current tasks are as follows:
1. [T][ ] Do My Reading
2. [D][ ] Do homework (by: tomorrow)
3. [E][ ] Beach Day (from: Sat 9am  to: Sat 3pm)
```
### Marking a task as done: `mark`
Marks the specified task as done.

Format: `mark TASK_ID`

Example:
`mark 1`

Expected output:
```
I have marked this task as done
[T][X] Do My Reading
-------------------------------------------------
Your current tasks are as follows:
1. [T][X] Do My Reading
2. [D][ ] Do homework (by: tomorrow)
3. [E][ ] Beach Day (from: Sat 9am  to: Sat 3pm)
```
### Unmarke a task as not done: `unmark`
Unmarks the specified task.
Marks it as undone

Format: `unmark TASK_ID`

Example:
`unmark 1`

Expected output:
```
I have unmarked this task
[T][ ] Do My Reading
-------------------------------------------------
Your current tasks are as follows:
1. [T][ ] Do My Reading
2. [D][ ] Do homework (by: tomorrow)
3. [E][ ] Beach Day (from: Sat 9am  to: Sat 3pm)
```
### Deleting a task: `delete`
Deletes the task.

Format: `delete TASK_ID`

Example:
`delete 3`

Expected output:
```
I have removed this item in index 3
      [E][ ] Beach Day (from: Sat 9am  to: Sat 3pm)
-------------------------------------------------
Your current tasks are as follows: 
1. [T][X] Do My Reading
2. [D][ ] Do homework (by: tomorrow)
```

### Finding a task: `find`
Finds task that has the keyword in its description.

Format: `find KEYWORD`

Example:
`find Reading`

Expected output:
```
1. [T][X] Do My Reading
```

### Exit the program: `bye`
Exits the program.

Expected output:
```
___________________________******___________________________
```

### Automatically Saved
Your task list is automatically saved in the hardisk


