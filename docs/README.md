# User Guide

## Features

Users can add, mark, unmark, list tasks as needed.

### Add task

Users can add new tasks to the list. Tasks have 3 available types, Event, Todo and Deadline

Format: `type of task` `name of task` date-attribute of task
Example: deadline do tutorial /by Sunday

Expected outcome:
New tasks of correct type and description is added to task list

### Mark task

Users can mark tasks as done.

Format: mark `task index`
Example: mark 3

Expected outcome:
[ ] state of task(s) changes to [x] when marked as done.

### Unmark task

Users can mark tasks as undone.

Format: unmark `task index`
Example: mark 3

Expected outcome:
[x] state of task(s) changes to [ ] when marked as done.

### List task(s)

Users can list and view all tasks currently in the task list

Format: list

Expected outcome:
If task list is empty, "Tasklist is empty."
Indexed list of `index`, `done state`, `description`

### List overdue task(s)

Users can list and view all tasks that are currently overdue in the task list

Format: list overdue

Expected outcome:
If task list is empty, "Tasklist is empty."
Indexed list of overdue tasks `index`, `done state`, `description`

### Find

Users can find and list all tasks by inputting keyword to be searched

Format: find `string to be searched`
Example: find food

Expected outcome:
If task lsit is empty, "Tasklist is empty."
Indexed list of tasks with description containing searched string

### Delete

Users can remove tasks from task list
Format: delete `index`
Example: delete 3

Expected outcome:
If index out of bounds, "Please input valid task number!"
"Noted. I've removed this task:" `index`
