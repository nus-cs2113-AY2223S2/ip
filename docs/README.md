# User Guide

## Features

### Keep a List of Items

Keep forgetting your tasks? Have no fear, NakiriAyame will help you keep track of them!

### Flexible Inputs

You're free to specify your dates as a date, or as a string describing the conditions and long enough to summon a demon.

### Persistent Data

Your Tasks are stored in a text file after every action, so your data (unlike Ayame herself) is never lost!

### Filter easily!

NakiriAyame filters and returns your Tasks by any keyword in O(1)! (Trust me, I got A+ in CS2040C)

### Ascii Art of Nakiri Ayame!

![Picture of Ayame](https://github.com/BenjaminPoh/ip/blob/master/assets/yammers.png?raw=true)

If you find it, that is.

## Usage

### `todo [Task]` - Add a Todo Task

Example of usage:  `todo Fix Ayame Ascii Art`

Expected outcome: Shows the added Todo Task. (Note the [T] to denote Todo)

```
Done! Added: [T][ ] Fix Ayame Ascii Art
```

### `deadline [Task] /by [String]` - Add a Deadline Task

Example of usage:  `deadline Finish ReadMe.md /by tonight`

Expected outcome: Shows the added Deadline Task. (Note the [D] to denote Deadline)

```
Done! Added: [D][ ] Finish ReadMe.md (By: tonight)
```

### `event [Task] /from [String] /to [String]` - Add a Event Task

Example of usage:  `event Go for my Google Internship with Ayame (real) /from April /to July`

Expected outcome: Shows the added Event Task. (Note the [E] to denote Event)

```
Done! Added: [E][ ] Go for my Google Internship with Ayame (real) (From: April To: July)
```

### `mark [Index]` - Mark a Task as complete

Example of usage:  `mark 2`

Expected outcome: Mark a task as complete (Note the [X] to denote a completed Task)

```
Nice! I've marked this task as done!
[D][X] Finish ReadMe.md (By: tonight)
```

### `unmark [Index]` - Mark a Task as incomplete

Example of usage:  `unmark 2`

Expected outcome: Mark a task as incomplete (Note the [ ] to denote an uncompleted Task)

```
Stop being lazy, get to work!
[D][ ] Finish ReadMe.md (By: tonight)
```

### `list` - Lists all recorded Tasks

Example of usage:  `list`

Expected outcome: Show all recorded Tasks and their status.

```
1.[T][ ] Fix Ayame Ascii Art
2.[D][ ] Finish ReadMe.md (By: tonight)
3.[E][ ] Go for my Google Internship with Ayame (real) (From: April To: July)
```

### `find [String]` - Displays for all tasks with the matching String

Example of usage:  `find Ayame`

Expected outcome: Displays all tasks with the matching String

```
These are the tasks found with Keyword: Ayame
[1] [T][ ] Fix Ayame Ascii Art
[2] [E][ ] Go for my Google Internship with Ayame (real) (From: April To: July)
```

### `delete [index]` - Delete a Task

Example of usage:  `delete 3`

Expected outcome: Deletes a specific task from the list, shows the deleted Task and the number of Tasks left

```
Task removed:
[E][ ] Go for my Google Internship with Ayame (real) (From: April To: July)
Total tasks left: 2
```

### `bye` - Closes NakiriAyame

Example of usage:  `bye`

Expected outcome:

```
Otsu-Nakiri!
```