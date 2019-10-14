/*
@author Nathaniel Fishel
@description This is the main class for the donut shop simulation. The program will first use a random number generator to calculate how many customers
are coming into the shop. Then will see if there are any available servers/employees.
then        1. if a server is available it will take the customer out of the queue
            2. else if no server is available the wait time will be adjusted for the customers in the queue.
 */
import java.util.*;
public class sim {
    public static double randomNum(){
        Random random = new Random(97);
        return random.nextDouble();
    }


    //used too generate a service time for customers
   public static int getServiceTime(int i){
        Random random = new Random(97);
        int serviceTime = random.nextInt(i);
        if(serviceTime == 0)// service time can not be 0;
            serviceTime = serviceTime +1;
        return serviceTime;
    }

    private static int getPoissonRandom(double mean) {
        Random random = new Random();
        double r = random.nextDouble();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r;
            k++;
        }
        while (p > L);
        return k - 1;
    }

    public static void heavyDemand(Queue q){

        int lwtime = 0;
        for(int i = 0; i<=20; i++){//loop for 20 ticks, ticks = 1 min
            int cNum = getPoissonRandom(2);
            System.out.println(cNum+" new customers have come into the store");
            if (cNum > 0){// if new customers come in create new customer objects
                for(int j = 0; j<=cNum; j++){
                    q.enqueue();//generates new customer objects and places them into the queue as well as generating service time.
                }
            }
            //Here is where you adjust the amount of servers for the experiment
            q = server(q, i);
            q = server(q, i);
            q = server(q, i);
            System.out.println("========================");

        }
    }
    public static Queue server(Queue q, int i){//This method acts as the server and removes customers from the queue
        int server = 1;
        server--;
        if(server == 0) {
            q.dequeue();
            server =  q.dequeue();
            q.wTimeIncrement();
            System.out.println("Time passed:" +i+" mins Server is free, taking a new customer from the queue");
            System.out.println("Time until Server is free: "+server);
            System.out.println(q.countEl()+" customers left in the queue");
            System.out.println();

        }
        else {
            q.wTimeIncrement();
            System.out.println("Time passed:" +i+" mins Server occupied for "+server+" mins");
            System.out.println(q.countEl()+" customers left in the queue ");
            System.out.println();

        }
        return q;
    }

    public static void menu(){
        System.out.println("Welcome to the simulation");
        System.out.println("Please select a option of how to run the simulation");
        System.out.println("1: Heavy Demand");
        System.out.println("2: Low Demand");
        Scanner stdIn = new Scanner(System.in);
        int i = stdIn.nextInt();
        switch(i){
            case 1:
                Queue cQueue = new Queue();
                heavyDemand(cQueue);
                break;

            case 2:
                getPoissonRandom(.25);
                getServiceTime(4);

        }
    }


    public static void main(String[] args){
        menu();
    }

}
