# User Guide

## Features 

### Feature- Add Tasks

With the command: "todo Laundry"
The task "Laundry" is added to a task list.

Expected Output: 

```
todo Laundry
____________________________________________________________
Got it. I've added this task:
  [T][ ] Laundry
Now you have 1 tasks in the list.
____________________________________________________________
```

With the command: "deadline return book /by Sunday"
The task "return book" is added to a task list with the deadline "Sunday". 

Expected Output: 

```
deadline return book /by Sunday 
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________________
```

With the command: "event project meeting /from Mon 2pm /to 4pm"
The task "project meeting" is added to a task list with the beginning of the event "Mon 2pm" and the end as "4pm".

Expected Output: 

```
event project meeting /from Mon 2pm /to 4pm
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting  (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
```

### Feature- Delete Tasks

With the command: "delete 1" 
The first task is deleted. 

Expected Outcome:

```
delete 1
____________________________________________________________
 Noted. I've removed this task:
  [T][ ] Laundry
Now you have 2 tasks in the list.
____________________________________________________________
```

### Feature- Mark/Unmark Tasks

With the command: "mark 1" 
The first task is marked. 

Expected Outcome:

```
mark 1
____________________________________________________________
Nice! I've marked this task as done:
[D][X] return book (by: Sunday)
____________________________________________________________
```

With the command: "unmark 1" 
The first task is unmarked. 

Expected Outcome:

```
unmark 1
____________________________________________________________
OK, I've marked this task as not done yet:
[D][ ] return book (by: Sunday)
____________________________________________________________
```

### Feature- Find Tasks with a keyword

With the command: "find book" 
Tasks with the word "book" will be listed. 

Expected Outcome:

```
find book
____________________________________________________________
 Here are the matching tasks in your list: 
1.[D][ ] return book (by: Sunday)
____________________________________________________________
```
### Feature- Display Task list

With the command: "list" 
The task list will be displayed

Expected Outcome:

```
list
____________________________________________________________
Here are the tasks in your list:
1.[D][ ] return book (by: Sunday)
2.[E][ ] project meeting  (from: Mon 2pm to: 4pm)
____________________________________________________________
```

### Feature- To end the program

With the command: "bye"
The program will end. 

Expected Outcome:

```
bye
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```


