# Duke Individual Project

This is an organiser to help you keep track of your tasks. 

## Using Duke

Prerequisites: JDK 11, update Intellij to the most recent version.

On opening, Duke should greet you with this
```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
```

## Loading Save File

The default path for the save file has been defined in ```Duke.java```  

On opening, Duke will read and parse the contents in the save file to initialise your task list.  
*If there are any errors with the data in the save file, a warning will be shown after the welcome message.*  

Each time the task list is updated, Duke will automatically overwrite the save file to the latest version.  
There is no need to save the task list before exiting the program. 

## List of Commands
- Enter ```help``` to see a list of commands that are available in Duke
- Enter ```list``` to see the complete list of all tasks
- Enter ```todo [task]``` to add a task to the list
- Enter ```deadline [task] /by [date]``` to add a task that need to be done before a specific date/time
- Enter ```event [task] /from [date] /to [date]``` to add a task that starts and ends at a specific date/time
- Enter ```mark [idx]``` based on the task's index in the list to mark it as done 
- Enter ```unmark [idx]``` based on the task's index in the list to mark it as not done 
- Enter ```delete [idx]``` based on the task's index in the list to remove the task from your list
- Enter ```find [keyword]``` to see all tasks containing the keyword
- Enter ```date [yyyy-MM-dd]``` to see all tasks occurring on that date
- Enter ```bye``` to exit the program  

> #### Using the ```date``` function
> The ```date``` function will only work for Deadlines and Events with ```[date]``` entered as ```[yyyy-MM-ddTHH:mm]```       
> eg. ```deadline read book /by 2023-10-30T23:59``` sets a deadline for Oct 20 2023, 11:59PM
