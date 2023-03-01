# User Guide
Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.

## Features 

### Add tasks

There are 3 types of tasks that can be added to the task list
   * todo task (represented with [T])
   * deadline task (represented with [D])
   * event task (represented with [E])

### Lists tasks

Lists all the tasks currently stored in the task list

### Delete tasks

Deletes a task from your task list with a specified index

### Find tasks

Find all the tasks currently stored in the task list which contains a specific substring

### Local Database

Automatically stores users' task list into the hard drive of their computer. The task list can be loaded up upon startup of the program again.

## Usage

### `todo` - Add a todo task

Adds a new todo task to the task list. The description of the task has to be provided.

Format: `todo <description>`

Example of usage: 

`todo CS2113 assignment`

Expected outcome:

* Creates a new todo task to be added to the task list.
  * `COMMAND`:  todo
  * `DESCRIPTION`: CS2113 assignment

```
____________________________________________________________
Got it. I've added this task:
  [T][ ] CS2113 assignment
Now you have 1 tasks in the list.
____________________________________________________________
```

### `list` - Add a deadline task

Adds a new deadline task to the task list. The description and the deadline of the task has to be provided.

Format: `deadline <description> /by <deadline>`

Example of usage:

`deadline CS2113 assignment /by Friday 3pm`

Expected outcome:

* Creates a new deadline task to be added to the task list.
    * `COMMAND`:  deadline
    * `DESCRIPTION`: CS2113 assignment
    * `DEADLINE`: Friday 3pm

```
____________________________________________________________
Got it. I've added this task:
  [D][ ] CS2113 assignment (by: Friday 3pm)
Now you have 1 tasks in the list.
____________________________________________________________
```

### `event` - Add an event task

Adds a new event task to the task list. The description and the duration of the task has to be provided.

Format: `event <description> /from <start> to <end>`

Example of usage:

`event CS2113 Lecture /from 4pm /to 6pm`

Expected outcome:

* Creates a new event task to be added to the task list.
    * `COMMAND`:  
    * `DESCRIPTION`: CS2113 assignment
    * `FROM`: 4pm
    * `TO`: 6pm

```
____________________________________________________________
Got it. I've added this task:
  [E][ ] CS2113 Lecture (from: 4pm to: 6pm)
Now you have 1 tasks in the list.
____________________________________________________________
```

### `list` - List all tasks

Displays all the tasks currently stored in the task list.

Format: `list`

Example of usage:

`list`

Expected outcome:

* Lists all the tasks in the order they were added
    * `COMMAND`: list

```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] CS2113 assignment
2.[D][ ] CS2113 assignment (by: Friday 3pm)
3.[E][ ] CS2113 Lecture (from: 4pm to: 6pm)
____________________________________________________________
```

### `mark` - Mark a task

Marks a task with the given index by the user as completed. The index has to be a number from 1 to the size of the task list.

Format: `mark <index>`

Example of usage:

`mark 1`

Expected outcome:

* Marks the task at index 1 of the task list as done.
    * `COMMAND`: mark
    * `INDEX`: 1

```
____________________________________________________________
Nice! I've marked this task as done:
[T][X] CS2113 assignment
____________________________________________________________
```

### `unmark` - Unmark a task

Unmark a task with the given index by the user as not completed. The index has to be a number from 1 to the size of the task list.

Format: `unmark <index>`

Example of usage:

`unmark 1`

Expected outcome:

* Unmark the task at index 1 of the task list as not done.
    * `COMMAND`: unmark
    * `INDEX`: 1

```
____________________________________________________________
OK, I've marked this task as not done yet:
[T][ ] CS2113 assignment
____________________________________________________________
```

### `delete` - Delete a task

Removes the task with the given index by the user from the task list. The index has to be a number from 1 to the size of the task list.

Format: `delete <index>`

Example of usage:

`delete 1`

Expected outcome:

* Deletes the task of index 1 from the task list
* Displays remaining number of tasks in the list
    * `COMMAND`: delete
    * `INDEX`: 1

```
____________________________________________________________
Noted. I've removed this task:
  [T][ ] CS2113 assignment
Now you have 2 tasks in the list.
____________________________________________________________
```

### `find` - Finds tasks with substring

Finds the tasks with similar substring given by the user from the task list. 

Format: `find <substring>`

Example of usage:

`find assignment`

Expected outcome:

* Find and display the tasks with description that contains the given substring in the task list
    * `COMMAND`: find
    * `substring`: assignment

```
____________________________________________________________
Here are the matching tasks in your list:
1.[D][ ] CS2113 assignment (by: Friday 3pm)
____________________________________________________________
```

### `bye` - Exit the program

Closes and exits the program, Duke 

Format: `bye`

Example of usage:

`bye`

Expected outcome:

* Program outputs an exit message and closes the program
    * `COMMAND`: bye

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```