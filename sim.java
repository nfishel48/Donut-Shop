/*
@author Nathaniel Fishel
@description This is the main class for the donut shop simulation. The program will first use a random number generator to calculate how many customers
are coming into the shop. Then will see if there are any available servers/employees.
then        1. if a server is available it will take the customer out of the queue
            2. else if no server is available the wait time will be adjusted for the customers in the queue.
 */
import java.util.*;
public class sim {
  static int inService = 0;
   static int completed = 0;


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
    public static void heavyDemand(Queue q) {
        //Add or remove servers here
        QueueNode server0 = new QueueNode(0, 0);
        QueueNode server1 = new QueueNode(0, 0);

        for (int i = 0; i <= 20; i++) {//loop for 20 ticks, ticks = 1 min
            int cNum = getPoissonRandom(2);
            System.out.println(cNum + " new customers have come into the store");
            if (cNum > 0) {// if new customers come in create new customer objects
                for (int j = 0; j < cNum; j++) {
                    q.enqueue();//generates new customer objects and places them into the queue as well as generating service time.
                }
            }

            //set the server nodes equal to the work done by the method
            server0 = doWork(server0, q, i);
            server1 = doWork(server1, q, i);
            q.wTimeIncrement();

            //Print results
            System.out.println("Tick #" + i);
            System.out.println("Customers with completed service: " + completed);
            System.out.println("Customers in queue " + q.countEl());
            if(q.countEl() == 0){
                System.out.println("Nobody is waiting");
            }
            else {
                System.out.println("Minimum wait time " + q.minWTime());
                System.out.println("Maximum wait time " + q.maxWTime());
                System.out.println("Average wait time " + q.avgWTime());
                System.out.println("========================");
            }

        }
    }
    public static void lightDemand(Queue q){
        //Add or remove servers here
        QueueNode server0 = new QueueNode(0,0);
        QueueNode server1 = new QueueNode(0,0);

        for(int i = 0; i<=20; i++){//loop for 20 ticks, ticks = 1 min
            int cNum = getPoissonRandom(.25);
            System.out.println(cNum+" new customers have come into the store");
            if (cNum > 0){// if new customers come in create new customer objects
                for(int j = 0; j<cNum; j++){
                    q.enqueueLow();//generates new customer objects and places them into the queue as well as generating service time.
                }
            }

            //set the server nodes equal to the work done by the method
            server0 = doWork(server0, q, i);
            server1 =  doWork(server1, q, i);
            q.wTimeIncrement();

            //Print results
            System.out.println("Tick #"+i);
            System.out.println("Customers with completed service: "+completed);
            System.out.println("Customers in queue "+q.countEl());
            System.out.println("Minimum wait time "+ q.minWTime());
            System.out.println("Maximum wait time "+ q.maxWTime());
            System.out.println("Average wait time "+ q.avgWTime());


            System.out.println("========================");

        }
    }
    public static QueueNode doWork(QueueNode server, Queue q, int i){//This method acts as the server and removes customers from the queue
        if(server.sTime == 0) {
            System.out.println("Server is open, Taking the first person from the queue ");
            inService++;
            server = q.firstEl();
            q.dequeue();
            server.sTime--;
            if(server.sTime == 0)
                completed++;
        }
        else {
            System.out.println("Server is busy for "+server.sTime+" ticks");
            server.sTime--;
            if(server.sTime == 0)
                completed++;
        }
        return server;
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
                Queue dQueue = new Queue();
                lightDemand(dQueue);


        }
    }


    public static void main(String[] args){
        menu();
    }

}
