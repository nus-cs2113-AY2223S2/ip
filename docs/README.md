# _**Bro**_ User Guide

## Features

- ### Adding a task: `todo`, `deadline`, `event`

    Add a ToDo, Deadline or Event task. The task will be saved upon ending the chat with Bro.

    Example of usage: 

    `todo Math Homework`

    `deadline Read Book /by Monday 8pm`

    `event Concert /from 6pm /to 7pm`

    Expected outcome:

    ```
    ─────────────────────────────────────
     added: Math Homework
    ─────────────────────────────────────
    ─────────────────────────────────────
     added: Read Book (by: Monday 8pm)
    ─────────────────────────────────────
    ─────────────────────────────────────
     added: Concert (from: 6pm to: 7pm)
    ─────────────────────────────────────
    ```

- ### List all tasks: `list`

    List all tasks that have been saved, as well as tasks that have just been added.

    Example of usage:
    
    `list`

    Expected outcome:

    ```
    ─────────────────────────────────────
     Your tasks:
     1.[T][ ] Math Homework
     2.[D][ ] Read Book (by: Monday 8pm)
     3.[E][ ] Concert (from: 6pm to: 7pm)
    ─────────────────────────────────────
    ```

- ### Mark a task as complete / incomplete: `mark`, `unmark`

    Mark an incomplete task as complete, or mark a complete task as incomplete. Nothing happens if an incomplete task is marked as incomplete or if a complete task is marked as complete. Throws an error if the index is out of bounds.

    Example of usage:
    
    `mark 2`

    Expected outcome:

    ```
    ─────────────────────────────────────────────
     Marked Read Book (by: Monday 8pm) as done.
    ─────────────────────────────────────────────

    list
    ────────────────────────────────────
     Your tasks:
     1.[T][ ] Math Homework
     2.[D][✔] Read Book (by: Monday 8pm)
     3.[E][ ] Concert (from: 6pm to: 7pm)
    ─────────────────────────────────────
    ```

- ### Delete a task: `delete`

    Delete a task from the saved tasks list by inputting the index of the task. Throws an error if the index is out of bounds.

    Example of usage: 

    `delete 2`

    Expected outcome:

    ```
    ───────────────────────────────────────
     Ok bro I remove this task:
        [D][✔] Read Book (by: Monday 8pm)
     Now you have 2 tasks in the list.
    ───────────────────────────────────────
    ```

- ### Find a task: `find`

    Delete a task from the saved tasks list. Throws an error if the index is out of bounds.

    Example of usage: 

    `find math`

    Expected outcome:

    ```
    ────────────────────────────────────────────
    Here are the matching tasks in your list:
    1.[T][ ] Math Homework
    ────────────────────────────────────────────
    ```