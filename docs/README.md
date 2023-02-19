# User Guide

## Features 

1. Supports three different types of tasks: `Todo`, `Deadline` and `Event`
2. Supports marking tasks as completed or not completed
3. Displays stored tasks
4. Reminds user about upcoming deadlines
5. Supports searching for previously added tasks

### Feature-ABC

Description of the feature.

### Feature-XYZ

Description of the feature.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

## User-Guide

### Adding a new Todo
- Syntax: `todo [task-description]`

### Adding a new Deadline
- Syntax: `deadline [task-description] /by [date]`
- Date Format: "yyyy-M-d" or "yyyy-M-d HH:mm"
   - If no time is specified, Duke sets time to 23:59
- Deadline cannot be set in the past, else task will not be added

### Adding a new Event
- Syntax: `event [task-description] /from [date] /to [date]`
- Date Format: "yyyy-M-d" or "yyyy-M-d HH:mm"
   - If no time is specified, Duke sets time to 23:59
- End Date cannot be set in the past, else task will not be added
- Start Date cannot be after End Date, else task will not be added

### Displaying all tasks
- Syntax: `list`

### Searching for tasks
- Syntax: `find [query]`

### Displaying upcoming tasks
- Syntax: `upcoming`
- Returns all tasks with deadline within 3 days

### Marking task as completed
- Syntax: `mark [index]`
- Recommended to use `list` before `mark` to get the task index

### Marking task as not completed
- Syntax: `unmark [index]`
- Recommended to use `list` before `unmark` to get the task index

### Deleting task
- Syntax: `delete [index]`
- Recommended to use `list` before `delete` to get the task index

### Exiting
- Syntax: `bye`