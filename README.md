# Duke - User Guide

Duke is a personal task manager designed to help you stay organized and on top of your to-do list. With its automated features, Duke streamlines task management and can significantly improve your productivity.

## Usage

### todo {Task} - Add a Todo Task
Example: `todo study`   
Expected outcome:
   ```
   Got it. I've added this task:
   [T][ ]  study
   ```
   
### deadline {Task} /by {Date/Time} - Add a Deadline Task
Example: `deadline return book /by Sun`   
Expected outcome:
   ```
   Got it. I've added this task:
   [D][ ]  return book (by Sun)
   ```

### event {Task} /from {Date/Time} /to {Date/Time} - Add a Event Task
Example: `event party /from Fri 6pm /to 8pm`   
Expected outcome:
   ```
   Got it. I've added this task:
   [E][ ]  party (from Fri 6pm to 8pm)
   ```
   
### mark {Task Index} - Mark Task as Done
Example: `mark 1`   
Expected outcome:
   ```
   Nice! I've marked this task as done:
   [T][X]  study
   ```
   
### unmark {Task Index} - Mark Task as Not Done
Example: `unmark 2`   
Expected outcome:
   ```
   OK, I've marked this task as not done yet:
   [D][ ]  return book (by Sun)
   ```
   
### list - List All Tasks
Example: `list`   
Expected outcome:
   ```
   1. [T][ ]       study
   2. [D][ ]       return book (by Sun)
   3. [E][ ]       party (from Fri 6pm to 8pm)
   ```
   
### find {Keyword} - Find Task(s) Containing Certain Keyword
Example: `find book`   
Expected outcome:
   ```
   Here are the matching tasks in your list:
   1. [T][ ]       read book
   2. [D][ ]       return book (by Sun)
   ```
   
### delete {Task Index} - Delete Task
Example: `delete 4`   
Expected outcome:
   ```
   Noted. I've removed this task:
   [T][ ]  read book
   ```
   
### bye - Leave Program
Example: `bye`   
Expected outcome:
   ```
   Bye. Hope to see you again soon.
   ```
   
