# User Guide

## Features 

### Feature- List

Displays the **List** of all tasks in Duke Task List.

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

`list`

Expected outcome:

Displays the Duke Task List.

```
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
Marks the Task given the **Specific Task Index**.

Example of usage:

`mark 2`

Expected outcome:

Marks the above Specified Task.

```
______________________________________________________________________
 Nice! I've marked this task as done:
  [D][X] submit report (by Friday 3pm)
______________________________________________________________________
```

### `unmark` - Marks the Task as **Not Complete** in Duke Task List.

Marks the Task as **Not Complete** using the ' ' character in Duke Task List.
Unmarks the Task given the **Specific Task Index**.

Example of usage:

`unmark 2`

Expected outcome:

Unmark the above Specified Task.

```
______________________________________________________________________
 Okay, I've marked this task as not done yet:
  [D][ ] submit report (by Friday 3pm)
______________________________________________________________________
```

### `todo` - Creates a **Todo Task** in Duke Task List.

Creates a **Todo Task** in Duke Task List given the **Todo Description** from the user.

Example of usage:

`todo dance practice`

Expected outcome:

Creates the Todo Task for the above given input.

```
______________________________________________________________________
 Got it. I've added this task:
  [T][ ] dance practice
 Now you have 5 tasks in your list.
______________________________________________________________________
```

### `deadline` - Creates a **Deadline Task** in Duke Task List.

Creates a **Deadline Task** in Duke Task List given the **Deadline Description** and **Due Date** from the user.

Example of usage:

`deadline submit report final draft /by Tuesday 12pm`

Expected outcome:

Creates the Deadline Task for the above given inputs.

```
______________________________________________________________________
 Got it. I've added this task:
  [D][ ] submit report final draft (by Tuesday 12pm)
 Now you have 6 tasks in your list.
______________________________________________________________________
```

### `event` - Creates an **Event Task** in Duke Task List.

Creates an **Event Task** in Duke Task List given the **Event Description**, **Event Start Date/Time** and **Event End Date/Time** from the user.

Example of usage:

`event Birthday Party /from Wednesday 2pm /to 5pm`

Expected outcome:

Creates the Event Task for the above given inputs.

```
______________________________________________________________________
 Got it. I've added this task:
  [E][ ] Birthday Party (from Wednesday 2pm to 5pm)
 Now you have 7 tasks in your list.
______________________________________________________________________
```

### `delete` - Deletes the **Task** from Duke Task List.

Deletes the **Specified Task** from the Duke Task List given the **Task Index**.

Example of usage:

`delete 7`

Expected outcome:

Deletes the above Specified Task from Duke Task List.

```
______________________________________________________________________
 Noted. I've deleted this task:
  [E][ ] Birthday Party (from Wednesday 2pm to 5pm)
 Now you have 6 tasks in your list.
______________________________________________________________________
```

### `find` - Finds the list of **Matching Tasks** from Duke Task List.

Finds and displays the **List of Tasks** from Duke Task List matching the **Search Keyword**.

Example of usage:

`find book`

Expected outcome:

Finds all Matching Tasks from Duke Task List given the above **Keyword book**.

```
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
______________________________________________________________________
 Bye. Hope to see you again soon!
______________________________________________________________________
```