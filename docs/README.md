# User Guide for Duke Chatbot aka Chatty

## Features 

### 1. List, Add, Delete Tasks

Chatty is a chatbot that works like a todo list. You are able to add, delete and list tasks in an ordered list.

### 2. Mark Tasks As Complete/Incomplete

You are able keep track of your tasks by marking then as complete or incomplete.

### 3. Find Tasks

You are able to find tasks based on a keyword specified.

### 4. Automatically Saves And Loads Your Task List 

Chatty will automatically help you save your task list into a text file located under "./data/duke.txt". Whenever you launch Chatty, it will load your task list into the system.


## Usage

### 1. List Tasks

List down all your tasks in your task list.

Format: `list`

Expected Outcome:
```
list
==================================================================
 Here are the tasks in your list:
 1.[T][X] read book
 2.[D][X] return book (by: June 6th)
 3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
 4.[T][ ] buy bread
==================================================================
```

### 2. Add Todo Task

Add a todo task into your task list.

Format: `todo [task name]`

Example of usage:

- `todo buy book`

Expected Outcome:
```
todo buy book
==================================================================
 Roger. I've added this task:
  [T][ ] buy book
 You currently have 5 task(s) in the list.
==================================================================
```

### 3. Add Deadline Task

Add a deadline task into your task list.

Format: `deadline [task name] /by [end date/time]`

Example of usage:

- `deadline wash clothes /by 3rd Mar 11pm`

Expected Outcome:
```
deadline wash clothes /by 3rd Mar 11pm
==================================================================
 Roger. I've added this task:
  [D][ ] wash clothes (by: 3rd Mar 11pm)
 You currently have 6 task(s) in the list.
==================================================================
```

### 4. Add Event Task

Add an event task into your task list.

Format: `event [task name] /from [start date/time] /to [end date/time]`

Example of usage:

- `event bob's birthday celebration /from 13th Mar 6pm /to 11pm`

Expected Outcome:
```
event bob's birthday celebration /from 13th Mar 6pm /to 11pm
==================================================================
 Roger. I've added this task:
  [E][ ] bob's birthday celebration (from: 13th Mar 6pm to: 11pm)
 You currently have 7 task(s) in the list.
==================================================================
```

### 5. Mark Task As Completed

Mark a particular task as completed given a specified task list number.

Format: `mark [task number]`

Example of usage:

- `mark 4`

Expected Outcome:
```
mark 4
==================================================================
 Good Job! I've marked this task as completed:
  [T][X] buy bread
==================================================================
```

### 6. Mark Task As Incomplete

Mark a particular task as incomplete given a specified task list number.

Format: `unmark [task number]`

Example of usage:

- `unmark 1`

Expected Outcome:
```
unmark 1
==================================================================
 Noted, I have marked this task as incomplete:
  [T][ ] read book
==================================================================
```

### 7. Delete Task

Delete a particular task as incomplete given a specified task list number.

Format: `delete [task number]`

Example of usage:

- `delete 6`

Expected Outcome:
```
delete 6
==================================================================
 Roger. I've removed this task:
  [D][ ] wash clothes (by: 3rd Mar 11pm)
 You currently have 6 task(s) in the list.
==================================================================
```

### 8. Find Task

Find task given a keyword.

Format: `find [keyword]`

Example of usage:

- `find book`

Expected Outcome:
```
find book
==================================================================
 Here are the matching tasks in your list:
 1.[T][ ] read book
 2.[D][X] return book (by: June 6th)
 3.[T][ ] buy book
==================================================================
```

### 9. Exit

Exit the chatbot.

Format: `bye`

Expected Outcome:
```
bye
==================================================================
 Goodbye. Hope to see you again soon!
==================================================================
```





