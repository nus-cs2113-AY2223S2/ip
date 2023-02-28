# User Guide

This is a desktop app to track your tasks. Optimized for used via Command Line Interface (CLI)

- [Quick Start](#quick-start)
- [Commands Format](#commands-format)
- [Features](#features)
- [Usage](#usage)
- [Additional Notes regarding Loading and Saving](#additional-notes-regarding-loading-and-saving)

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer  
   a. If you are new to java you may follow the [provided guide](#setting-up-in-intellij) to confirm your installation
2. Download the latest duke.jar from here
3. Copy the file to the folder you want to use as the home folder for your AddressBook
4. Start with `java -jar duke.jar`

## Commands Format
- Words in UPPER_CASE are the parameters to be supplied by the user e.g. in `todo DESCRIPTION`, DESCRIPTION is a parameter that can be added with variable length with even white space in between

- Words in lower_case are fixed key words. e.g. in `deadline DESCRIPTION /by END_DATETIME`, all fixed key words are separated by white space. Consult the [Feature List](#feature-list) for the list of commands/ key words required by each command

## Features 
1. Add a Todo task `todo DESCRIPTION`
2. Add a Deadline task `deadline DESCRIPTION /by END_DATETIME`
3. Add a Event task `event DESCRIPTION /from START_DATETIME /to END_DATETIME`
4. List all tasks that exists already `list`
5. Mark the task that as completed `mark TASK_IDX`
6. Unmark the task that as uncompleted `unmark TASK_IDX`
7. List all tasks that are like the input TASK_NAMELIKE `find TASK_NAMELIKE`
8. Delete a task `delete TASK_ID`
9. Save all tasks that are currently stored `save`
10. Load all tasks that are in the file data/duke.txt `load`
11. End the conversation `bye`

## Usage

### `todo DESCRIPTION` - Adds a Todo Task

Adds a simple task that only has description. Inputs end with next-line character

Example of usage: 

`todo Dye my hair`

Expected outcome:

Description of the outcome.

```
    ____________________________________________________________
    Got it. I've added this task:
    {LIST OF TASKS}
    Now you have %d tasks in your list.
    ____________________________________________________________
```

### `deadline DESCRIPTION /by END_DATETIME` - Adds a Deadline Task

Adds a simple task that only has description and a deadline. Inputs end with next-line character

Example of usage: 

`deadline Cut my hair /by tomorrow`

Expected outcome:

```
    ____________________________________________________________
    Got it. I've added this task:
      [T][ ] Dye my hair
    Now you have 2 tasks in your list.
    ____________________________________________________________
```

### `event DESCRIPTION /from START_DATETIME /to END_DATETIME` - Adds an event Task

Adds a task that has description, a start timing and end timing. Inputs end with next-line character

Example of usage: 

`event Shopping for a wig /from the following day 2pm /to 8pm`

Expected outcome:

```
    ____________________________________________________________
    Got it. I've added this task:
      [E][ ] Shopping for a wig (from: the following day 2pm to: 8pm)
    Now you have 3 tasks in your list.
    ____________________________________________________________
```

### `list` - Lists all tasks

Lists all tasks in the entire task list, showing whether they are marked and which category they are in

Example of usage: 

`list`

Expected outcome:

```
    ____________________________________________________________
    Here are the tasks in your list:
    1.[T][ ] Dye my hair
    2.[D][ ] Cut my hair (by: tomorrow)
    3.[E][ ] Shopping for a wig (from: tomorrow 2pm to: 8pm)

    ____________________________________________________________
```
### `mark TASK_ID` - Mark a task as complete

Mark a task as complete based on the ID provided. If the task is already complete, do nothing. TASK_ID corresponds to the integer beside the task in the output of the `list` function.

Example of usage: 

`mark 1`

Expected outcome:

```
    ____________________________________________________________
         Nice! I've marked this task as done
    [T][X] Dye my hair
    ____________________________________________________________
```

### `unmark TASK_ID` - Unmark a task 

Unmark a completed task based on the ID provided. If the task is already unmarked, does nothing. TASK_ID corresponds to the integer beside the task in the output of the `list` function.

Example of usage: 

`unmark 1`

Expected outcome:

```
    ____________________________________________________________
         OK, I've marked this task as not done yet:
    [T][ ] Dye my hair
    ____________________________________________________________
```

### `find NAME_LIKE` - Find all tasks like NAME_LIKE

Outputs all tasks that have a name like the given input. This is caps sensitive and whitespace sensitive.

Example of usage: 

`find my hair`

Expected outcome:

```
    ____________________________________________________________
    Here are the matching tasks in your list:
    1.[T][ ] Dye my hair
    2.[D][ ] Cut my hair (by: tomorrow)

    ____________________________________________________________
```

### `delete TASK_ID` - Delete a task 

Deletes a task from the task list. TASK_ID corresponds to the integer beside the task in the output of the `list` function.

Example of usage: 

`delete 1`

Expected outcome:

```
    ____________________________________________________________
    Noted. I've removed this task:
      [T][ ] Dye my hair
    Now you have 2 tasks in the list.
    ____________________________________________________________
```

### `save` - saves the current tasklist into storage

Saves the current tasklist in memory into storage through the following filepath: `{ROOT_DIR}/data/duke.txt`

Example of usage: 

`save`

Expected outcome:

```
    ____________________________________________________________
    Save Successful!
    ____________________________________________________________
```

### `load` - Load the current tasklist into storage

Load the current tasklist in memory into storage through the following filepath: `{ROOT_DIR}/data/duke.txt`

Example of usage: 

`load`

Expected outcome:

```
    ____________________________________________________________
    Load Successful!
    ____________________________________________________________
```

### `bye` - End the current session 

End the current session with Duke. Your tasks are **NOT** autosaved. If you would like to keep your tasks make sure to save first

Example of usage: 

`bye`

Expected outcome:

```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```
## Additional Notes Regarding Loading and Saving
- On the first use of the app, it might tell you that there are issues with loading the file. This is normal as the app checks for a save file in the '{ROOT_FOLDER}/data/duke.txt' on every load.
- Leaving the app does NOT save your current task list. Make sure to save using the `save` command if you would like to keep your tasks. You can find the output file '{ROOT_FOLDER}/data/duke.txt'
- You may also edit the files directly from the output save file '{ROOT_FOLDER}/data/duke.txt' for quick loads. The `load` function works appends all tasks into the current tasklist. Make sure to check for duplicates
- If you would like to clear your tasks before a new session. You may delete the '{ROOT_FOLDER}/data/duke.txt' file or clear its contents
- The non-autosave & autoload function allows more advanced users to navigate between adding and loading their tasklists quickly