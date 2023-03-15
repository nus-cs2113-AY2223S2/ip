# User Guide

## Duke
Duke is a program that helps you create your very own to-do list. There are 3 categories of
classification for each task: todo, event and deadline.

## Commands
### todo (task)
   Use this to add a task to your list without any specified timeframe. The task will be labelled with a
   "[T]" in your list.
   eg. todo stuff
### deadline (task) /by (time)
   For adding tasks with a specific deadline. The task will be labelled with a "[D]" in your list.
   eg. deadline stuff /by 2023-08-05 14:00
### event (task) /from (start time) /to (end time)
   For adding tasks that last over a certain time period. The task will be labelled with a "[E]" in your
   list.
   eg. deadline stuff /from 2023-08-05 14:00 /to 2023-08-05 16:00
### mark (i), unmark (i)
   Marks the ith task in your list as completed or uncompleted. Completed tasks are labelled with "[X]"
   in the list while uncompleted tasks are labelled with "[ ]". Newly added tasks are marked as uncompleted
   by default.
   eg. mark 1
### delete (i)
   Deletes the ith task from your list.
   eg. delete 1
### list
   Lists out all tasks in your list.
### find (string)
   Lists out all tasks in your list that contain the given string in their description.
   eg. find stuff
### dated (date/time)
   Lists out all tasks in your list that coincide with the given date/time. Naturally, this only works
   on deadline and event tasks.
   eg. find 2023-08-05 14:00
### bye
   Closes the program. Don't worry if you close the window without using this command, Duke will still
   remember all the tasks in your list prior to closing. You can pull them up again next time you open
   Duke.

## Notes
   For any command that involves time, any generally accepted date and time format is fine. That is to say:
   YYYY-MM-DD or DD-MM-YYYY or MM-DD-YYYY
   02:00 PM or 14:00, 08:00 AM or 08:00
   are all acceptable, just follow the format given here.
   