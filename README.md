# DukeNUS project template
```
    Hello from
     ____        _          __    _ _   _ ______
    |  _ \ _   _| | _____  |   \ | | | | | _____|
    | | | | | | | |/ / _ \ | |\ \| | | | |_____ |
    | |_| | |_| |   <  __/ | | \ \ | |_| |____| | 
    |____/ \__,_|_|\_\___| |_|  \__|_____|______|
```
This is a individual Java project for CS2113 Software Engineering and Object-Oriented Programming. It's named after the NUS medical school for no good reason.
This simple command line interface application will allow you to keep track of your tasks, set due dates, mark them as done, remove them, find them, and even store them between sessions.

## How to use the program
### Running the program
Execute the .jar file. This will open the command line interface where you will be greeted by a welcome message.
### Creating tasks
DukeNUS allows you to create 3 types of tasks by typing them into the interface:
1. `todo [description]`: a simple task with only a description.
2. `deadline [description] /by [duedate in yyyy-MM-dd format]`: a task with a description and a `/by` date.
3. `event [description] /from [start date in yyyy-MM-dd format] /to [end date in yyyy-MM-dd format]` a task with a description, a `/from` date, and a `/to` date.
The description for the tasks will be delimited by a space after the type of task is specified `todo`, `deadline`, `event`, and afterwards, the additional arguments will be passed after specifying the parameter with a slash in front. For example:

`todo`:
```
todo Watch The Amazing World of Gumball
```
`deadline`:
```
deadline Contribute to open-source software /by 2023-04-15
```
`event`:
```
event Super Smash Con 2022 /from 2022-06-01 /to 2022-06-08
```
All tasks created are initially marked as unfinished and the resultant task is shown:
```
    Got it. I've added this task:
    [T][ ] Watch The Amazing World of Gumball
    You now have 1 tasks in the list.
```
### Listing tasks
All tasks you have created can be listed with the `list` command, and all tasks will be shown with their completed status:
```
    Here are the tasks in your list:
    1.[T][ ] Watch The Amazing World of Gumball
    2.[D][ ] Contribute to open-source software (by: 2023-04-15)
    3.[E][ ] Super Smash Con 2022 (from: 2022-06-01 > to: 2022-06-08)
```
### Marking tasks
Based on the number of the task in the list, you can mark the specific task you deem completed:
```
mark 2
```
Result:
```
    Congratulations! You have finished this task:
    [D][X] Contribute to open-source software (by: 2023-04-15)
```
Similarly, you can unmark a task if it was actually not completed:
```
unmark 2
```
Result:
```
    The following task is now marked as undone:
    [D][ ] Contribute to open-source software (by: 2023-04-15)
```
### Deleting tasks
Similarly, tasks can also be removed from the list by specifying the task number:
```
delete 3
```
Result:
```
    Noted. I've removed this task:
    [E][ ] Super Smash Con 2022 (from: 2022-06-01 > to: 2022-06-08)
```
### Finding tasks
Suppose you have many tasks in your list and you'd like to find a specific task given part of its description. You can use `find` to locate the task:
```
find Gumball
```
Result:
```
    Here are the matching tasks in your list:
    1.[T][ ] Watch The Amazing World of Gumball
```
Note however that the search is case-sensitive.
### Terminating the program and saving your tasks
You may formally end your session with DukeNUS Medical School by saying `bye` to it:
```
bye
```
Result:
```
    Bye. Hope to see you again soon!s
```
Terminating the program will save the list of tasks you had into a .txt file in a `data` folder from where you ran the executable .jar file. Running the program again, you will notice that the tasks have been loaded when you check with `list`:
```
    Here are the tasks in your list:
    1.[T][ ] Watch The Amazing World of Gumball
    2.[D][ ] Contribute to open-source software (by: 2023-04-15)
```