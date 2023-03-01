# User Guide
Siri is a **desktop app for managing tasks, optimized for use via
a Command Line Interface (CLI)** while still having the benefits 
of a Graphic User Interface (GUI). If you can type fast, Siri 
can get your task management done faster than traditional GUI 
apps.

## Features 

### Adding a task: <code>todo</code>, <code>deadline</code>, <code>event</code>
Adds different types of task to the task list.  
There are three different types of task that can be added to the task list: `todo`, `deadline`, and `event`.

### List: <code>list</code>
Shows a list of all tasks in the task list.

### Mark a task: `mark`
Marks a task as done. The particular task status will be set as "done".

### Unmark a task: `unmark`
Marks a task as not done. The particular task status will be set as "not done".

### Deleting a task: `delete`
Deletes the specified task from the task list. The task list will no longer contain the deleted task.

### Finding tasks: `find`
Find tasks using keyword. A list of tasks containing the keyword will be printed to the user.

### Exiting the application: `bye`
The application will exit.

## Usage

### `todo` - Adds a todo task
Adds a todo task to the task list and prints out the added `todo` task to the user.

**Format**: `todo NAME_OF_TASK`
- Adds a todo task that is named `NAME_OF_TASK`.

**Example of usage**:  
`todo CS2113 Assignment`

**Expected outcome**:  
`CS2113 Assignment` will be added as a todo task to the task list.
```
Got it. I've added this task:
   [T][] CS2113 Assignment
Now you have 1 tasks in the list.
```


### `deadline` - Adds a deadline task
Adds a deadline task to the task list and prints out the added `deadline` task to the user.

**Format**: `deadline NAME_OF_TASK /by DEADLINE_OF_TASK`
- Adds a deadline task that is named `NAME_OF_TASK` with a deadline of `DEADLINE_OF_TASK`.
- Deadline of the task is indicated by `/by`.

**Example of usage**:  
`deadline Submit CS2113 ip Final Version /by 3rd March 2359`

**Expected outcome**:  
`Submit CS2113 ip Final Version` will be added as a deadline task to the task list.  
`3rd March 2359` will be added as the deadline of the task.

```
Got it. I've added this task:
  [D][ ] Submit CS2113 ip Final Version (by: 3rd March 2359)
Now you have 2 tasks in the list.
```


### `event` - Adds an event task
Adds an event task to the task list and prints out the added `event` task to the user.

**Format**: `event NAME_OF_TASK /from FROM_DATE /to TO_DATE`
- Adds an event task that is named `NAME_OF_TASK` with the starting date: `FROM_DATE` and end date: `TO_DATE`.
- Starting date of the event is indicated by `/from`.
- End date of the event is indicated by `/to`.

**Example of usage**:  
`event CS2113 Lecture 6 /from Friday 4pm /to Friday 6pm`

**Expected outcome**:  
`event CS2113 Lecture 6` will be added as an event task to the task list.  
`Friday 4pm` will be added as the start date of the event (task).  
`Friday 6pm` will be added as the end date of the event (task).

```
Got it. I've added this task:
  [E][ ] CS2113 Lecture 6 (from: Friday 4pm to: Friday 6pm)
Now you have 3 tasks in the list.
```


### `list` - List the tasks.
Prints out all tasks available in the task list.

**Format**: `list`

**Example of usage**:  
`list`

**Expected outcome**:  
Assuming that the below three lines of code are execute before `list`:  
   `todo CS2113 Assignment`  
   `deadline Submit CS2113 ip Final Version /by 3rd March 2359`  
   `event CS2113 Lecture 6 /from Friday 4pm /to Friday 6pm` 

The above three tasks will be printed out as shown below when `list` is executed.
```
Below is your task list
1. [T][ ] CS2113 Assignment
2. [D][ ] Submit CS2113 ip Final Version (by: 3rd March 2359)
3. [E][ ] CS2113 Lecture 6 (from: Friday 4pm to: Friday 6pm)
```


### `mark` - Mark the task
Sets the specified task as done based on the task index.

**Format**: `mark TASK_INDEX`
- mark the task at the specified `TASK_INDEX`.
- The index refers to the index number shown in the displayed task list. 
- The index must be a positive integer 1, 2, 3, …​

**Example of usage**:  
`mark 1`

**Expected outcome**:  
Assuming the task list consists of the tasks:
```
Below is your task list
1. [T][ ] CS2113 Assignment
2. [D][ ] Submit CS2113 ip Final Version (by: 3rd March 2359)
3. [E][ ] CS2113 Lecture 6 (from: Friday 4pm to: Friday 6pm)
```
After executing the code `mark 1`, the expected output would be:
```
Nice! I've marked this task as done:
  [T][X] CS2113 Assignment
```


### `unmark` - Unmark the task
Sets the specified task as not done based on the task index.

**Format**: `unmark TASK_INDEX`
- `unmark` the task at the specified `TASK_INDEX`.
- The index refers to the index number shown in the displayed task list. 
- The index must be a positive integer 1, 2, 3, …​

**Example of usage**:  
`unmark 1`

**Expected outcome**:  
Assuming the task list consists of the tasks:
```
Below is your task list
1. [T][X] CS2113 Assignment
2. [D][ ] Submit CS2113 ip Final Version (by: 3rd March 2359)
3. [E][ ] CS2113 Lecture 6 (from: Friday 4pm to: Friday 6pm)
```
After executing the code `unmark 1`, the expected output would be:
```
Ok! I've marked this task as not done yet:
  [T][ ] CS2113 Assignment
```

### `delete` - Delete a task
Deletes the specified task from the task list.

**Format**: `delete TASK_INDEX`
- Deletes the task at the specified `TASK_INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3 ...

**Example of usage**:  
`delete 2`

**Expected outcome**:  
Assuming the task list consists of the tasks:
```
Below is your task list
1. [T][ ] CS2113 Assignment
2. [D][ ] Submit CS2113 ip Final Version (by: 3rd March 2359)
3. [E][ ] CS2113 Lecture 6 (from: Friday 4pm to: Friday 6pm)
```
After executing the code `delete 2`, the expected output would be:
```
Noted! I've deleted this task:
  [D][ ] Submit CS2113 ip Final Version (by: 3rd March 2359)
Now you have 2 tasks in the list.
```


### `find` - Find tasks from the task list
Find tasks which names contain any of the given keywords and prints out the tasks.

**Format**: `find KEYWORD`
- The search is case-sensitive. e.g.`CS2113` will not match `cs2113`
- The order of the keywords matter. e.g.`CS2113 Assignment` will not match `Assignment CS2113`
- Only the name of the task is searched.

**Example of usage**:  
`find Lecture 6`

**Expected outcome**:  
Assuming the task list consists of the tasks:
```
Below is your task list
1. [T][ ] CS2113 Assignment
2. [D][ ] Submit CS2113 ip Final Version (by: 3rd March 2359)
3. [E][ ] CS2113 Lecture 6 (from: Friday 4pm to: Friday 6pm)
4. [T][ ] Read Lecture 6 Slides
```
After executing the code `find Lecture 6`, the expected output would be:
```
Here are the matching tasks in your list:

3. [E][ ] CS2113 Lecture 6 (from: Friday 4pm to: Friday 6pm)
4. [T][ ] Read Lecture 6 Slides
```


### `bye` - Exit the program
Exits the program.

**Format**: `bye`

**Example of usage**:  
`bye`

**Expected outcome**:
```
>o< Goodbye! Hope to see you again soon! >o<
```