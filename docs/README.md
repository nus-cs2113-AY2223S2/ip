# User Guide for Duke

## Features 

### Add tasks to your to-do list

Add different types of tasks to your to-do list and Duke will keep track of it for you.

### Delete tasks from your to-do list

Delete irrelevant tasks from your to-do list.

### Keep track of task status

Mark or unmark tasks in the to-do list to keep track of what you have completed.

### Find tasks in to-do list

Find all tasks containing certain keywords. 


## Usage

### `todo` - Add a todo task.

Adds a todo task to the to-do list.

**Example of usage:** 

`todo finish lecture 6`

**Expected outcome:**


```
________________________________________________________________
Great! I've added this task:
   [T][ ] finish lecture 6
Now you have 1 task(s) in the list.
________________________________________________________________
```
### `deadline` - Add a deadline task.

Adds a deadline task to the to-do list. Specify the deadline using `/by` followed by the actual deadline.

**Example of usage:**

`deadline Assignment 2 /by Monday 2359`

**Expected outcome:**

```
________________________________________________________________
Great! I've added this task:
   [D][ ] Assignment 2  (by: Monday 2359)
Now you have 2 task(s) in the list.
________________________________________________________________
```

### `event` - Add an event task.
Adds an event task to the to-do list.  
Specify starting time using `/from` followed by the actual starting time.  
Specify ending time using `/to` followed by the actual ending time.

**Example of usage:**

`event project meeting /from Tue 8pm /to Tue 9pm`

**Example of outcome:**
```
________________________________________________________________
Great! I've added this task:
   [E][ ] project meeting (from: Tue 8pm to: Tue 9pm)
Now you have 3 task(s) in the list.
________________________________________________________________
```

### `list` - List all the tasks in the to-do list.

**Example of usage:**

`list`

**Example of outcome:**

```
________________________________________________________________
Here are the tasks in your list:
1. [T][ ] finish lecture 6
2. [D][ ] Assignment 2  (by: Monday 2359)
3. [E][ ] project meeting (from: Tue 8pm to: Tue 9pm)
________________________________________________________________
```

### `mark` - Mark a task as done.

Marks a task as done based on the index specified.

**Example of usage:**

`mark 1`

**Example of outcome:**

```
________________________________________________________________
Task 1 marked as done:
[T][X] finish lecture 6
________________________________________________________________
```

### `unmark` - Unmark a task that has been done.

Unmarks a task that has been done based on the index specified.

**Example of usage:**

`unmark 1`

**Example of outcome:**

```
________________________________________________________________
Task 1 marked as not done yet:
[T][ ] finish lecture 6
________________________________________________________________
```

### `delete` - Delete task.

Deletes a task based on the index specified.

**Example usage:**
`delete 1`

**Example outcome:**
```
________________________________________________________________
I've removed task 1:
   [T][X] finish lecture 6
Now you have 2 task(s) in the list.
________________________________________________________________
```

### `find` - Find tasks.

Finds and displays all tasks containing the search word, including their index number and full description.

**Example of usage:**

`find watch`

**Expected outcome:**
```
________________________________________________________________
Here are the matching tasks in your list:

4. [T][ ] watch lecture
5. [E][ ] watch movie with sister (from: 9pm to: 11pm)
________________________________________________________________
```

### `bye` - Exit Duke.

**Expected usage:**

`bye`

**Expected outcome:**
```
Thank you for using Duke. Hope to see you soon!
```