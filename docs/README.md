# User Guide

## `Introduction`

This is Duke, a chatbot. He is here to assist you in compiling a task list that you can reference to.

## `Download`

1) Make sure that your computer supports Java 11 or above.  
2) Download this [jar file](https://github.com/geraldkoh4/ip/releases/download/A-Release/Duke.jar).  
3) Copy the file path.  
4) Go into your console and enter `"java -jar {file path}"`.   

## `Features` 

1) Add a Task  
2) List all Tasks  
3) Mark/Unmark a Task  
4) Find a Task  
5) Delete a Task  
6) Exit  

### 1.1) Add a Task (To Do)

Add a "To Do" Task to the list. 

**`Format`**: todo {description} 

*Sample Input Command*: **`todo Fly a Kite`**

*Output*:
```
Got it. I've added this task:
[T][ ] Fly a Kite
Now you have 1 tasks in the list.
```

### 1.2) Add a Task (Deadline)

Add a "Deadline" Task to the list.

**`Format`**: deadline {description} /by {time/date}

*Sample Input Command*: **`deadline Finish My Homework /by Today`**

*Output*:
```
Got it. I've added this task:
[D][ ] Finish My Homework (by: Today)
Now you have 2 tasks in the list.
```

### 1.3) Add a Task (Event)

Add a "Event" Task to the list.

**`Format`**: event {description} /from {time/date} /to {time/date}

*Sample Input Command*: **`event Play Video Games /from Today 12pm /to Today Night`**

*Output*:
```
Got it. I've added this task:
[E][ ] Play Video Games (from: Today 12pm to: Today Night)
Now you have 3 tasks in the list.
```

### 2) List all Tasks

Lists every Task you currently have in your list.

**`Format`**: list

*Output*:
```
1) [T][ ] Fly a Kite
2) [D][ ] Finish My Homework (by: Today)
3) [E][ ] Play Video Games (from: Today 12pm to: Today Night)
```

### 3) Mark/Unmark a Task

Mark or unmark a Task to note if you have completed the Task through the tasknumber.

**`Format`**: mark {tasknumber}

*Sample Input Command*: **`mark 1`**

*Output*:
```
Done!
[T][X] Fly a Kite
```

### 4) Find a Task

Find a task through a search criteria.

**`Format`**: find {search criteria}

*Sample Input Command*: **`find Play`**

*Output*:
```
3) [E][ ] Play Video Games (from: Today 12pm to: Today Night)
Done!
```

### 5) Delete a Task

Delete a task that you no longer need through the tasknumber.

**`Format`**: delete {tasknumber}

*Sample Input Command*: **`delete 1`**

*Output*:
```
Got it. I've removed this task
[T][X] Fly a Kite
Now you have 2 tasks in the list.
```

### 6) Exit

Exit from the program.

**`Format`**: bye

*Output*:
```
That's all from me! Goodbye!
```

# Note

## Test - Echo

Echo is a command that you can use to test if the chatbot is responding!

**`Format`**: echo {description}

*Sample Input Command*: **`echo 1`**

*Output*:
```
1
```

## Flags

Do not use the character '/'. It is solely reserved for flags.
Example of the flags include:  
Deadline by flag: "/by "  
Event from flag: "/from "  
Event to flag: "/to "  

Thank you!
