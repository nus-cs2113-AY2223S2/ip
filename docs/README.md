# User Guide

## Features 

### Feature- List

Displays the **list** of all tasks in Duke Task List.

### Feature- Mark

Marks the task as **Complete** in Duke Task List.

### Feature- Unmark

Marks the task as **Not Complete** in Duke Task List.

### Feature- Todo

Creates a **Todo Task** in Duke Task List.

### Feature- Deadline

Creates a **Deadline Task** in Duke Task List.

### Feature- Event

Creates an **Event Task** in Duke Task List.

### Feature- Delete

Deletes the specified task from the Duke Task List given the **Task Index**.

### Feature- Find

Finds and displays the list of task from Duke Task List matching the **Search Keyword**.

### Feature- Bye

**Exits** the Duke Program.

## Usage

### `list` - Lists all **Tasks** in Duke Task List.

Retrieves all the tasks from Duke ArrayList and displays it to the user.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Displays the Duke Task List.

```
list
______________________________________________________________________
 Here are the tasks in your list:
  1.  [T][X] read book
  2.  [D][ ] submit report (by Friday 3pm)
  3.  [E][ ] disco party (from 3pm to 5pm)
  4.  [D][X] return book (by June 6th)
______________________________________________________________________
```

### `mark` - Marks the Task as **Complete** in Duke Task List.

Marks the Task as **Complete** using the 'X' character in Duke Task List.

Example of usage:

`mark (task index to mark)`

Expected outcome:

Marks the Specified Task.

```
mark 2
______________________________________________________________________
 Nice! I've marked this task as done:
  [D][X] submit report (by Friday 3pm)
______________________________________________________________________
```

### `unmark` - Marks the Task as **Not Complete** in Duke Task List.

Marks the Task as **Not Complete** using the ' ' character in Duke Task List.

Example of usage:

`unmark (task index to unmark)`

Expected outcome:

Unmark the Specified Task.

```
unmark 2
______________________________________________________________________
 Okay, I've marked this task as not done yet:
  [D][ ] submit report (by Friday 3pm)
______________________________________________________________________
```

### `todo` - Creates a **Todo Task** in Duke Task List.

Creates a **Todo Task** in Duke Task List given the todo description from the user.

Example of usage:

`todo (todo description)`

Expected outcome:

Creates the Todo Task for the given input.

```
todo dance practice
______________________________________________________________________
 Got it. I've added this task:
  [T][ ] dance practice
 Now you have 5 tasks in your list.
______________________________________________________________________
```

### `deadline` - Creates a **Deadline Task** in Duke Task List.

Creates a **Deadline Task** in Duke Task List given the deadline description and due date from the user.

Example of usage:

`deadline (deadline description) /by (due date)`

Expected outcome:

Creates the Deadline Task for the given inputs.

```
deadline submit report final draft /by Tuesday 12pm
______________________________________________________________________
 Got it. I've added this task:
  [D][ ] submit report final draft (by Tuesday 12pm)
 Now you have 6 tasks in your list.
______________________________________________________________________
```

### `event` - Creates an **Event Task** in Duke Task List.

Creates an **Event Task** in Duke Task List given the event description, event start date/time and event end date/time from the user.

Example of usage:

`event (event description) /from (event start date/time) /to (event end date/time)`

Expected outcome:

Creates the Event Task for the given inputs.

```
event Birthday Party /from Wednesday 2pm /to 5pm
______________________________________________________________________
 Got it. I've added this task:
  [E][ ] Birthday Party (from Wednesday 2pm to 5pm)
 Now you have 7 tasks in your list.
______________________________________________________________________
```

### `delete` - Deletes the **Task** from Duke Task List.

Deletes the specified task from the Duke Task List given the **Task Index**.

Example of usage:

`delete (task index to delete)`

Expected outcome:

Deletes the Specified Task from Duke Task List.

```
delete 7
______________________________________________________________________
 Noted. I've deleted this task:
  [E][ ] Birthday Party (from Wednesday 2pm to 5pm)
 Now you have 6 tasks in your list.
______________________________________________________________________
```

### `find` - Finds the list of **Matching Tasks** from Duke Task List.

Finds and displays the list of task from Duke Task List matching the **Search Keyword**.

Example of usage:

`find (search keyword)`

Expected outcome:

Finds all Matching Tasks from Duke Task List.

```
find book
______________________________________________________________________
 Here are the matching tasks in your list:
  1.  [T][X] read book
  2.  [D][X] return book (by June 6th)
______________________________________________________________________
```

### `bye` - **Exits** the Duke Program.

Saves the current **Duke Task List** and **Exits** the Duke Program.

Example of usage:

`bye`

Expected outcome:

Displays Bye Message to the user.

```
bye
______________________________________________________________________
 Bye. Hope to see you again soon!
______________________________________________________________________
```