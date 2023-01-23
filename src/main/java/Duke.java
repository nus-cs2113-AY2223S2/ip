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
        Scanner in=new Scanner(System.in);
        String[] list= new String[100];
        String[] result= new String[100];
        //if mark or unmarked
        boolean mark=false;
        boolean skip;

        int indexing=1;

        int i=0;
        while(true)
        {
            skip=false;
            String line=in.nextLine();
            if(line.equalsIgnoreCase("bye"))
            {
                break;
            }
            list[i]=line;
            String[] to_get = line.split(" ");
            if(line.toLowerCase().contains("un"))
            {
                mark=false;

                indexing=Integer.parseInt(to_get[1]);

            }
            else if(line.toLowerCase().contains("mark"))
            {
                mark=true;

                indexing=Integer.parseInt(to_get[1]);

            }


            String desc;
            result[i]=" ";

            if(mark)
            {
                 desc="mark";
            }
            else
            {
                 desc="unmarked";
            }
            Task t = new Task(desc);
            if(mark)
            {
                result[indexing-1]=t.markAsDone(true);

            }
            else {
                result[indexing-1]=t.markAsUnDone(false);
            }

            if(line.toLowerCase().contains("un"))
            {
                skip=true;
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:\n"+
                        "["+result[indexing-1]+"] "+ list[indexing-1]);
                System.out.println("____________________________________________________________\n");
            }

            else if(line.toLowerCase().contains("mark"))
            {
                skip=true;
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:\n"+
                       "["+result[indexing-1]+"] "+ list[indexing-1]);
                System.out.println("____________________________________________________________\n");

            }



            //added ^

            if(line.equalsIgnoreCase("list"))
            {
                skip=true;
                System.out.println("____________________________________________________________\n"+
                        "Here are the tasks in your list:");


                for(int m=0;m<i;m+=1)
                {
                    int index=m+1;
                    //the [X] or []
                    System.out.println(index + "."+"[" + result[m] + "] " + list[m] );
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
