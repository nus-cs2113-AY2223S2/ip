import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        String greeting="____________________________________________________________\n"
                +" Hello! I'm Duke\n"
                +" What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(greeting);
        Scanner in= new Scanner(System.in);
        String[] list= new String[100];
        Task[] tasks = new Task[100];

        //if mark or unmarked
        boolean skip;
        int indexing=1;
        int i=0;

        while(true)
        {
            skip=false;
            String line=in.nextLine();
            //if bye
            if(line.equalsIgnoreCase("bye"))
            {
                break;
            }
            //getting index and line
            list[i]=line;
            String[] find_index = line.split(" ");

            //Task description
            String desc;

            desc=line;
            Task t = new Task(desc);


            if(line.toLowerCase().contains("unmark"))
            {
                indexing=Integer.parseInt(find_index[1]);
                tasks[indexing-1].markAsUnDone();



                skip=true;
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:\n"+
                        "["+tasks[indexing-1].getStatusIcon()+"] "+ list[indexing-1]);
                System.out.println("____________________________________________________________\n");
            }


            else if(line.toLowerCase().contains("mark"))
            {
                indexing=Integer.parseInt(find_index[1]);
                tasks[indexing-1].markAsDone();



                skip=true;
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:\n"+
                       "["+tasks[indexing-1].getStatusIcon()+"] "+ list[indexing-1]);
                System.out.println("____________________________________________________________\n");

            }

            else if(line.toLowerCase().contains("todo") || line.toLowerCase().contains("deadline") || line.toLowerCase().contains("event") )
            {


                if(line.toLowerCase().contains("todo"))
                {
                    String TodoTask =line.toLowerCase().substring(5,line.length());
                    list[i]=TodoTask;
                    tasks[i] = new Todo(TodoTask);

                }
                else if(line.toLowerCase().contains("deadline")) {
                    String[] ToSplitDeadline=line.split("/");
                    String DeadlineTask =ToSplitDeadline[0].toLowerCase().substring(9,ToSplitDeadline[0].length()-1);
                    list[i]=DeadlineTask;
                    tasks[i] = new Deadline(DeadlineTask, ToSplitDeadline[1].substring(3,ToSplitDeadline[1].length()));
                }
                else if(line.toLowerCase().contains("event"))
                {
                    String[] ToSplitEvent=line.split("/");
                    String EventTask =ToSplitEvent[0].toLowerCase().substring(6,ToSplitEvent[0].length());
                    list[i]=EventTask;
                    tasks[i] = new Event(EventTask, ToSplitEvent[1].substring(5,ToSplitEvent[1].length()),ToSplitEvent[2].substring(3,ToSplitEvent[2].length()));

                }
                skip=true;

                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:\n"+
                                "  "+ tasks[i]+ "\n"+
                        "Now you have "+ (i+1) +" tasks in the list.");

                System.out.println("____________________________________________________________\n");
                i+=1;


            }


            if(line.equalsIgnoreCase("list"))
            {
                skip=true;
                System.out.println("____________________________________________________________\n"+
                        "Here are the tasks in your list:");

                for(int m=0;m<i;m+=1)
                {
                    int index=m+1;
                    if(tasks[m]==null)
                    {
                        System.out.println(index + "." +"["+ t.getStatusIcon()+ "] "+list[m]);

                    }
                    else {
                        //the [X] or []
                        System.out.println(index + "." + tasks[m]);
                    }

                }
                System.out.println( "____________________________________________________________");

            }
            else {
                if(!skip) {
                    i += 1;
                    System.out.println("____________________________________________________________\n" + "added: " + line);
                    System.out.println("____________________________________________________________\n");
                }
            }

        }


            System.out.println("____________________________________________________________\n"
                    +" Bye. Hope to see you again soon!\n"
                    +"____________________________________________________________\n");


    }
}
