# User Guide

Jonathan is a Personal Assistant Chatbot that helps a person to keep track of various things

## Features 

### Adding an entry: ```todo``` ```deadline``` ```event```

There are three options to add a new entry of task:

1. ```todo```: is a task without any date/time attached to it. e.g., visit new theme park.

##### Command

```aidl
todo <description>
```

##### Usage and Example Output

```
todo borrow book
    ____________________________________________________________
    Got it. I've added this task:
    [T][ ] borrow book
    Now you have 1 tasks in the list.
    ____________________________________________________________
```

2. ```deadline```
is a task that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm

##### Command

```
deadline <description> /by <date&time>

Additional notes:
date&time format shoudl be: dd/mm/yyy hhmm
should be in 24h format
```

##### Usage and Example Output

```
deadline submit report /by 11/10/2019 1730
    ____________________________________________________________
    Got it. I've added this task:
    [D][ ] submit report (by: 11th of October 2019, 5:30PM)
    Now you have 1 tasks in the list.
    ____________________________________________________________
```

3. ```event```
is a task that start at a specific date/time and ends at a specific date/time e.g.,
team project meeting 2/10/2019 2-4pm

##### Command

```
event <description> /from <date&time> /to <date&time>

Additional notes:
date&time format shoudl be: dd/mm/yyy hhmm
should be in 24h format
```

##### Usage and Example Output

```
event team project meating /from 11/10/2019 1700 /to 11/10/2019 1830
    ____________________________________________________________
    Got it. I've added this task:
    [E][ ] team project meating (from: 11th of October 2019, 5:0PM to: 11th of October 2019, 6:30PM)
    Now you have 2 tasks in the list.
    ____________________________________________________________
```

### Show all tasks: ```list```

##### Command

```
list
```

##### Usage and Example Output

```
list
    ____________________________________________________________
    Here are the tasks in your list:
    1. [D][ ] submit report (by: 11th of October 2019, 5:30PM)
    2. [E][ ] team project meating (from: 11th of October 2019, 5:0PM to: 11th of October 2019, 6:30PM)
    3. [T][ ] borrow book
    ____________________________________________________________
```

### Find tasks: ```find```

This feature will show all task that contain <keyword> in the description of the task

##### Command

```
find <keyword>
```

##### Usage and Example Output

```
find book
    ____________________________________________________________
    Here are the matching tasks in your list:
    1. [T][ ] borrow book
    2. [D][ ] return book (by: 11th of July 2019, 12:0PM)
    ____________________________________________________________
```

### Delete task: ```delete```

##### Command

```
delete <task_number>

Additional notes:
<task_number> must be positive integer (eg. 1, 2, 3) and must match with the
task number when the list command is called
```

##### Usage and Example Output

```
list
    ____________________________________________________________
    Here are the tasks in your list:
    1. [D][ ] submit report (by: 11th of October 2019, 5:30PM)
    2. [E][ ] team project meating (from: 11th of October 2019, 5:0PM to: 11th of October 2019, 6:30PM)
    3. [T][ ] borrow book
    4. [D][ ] return book (by: 11th of July 2019, 12:0PM)
    ____________________________________________________________
delete 1
    ____________________________________________________________
    Noted. I've removed this task:
    [D][ ] submit report (by: 11th of October 2019, 5:30PM)
    Now you have 3 tasks in the list.
    ____________________________________________________________
```

### Mark task as done: ```mark```

##### Command

```
mark <task_number>

Additional notes:
<task_number> must be positive integer (eg. 1, 2, 3) and must match with the
task number when the list command is called
```

##### Usage and Example Output

```
mark 2
    ____________________________________________________________
    Nice! I've marked this task as done:
    [T][X] borrow book
    ____________________________________________________________
```

### Unmark task: ```unmark```

##### Command

```
unmark <task_number>

Additional notes:
<task_number> must be positive integer (eg. 1, 2, 3) and must match with the
task number when the list command is called
```

##### Usage and Example Output

```
unmark 2
    ____________________________________________________________
    OK, I've marked this task as not done yet:
    [T][ ] borrow book
    ____________________________________________________________
```

### Exit: ```bye```

##### Command

```
bye
```

##### Usage and Example Output

```
bye
    ____________________________________________________________
    Bye. Hope to see you again soon!
    ____________________________________________________________
```