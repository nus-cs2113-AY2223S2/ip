# User Guide
                  ____        _        
                 |  _ \\ _   _| | ___ 
                 | | | | | | | |/ /  \
                 | |_| | |_| |   <  /
                 |____/ \\__,_|_|\\_\\

Is a CLI Task Managing Application
## Quick Start
1. Ensure you have `Java 11` or above installed in your Computer.

2. Download the latest `.jar` from [here](https://github.com/nicholas132000/ip/releases/download/A-Release/INDIVIDUAL.PROJECT.jar).

3. Copy the file to the folder you want to use as the home folder for your `INDIVIDUAL.PROJECT.jar`.

4. Open a command terminal, ```cd``` into the folder you put the `jar` file in, and use the ```java -jar INDIVIDUAL.PROJECT.jar``` command to run the application.


# Main Features:
## 1. Add Task
- There 3 types of Tasks you can add
#### Todo
- Simply a task with its description
>`todo [description of todo task]`
>
> eg.
> 
> `todo CS2113 quiz`
#### Deadline
-  A Todo with a deadline timing to complete by
>`deadline [description of deadline task] / [datetime to complete by]`
>
> eg.
> 
> `deadline CS2113 quiz /Monday 3pm`
#### Event
-  A Task with a start and end time
>`event [description of event task] /[start datetime] /[end datetime]`
>
> eg.
>
> `event CS2113 FINALS /Monday 3pm /Monday 5pm`

## 2. Delete Task
- Simply delete any task you want out of your task list
>`delete [task no.]`
> 
> eg.
> 
> `delete 2`
> 
> Results in:
> 
> `Noted. I've removed this task:`
> 
> `[D][ ] CS2113 quiz  (by: Monday 3pm)`


## 3. Exit Program
- Exit from the program by saying bye to Duke
>`bye`
>
>
> Results in:
>
> `Bye. Hope to see you again!`


## Additional Features
- Mark task
>`mark [task no.]`
>
> eg.
> 
> `mark 3`
> 
> Results in:
> 
>`Nice! I've marked this task as done:`
> 
>`[D][X] CS2113 quiz (by: 3pm)` 
- Unmark task
>`unmark [task no.]`
>
> eg.
>
> `unmark 3`
>
> Results in:
>
>`OK, I've marked this task as not done yet:`
>
>`[D][ ] CS2113 quiz (by: 3pm)`
- List tasks
>`list`
>
> Results in:
> 
> `Here are the tasks in your list:`
> 
> `1.[T][ ] CS2113 quiz`
> 
> `2.[D][ ] CS2113 quiz  (by: Monday 3pm)`
> 
> `3.[E][ ] CS2113 FINALS  (from: Monday 3pm  to: Monday 5pm)`
- Find tasks by keyword
>`find [keyword]`
> 
> eg.
> 
> `find quiz`
> 
> Results in:
> 
> `Here are the tasks in your list:`
> 
> `1.[T][ ] CS2113 quiz`
> 
> `2.[D][ ] CS2113 quiz  (by: Monday 3pm)`

