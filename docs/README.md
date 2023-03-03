# User Guide

## Features 

### Addition of Tasks
Users are able to add up to 3 different types of tasks:
 1. <code>todo</code>: A simple task description
 2. <code>deadline</code>: A task description with a deadline
 3. <code>event</code>: A task description with a time period


### Operations on the Tasks
These are the operations that users can perform on the task list:
 1. <code>list</code>: List all the task in the list
 2. <code>mark</code>: Mark the task specified as done
 3. <code>unmark</code>: Mark the task specified as undone
 4. <code>find</code>: Search for all task with matching string and list them
 5. <code>delete</code>: Delete task from the list

### Exit the Program
To exit the program, input <code>bye</code> into the CLI.

## Usage

### `todo` - Adds a to-do task
This command will add a task description to task list and will be labeled as a todo task.

Format: `todo <taskDescription>`

Example of usage: `todo do laundry`

Expected outcome:
```
Got it. I've added this task:
[T][ ] do laundry
Now you have 1 tasks in the list.
____________________________________________________________
```

Description of the outcome:
All characters after <code>todo</code> will be treated as the task description.

### `deadline` - Adds a deadline task
This command will add a task description to the task list with a deadline and will
be labelled as a deadline task.

Format: `deadline <taskDescription> /by <dueDate>`

Example of usage: `deadline cs2113 iP /by 3 Mar 2359`

Expected outcome:
```
Got it. I've added this task:
 [D][ ] cs2113 iP (by: 3 Mar 2359)
Now you have 2 tasks in the list.
____________________________________________________________
```

Description of the outcome:
Users should input the task description before the <code>/by</code> keyword and the
due-date after it. All characters before <code>/by</code> would be treated as the 
task description and the ones after are treated as the due date.

### `event` - Adds a deadline task
This command will add a task description to the task list with a time period and will
be labelled as an event task.

Format: `event <taskDescription> /from <startTime> /to <endTime>`

Example of usage:
`event Recess Week /from 20 Feb /to 26 Feb`

Expected outcome:
```
Got it. I've added this task:
 [E][ ] Recesss Week (from: 20 Feb to: 26 Feb)
Now you have 3 tasks in the list.
____________________________________________________________
```

Description of the outcome:
All characters before <code>/from</code> would be treated as the
task description and the ones after <code>/to</code> are treated as the end time.
The characters in-between will be treated as the start time. 

### `list` - List out all the task in the list
This command will print the all the task in the sequence in which they were added to 
the list. The message <code>You are free today :)</code> will show if the list is empty.

Format: `list`

Example of usage: `list`

Expected outcome:
```
1.[T][ ] do laundry
2.[D][ ] cs2113 iP (by: 3 Mar 2359)
3.[E][ ] Recess Week (from: 20 Feb to: 26 Feb)
____________________________________________________________
```

Description of the outcome:
The letter in the first square brackets represents the task type: 'T' for to-do, 'D'
for deadline and 'E' for event. The letter in the second square brackets represents
the completion status: 'X' for completed and empty for uncompleted.

### `mark` - Mark task based on index
Task of the corresponding index will be marked as done.

Format: `mark <taskIndex>`

Example of usage: `mark 2`

Expected outcome:
```
Nice! I've marked this task as done:
[D][X] CS2113 iP (by: 3 Mar 2359)
____________________________________________________________
```

### `unmark` - Unmark task based on index
Task of the corresponding index will be marked as undone.

Format: `unmark <taskIndex>`

Example of usage: `unmark 2`

Expected outcome:
```
Ok, I've marked this task as not done yet:
[D][ ] CS2113 iP (by: 3 Mar 2359)
____________________________________________________________
```

### `find` - Print all the task which contains the keyword
Based on the keyword (case-sensitive), all task description which contains the 
keyword would be printed. The order will be determined based on the order they 
were added to the list.

Format: `find <keyword>`

Example of usage: `find s`

Expected outcome:
```
Here are the matching tasks in your list:
1.[D][ ] cs2113 iP (by: 3 Mar 2359)
2.[E][ ] Recess Week (from: 20 Feb to: 26 Feb)
____________________________________________________________
```

### `delete` - Delete task based on index
Task of the corresponding index will be removed from the list.

Format: `delete <taskIndex>`

Example of usage: `delete 1` 

Expected outcome:
```
Noted. I've removed this task:
 [T][ ] do laundry
Now you have 2 tasks in the list.
```


### `bye` - Exit the program
This command will write the save file for the task list and close the program. 

Format: `bye`

Example of usage: `bye`

Expected outcome:
```
Bye. Hope to see you again soon!
____________________________________________________________
```
