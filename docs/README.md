# User Guide

## Features 

### Persistent File Storage

Saves the tasks in a file on your storage drive. This allows the tasks to be loaded the next time the program is run.

### Date Parsing

When adding Deadline and Event tasks, Tusky is able to parse the date strings in `YYYY-MM-DD` format.

## Starting the program

1. Open the directory containing the `tusky.jar` file in the command line.
2. Run the command `java -jar tusky.jar`

## Usage

### `bye` - Exits the program

Example of usage:

`bye`

Expected outcome:

The program exits

```
Bye. Hope to see you again soon!
```

### `todo` - Adds a Todo task to the list.

Todo tasks only require a description.

Example of usage:

`todo <description>`

`description` can be up to 50 characters long, and allow for alphanumeric characters. `/` specifically is not allowed as it is used as a delimiter for keywords.

Expected outcome:

A Todo task is added to the list and written to file

```
Got it. I've added this task:
   [T][ ] <description>
 Now you have <size> tasks in the list.
```

### `deadline` - Adds a Deadline task to the list.

Deadline tasks have a `by` date, which can be used to indicate the deadline of a task.

Example of usage:

`deadline <description> /by <date>`

`description` can be up to 50 characters long, and allow for alphanumeric characters. `/` specifically is not allowed as it is used as a delimiter for keywords.

`date` is in the format of `YYYY-MM-DD`

Expected outcome:

A Deadline task is added to the list and written to file

```
Got it. I've added this task:
   [D][ ] <description> (by: Mar 3 2023)
 Now you have <size> tasks in the list.
```

### `event` - Adds an Event task to the list.

Events have a `from` and `to` date, which can be used to indicate the start and end dates of an event.

Example of usage:

`event <description> /from <start_date> /to <end_date>`

`description` can be up to 50 characters long, and allow for alphanumeric characters. `/` specifically is not allowed as it is used as a delimiter for keywords.

`start_date` and `end_date` are in the format of `YYYY-MM-DD`

Expected outcome:

An Event task is added to the list and written to file

```
Got it. I've added this task:
   [E][ ] <description> (from: Mar 3 2023 to: Mar 4 2023)
 Now you have <size> tasks in the list.
```


### `list` - Lists all tasks

Example of usage:

`list`

Expected outcome:

```
 Here are the tasks in your list:
 1.[T][X] do CS2113 ip
 2.[E][ ] CS2113 (from: Jan 1 2021 to: Dec 12 2023)
 3.[T][ ] other homework
 4.[D][ ] finish work (by: Mar 3 2023)
 5.[E][ ] sleep (from: Mar 3 2023 to: Mar 4 2023)
```
### `mark` - Mark a task as done

Example of usage:

`mark <index>`

The `index` should be a positive integer.

Expected outcome:

```
Nice! I've marked this task as done:
   [D][X] finish work (by: Mar 3 2023)
```

### `unmark` - Mark a task as not done

Example of usage:

`unmark <index>`

The `index` should be a positive integer.

Expected outcome:

```
OK, I've marked this task as not done yet:
   [D][ ] finish work (by: Mar 3 2023)
```
### `delete` - Delete a task

Example of usage:

`delete <index>`

The `index` should be a positive integer.

Expected outcome:

```
 Noted. I've removed this task:
   [T][ ] other homework
 Now you have <size> tasks in the list.
```
### `find` - Finds tasks by keyword

Returns all tasks with descriptions containing the keyword.

Example of usage:

`find <keyword>`

Expected outcome:

```
 Here are the matching tasks in your list:
 1.[T][X] sleep
 2.[E][X] sleep2 (from: Jan 1 2021 to: Dec 12 2023)
 3.[E][ ] sleep3 (from: Mar 3 2023 to: Mar 4 2023)
```

