# Duke: Your Personal Agenda Assistant  📑

Duke is a Java personal agenda keeper. He is smart enough to assist in your daily activity planning. Below are his functionalities:

1. [Add Task](#add)
    1. [Todo](#t) 
    2. [Event](#e)
    3. [Deadline](#d)    
2. [Mark/Unmark Task](#mum)
    1. [Mark Task](#mt)
    2. [Unmark Task](#u)
3. [Delete Task](#dt)
4. [List Task](#lt)
5. [Find Task](#ft)
6. [Exit](#end)
--------------------------------------------------------------------
# NOTE:
* Follow the input format as mentioned below to avoid errors.
--------------------------------------------------------------------

# 1. Add Task <a name = "add"></a>

You can add 3 types of tasks, Todos, Events and Deadlines.

## 1.1. Todo <a name = "t"></a>
* COMMAND: todo {task}


Input:

```todo return book```

Output:
```
As per requested Sire, this task has been added to your calendar.
[T][ ] return book
You now have 7 items left
```


## 1.2. Event <a name = "e"></a>
* COMMAND: event {task} \from {string} \to {string}

Input:

```event meeting with friend /from 4pm /to 6pm```

Output:
```
As per requested Sire, this task has been added to your calendar.
[E][ ] meeting with friend (from: 4pm to: 6pm)
You now have 8 items left
```

## 1.3. Deadline <a name = "d"></a>
* Command: deadline {task} \by {string}

Input:

```deadline finish CS2113 assignment/by Sunday```

Output:
```
As per requested Sire, this task has been added to your calendar.
[D][ ] finish CS2113 assignmen (by: Sunday)
You now have 9 items left
```


# 2. Mark/Unmark Task <a name = "mum"></a>

Mark the task at the index keyed in by users as done, or unmark it as unfinished.
For index of all current tasks, please see [List Task](#lt).

## 2.1. Mark Task <a name="mt"></a>
* COMMAND: mark {enter task_index}

Input:

```mark 1```

Output:

```
Sir, your task has been marked as completed.
[T][X] Finish CS2113 iP
```

## 2.2. Unmark Task <a name="u"></a>
* COMMAND: unmark {enter task_index}

Input:

```unmark 2```

Output:

``` 
Sir, your task has been unmarked as requested.
[T][ ] sleep
```

# 3. Delete Task <a name = "dt"></a>

Delete task at keyed in index as follows:

* COMMAND: delete {enter task_index}

Input:

```delete 1```

Output:

```
Sire, I have removed this task from your schedule
[T][X] Finish CS2113 iP
You now have 8 items left
```

# 4. List Task <a name = "lt"></a>

List all tasks in the list as follows:

* COMMAND: list

Input:

```list```

Output:

```
Your current list of items as requested, sir.
1.[T][ ] sleep
2.[E][ ] project meeting (from: Mon 2pm to: 4pm)
3.[T][ ] return book
4.[E][ ] meeting with friend (from: 4pm to: 6pm)
```

# 5. Find Task <a name = "ft"></a>

Find the desired tasks by keying in a term they contain as follows:

* COMMAND: find {keyword}

Input:

```find friend```

Output:
```
Here are the matching items Sire:
[E][ ] meeting with friend (from: 4pm to: 6pm)
```


# 6. Exit <a name = "end"></a>

Exit the program as follows:

Input:

```bye```

Output:
```
Glad I could be of help!
```

# End of user guide
