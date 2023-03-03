# Duke Task Manager: User Guide
Duke is a Personal Assistant Chatbot that aids the user in keeping track of 
their tasks.

## Quick Start
1. Ensure that Java 11 is installed in your computer, if not proceed to download from the link:
   https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html
2. Verify that you have a copy of "ip.jar" in your computer
3. After that, locate folder containing ip.jar in your computer, right-click it, and choose "Open in Terminal".
4. In the terminal, type: java -jar ip.jar, and press enter. The Duke Task Manager will start up.

## Features Overview
1. Startup
2. Add Todo Task
3. Add Deadline Task
4. Add Event Task
5. List out the Tasks 
6. Marking Task Done
7. Marking Task Undone
8. Deleting Task from list
9. Finding Task/s from Keyword
10. Exit

### Feature - Startup

Duke will load the saved tasks from 'tasks.txt', or create this file if it does not exist.

Expected output of startup: `On First Startup`
```
____________________________________________________________
Hello! I'm Duke
____________________________________________________________
____________________________________________________________
What can I do for you?
____________________________________________________________
```

Expected output of startup: `On Subsequent Startup - If no saved task`
```
____________________________________________________________
Hello! I'm Duke
____________________________________________________________
____________________________________________________________
No saved task recorded
____________________________________________________________
____________________________________________________________
What can I do for you?
____________________________________________________________
```

Expected output of startup: `On Subsequent Startup - If there is are saved tasks`
```
____________________________________________________________
Hello! I'm Duke
____________________________________________________________
____________________________________________________________
Here are the tasks in your save list:
1.[T][ ] study CS2113
____________________________________________________________
____________________________________________________________
What can I do for you?
____________________________________________________________
```
### Note: The command inputs are not case-sensitive
### Feature - Add Todo Task

### `command` - todo
Format: [command] [task description]

Sample input:
```
todo study CS2113
```
Expected output:
```
____________________________________________________________
Roger! The Todo task has been added:
[T][ ] study CS2113
Now you have 1 in the list
____________________________________________________________
```

### Feature - Add DeadLine Task

### `command` - deadline
Format: [command] [task description]

Sample input: 
```
deadline submit ip /by 2359
```
Expected output:
```
____________________________________________________________
Roger! The Deadline task has been added:
    [D][ ] submit ip (by: 2359)
Now you have 2 in the list
____________________________________________________________
```
### Feature - Add Event Task

### `command` - event
Format: [command] [task description]

Sample input:
```
event CS2113 Midterms /from 9am /to 10am
```

Expected output:
```
____________________________________________________________
Roger! The Deadline task has been added:
    [E][ ] CS2113 Midterms (from: 9am to: 10am)
Now you have 3 in the list
______________________________________________s______________
```

### Feature - List out the Tasks

### `command` - list
Format: [command]

Sample input:
```
list
```
Expected output:
```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] study CS2113
2.[D][ ] submit ip (by: 2359)
3.[E][ ] CS2113 Midterms (from: 9am to: 10am)
____________________________________________________________
```

### Feature - Marking Task Done

### `command` - mark
Format: [command] [task index]

Sample input:
```
mark 1
```
Expected output:
```
____________________________________________________________
Awesome! I've mark this task as done:
[X] study CS2113
____________________________________________________________
```

### Feature - Marking Task Undone

### `command` - unmark
Format: [command] [task index]

Sample input:
```
unmark 1
```
Expected output:
```
____________________________________________________________
What!?!? OK, I've marked this task as not done yet:
[ ] study CS2113
____________________________________________________________
```

### Feature - Deleting Task from list

### `command` - delete
Format: [command] [task index]

Sample input:
```
delete 1
```
Expected output:
```
____________________________________________________________
Noted. I've removed this task:
  [T][ ] study CS2113
Now you have 2 tasks in the list
____________________________________________________________
```

### Feature - Finding Task/s from Keyword

### `command` - find
Format: [command] [keyword]

Sample input:
```
find CS2113
```
Expected output:
```
____________________________________________________________
Here are the matching tasks in your save list:
1.[E][ ] CS2113 Midterms (from: 9am to: 10am)
____________________________________________________________
```

### Feature - Exit
This will close Duke.
### `command` - todo
Format: [command] [task description]

Sample input:
```
bye
```
Expected output:
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```


