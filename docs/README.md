# The Duke Project

This is my version of the Duke project, done as part of the CS2113 module in NUS AY2022/2023.

## Start Guide
Below is a brief guide as to how to download and/or modify the Duke project for your own use.

The Duke project is optimised to run in a Command Line environment.

Download the latest JAR file under releases.\
Move the JAR file to a location of your choice.\
Run the Duke program via double-clicking on the downloaded file, or by copying the file path and entering the following
command into your terminal.

Example of the command to run Duke from terminal:
~~~
java -jar "C:\Users\Albert\Desktop\CS2113.jar"
~~~

# User Guide
Upon starting the Duke program, you will be greeted with a welcome message, as well as the commands Duke can process, 
after which you may begin to add tasks to Duke.

Welcome Message:
~~~
     ____        _        
    |  _ \ _   _| | _____ 
    | | | | | | | |/ / _ \
    | |_| | |_| |   <  __/
    |____/ \__,_|_|\_\___|

    ____________________________________
    Hello! I'm Duke
    What can I do for you?

    Here are some commands I can understand: 

    todo [task description]                          --> to add a todo task
    deadline [task description] /by [due date]       --> to add a deadline
    event [task description] /from [when] /to [when] --> to add an event
    mark [task number]                               --> to mark a task as done
    unmark [task number]                             --> to unmark a task (i.e. not done)
    list                                             --> to show the lists of tasks
    delete [task number]                             --> to delete a task
    find [keyword]                                   --> to find tasks that contain a keyword
    bye                                              --> to exit the program

    ____________________________________
~~~

## Creating Tasks
There are 3 types of tasks that you can currently create.

1. ``todo [description]``: creates a task that will be classified as todo
2. ``deadline [description] /by [due date in YYYY-MM-DD format]``: creates a task that will be classified as a deadline,
with a due date assigned to the task.
3. ``event [description] /from [start] /to [end]``: creates a task that will be classified as an event, with a start and
end time.

All tasks will be marked with T, D and E respectively.

### Examples
We shall use the tasks below as examples throughout this User Guide.

``todo``:
~~~
todo read book
    ____________________________________
     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 tasks in the list
    ____________________________________
~~~

``deadline``:
~~~
deadline Coding assignment /by 2023-04-25 
    ____________________________________
     Got it. I've added this task:
       [D][ ] Coding assignment (by:Apr 25 2023)
     Now you have 2 tasks in the list
    ____________________________________
~~~

``event``:
~~~
event Engineering Principles lecture /from Tuesday 2pm /to Tuesday 4pm
    ____________________________________
     Got it. I've added this task:
       [E][ ] Engineering Principles lecture(from: Tuesday 2pm to:Tuesday 4pm)
     Now you have 3 tasks in the list
    ____________________________________
~~~

## Marking Tasks
All tasks can be marked by entering ``mark [task number]``.\
Conversely, they can be unmarked by entering ``unmark [task number]``.

Using the 3 tasks entered above as examples...

### Example
~~~
mark 1
    ____________________________________
     Nice! I've marked this task as done:
       [T][X] read book
    ____________________________________
mark 2
    ____________________________________
     Nice! I've marked this task as done:
       [D][X] Coding assignment (by:Apr 25 2023)
    ____________________________________
unmark 2
    ____________________________________
    OK, I've marked this task as not done yet:
       [D][ ] Coding assignment (by:Apr 25 2023)
    ____________________________________
~~~

## Viewing your Tasks
All tasks can be viewed by entering ``list`` into the terminal.\
All tasks will contain their classification label (T, D or E) and their marked status (marked or unmarked).

### Example
~~~
list
    ____________________________________
     Here are the tasks in your list:
     1.[T][X] read book
     2.[D][ ] Coding assignment (by:Apr 25 2023)
     3.[E][ ] Engineering Principles lecture(from: Tuesday 2pm to:Tuesday 4pm)
    ____________________________________
~~~

## Deleting your Tasks
Any task can be deleted by entering ``delete [task number]`` into the terminal.\

### Example
A ``list`` command is entered after deleting 2 tasks to show what are the tasks left.
~~~
delete 1
    ____________________________________
     Noted. I've removed this task:
       [T][X] read book
     Now you have 2 tasks in the list
    ____________________________________
delete 2
    ____________________________________
     Noted. I've removed this task:
       [E][ ] Engineering Principles lecture(from: Tuesday 2pm to:Tuesday 4pm)
     Now you have 1 tasks in the list
    ____________________________________
list
    ____________________________________
     Here are the tasks in your list:
     1.[D][ ] Coding assignment (by:Apr 25 2023)
    ____________________________________
~~~
Assume that we have added the deleted tasks back into Duke.

## Finding a Task
Any task can be found by entering ``find [keyword]`` into the terminal.\
Duke will return any task that contains this ``[keyword]``, regardless of case.

### Example
~~~
find lecture
    ____________________________________
     Here are the matching tasks in your list:
     1.[E][ ] Engineering Principles lecture(from: Tuesday 2pm to:Tuesday 4pm)
    ____________________________________
find book
    ____________________________________
     Here are the matching tasks in your list:
     1.[T][ ] read book
    ____________________________________
~~~

## In case you forget the commands
Upon entering a command not found in this User Guide, Duke will prompt you with a list of commands available to you.

### Example
We use an example of entering ``store read book``, posing as a user pretending to "store" a task of reading a book.
~~~
store read book
    ____________________________________
    OOPS!!! I'm sorry, but I don't know what that means :-(
    I only understand the following commands unfortunately: 

    todo [task description]                          --> to add a todo task
    deadline [task description] /by [due date]       --> to add a deadline
    event [task description] /from [when] /to [when] --> to add an event
    mark [task number]                               --> to mark a task as done
    unmark [task number]                             --> to unmark a task (i.e. not done)
    list                                             --> to show the lists of tasks
    find [keyword]                                   --> to find tasks containing the keyword
    bye                                              --> to exit the program

    THANKS!
    ____________________________________
~~~
Thus, in the event of forgetting what commands are available, Duke will prompt you with the available commands.

## Saving your Tasks and Terminating the Programme
Once you are done using Duke, and would like to save your current tasks, enter ``bye`` into the terminal.

### Example
~~~
bye
    ____________________________________
    Bye. Hope to see you again soon!
    ____________________________________
~~~

Upon restarting the Duke programme, the saved tasks will be loaded by entering ``list`` into the terminal.
~~~
     ____        _        
    |  _ \ _   _| | _____ 
    | | | | | | | |/ / _ \
    | |_| | |_| |   <  __/
    |____/ \__,_|_|\_\___|

    ____________________________________
    Hello! I'm Duke
    What can I do for you?

    Here are some commands I can understand: 

    todo [task description]                          --> to add a todo task
    deadline [task description] /by [due date]       --> to add a deadline
    event [task description] /from [when] /to [when] --> to add an event
    mark [task number]                               --> to mark a task as done
    unmark [task number]                             --> to unmark a task (i.e. not done)
    list                                             --> to show the lists of tasks
    delete [task number]                             --> to delete a task
    find [keyword]                                   --> to find tasks that contain a keyword
    bye                                              --> to exit the program

    ____________________________________
list
    ____________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[D][ ] Coding assignment (by:Apr 25 2023)
     3.[E][ ] Engineering Principles lecture(from: Tuesday to:2pm Tuesday 4pm)
    ____________________________________
~~~