# Arsdorint chatbot

This is Arsdorint chatbot, a member of Arsdorint Team, and an adaptation application of CS2113 Project Duke) 

## User Guide

1. To download the application, click [here](https://github.com/arsdorintbp2003/ip/releases/tag/A-Released).
2. Copy the file path after your download. (ie: C:\Users\YourPC\GitHub\ip_jar)
3. Open your terminal, if the current path is not where you keep the file (ie: C:\Users\YourPC), just type `cd` + the remaining path (ie: 'cd GitHub\ip_jar`) to access the file path.
4. Initializing the application by typing `java -jar ip.jar`, and enjoy using the chatbot. 
5. The chatbot contains the following feature
 + List
 + Todo
 + Deadline
 + Event
 + Mark
 + Unmark
 + Date
 + Find
 + Bye

## Feature - List
- This function will list all the tasks inputed by the user.
- Example: 

```
>>> list
Here are the tasks in your list:

____________________________________________________________
____________________________LIST____________________________
1.[T][ ] midterm
	Now you have 1 task in the list.
____________________________________________________________
```

## Feature - Todo
- This function will add a task that the user want to do, with a tickbox to mark if the task is done yet or not.
- Example:

```
>>> todo midterm
____________________________________________________________

Got it. I've added this task:
	
[T][ ] midterm
	Now you have 1 task in the list.
____________________________________________________________
```

## Feature - Deadline
- This function will add a deadline that the user need to complete, with a tickbox to mark if the deadline is finished or not.
- Example:

```
>>> deadline midterm /by tommorrow
____________________________________________________________

Got it. I've added this task:
	
[D][ ] midterm	(by tommorrow)
	Now you have 2 tasks in the list.
____________________________________________________________
```

or 

```
>>> deadline midterm /2023-03-04
____________________________________________________________

Got it. I've added this task:
	
[D][ ] midterm	(4 Mar 2023)
	Now you have 4 tasks in the list.
____________________________________________________________
```

## Feature - Event
- This function will add an event that the user need to attend, with a tickbox to mark if the user have attended or not.
- Example:

```
>>> event Open House /on Sunday
____________________________________________________________

Got it. I've added this task:
	
[E][ ] Open House	(on Sunday)
	Now you have 5 tasks in the list.
____________________________________________________________
```

or

```
>>> event NUS football match /2023-03-05
____________________________________________________________

Got it. I've added this task:
	
[E][ ] NUS football match	(5 Mar 2023)
	Now you have 6 tasks in the list.
____________________________________________________________
```

## Feature - Mark
- This function will mark a task as done by tick a "X" into the tickbox.
- Example:

```
>>> mark 2
____________________________________________________________

Got it. I've marked this task as done:
	
[D][X] midterm	(by tommorrow)
	Now you have 5 unmarked tasks in the list.
____________________________________________________________
```

## Feature - Unmark
- This function will mark a task as not done yet by leaving the tickbox empty.
- Example: 

```
>>> unmark 2
____________________________________________________________

Got it. I've marked this task as not done yet:
	
[D][ ] midterm	(by tommorrow)
	Now you have 0 marked tasks in the list.
____________________________________________________________
```

## Feature - Date
- This function will find if a date is in the task list yet. The date should be in the format of YYYY-MM-DD with YYYY is the year, MM is the month and DD is the day.
- Example:

```
>>> date 2022-03-04
____________________________________________________________
1 task occurs on this date:
[D][ ] Open House       (4 Mar 2022)
```

## Feature - Find
- This function will find if the task the user want to search is in the list yet, and list all task with that name
- Example:

```
>>> find Open House
____________________________________________________________
Here are the matching tasks in your list, with 1 task as followed:
[E][ ] Open House	(on Sunday)

____________________________________________________________
```

## Feature - Bye
 - This function will help the chatbot to say bye to the user, and end up the conversation.
 - Example: 
 
 ```
 >>> bye
____________________________________________________________
 Bye. Hope to see you again soon!


____________________________________________________________
```

### That's all about Arsdorint chatbot! Hope you enjoy our product, and leave some comments to help us improve the chatbot! Thank you so much!
   ```
   Hello from
        ___                         _                                 _
       / _ \     _____   _____  ___| |   ___    _____   _   _____   _| |_
      / /_\ \   /  ___| /  __/ /  _  |  / _ \  /  ___| | | |  _  \ |_   _|
     / _____ \  | /    __\ \   | |_| | | |_| | | /     | | | | | |   | |
    /_/     \_\ |_|   /____/   \_____|  \___/  |_|     |_| |_| |_|   |_|

   ```
