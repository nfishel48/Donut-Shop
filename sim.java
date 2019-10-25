/*
@author Nathaniel Fishel
@assignment Project 1
@semester Fall 2019
@class COSC 311
@professor Dr. Haynes
@description This is the main class for the donut shop simulation. The program will first use a random number generator to calculate how many customers
are coming into the shop. Then will see if there are any available servers/employees.
then        1. if a server is available it will take the customer out of the queue
            2. else if no server is available the wait time will be adjusted for the customers in the queue.
 */

import java.util.*;
public class sim {
   static int inService = 0;
   static int completed = 0;
   static Random r = new Random(97);


    private static int getPoissonRandom(double mean) {
            double L = Math.exp(-mean);
            int k = 0;
            double p = 1.0;
            do {
                p = p * r.nextDouble();
                k++;
            }
            while (p > L);
            return k - 1;
    }
     static void heavyDemand(Queue q) {
        //Add or remove servers here
        QueueNode server0 = new QueueNode(0, 0);
        QueueNode server1 = new QueueNode(0, 0);
         QueueNode server2 = new QueueNode(0, 0);
         QueueNode server3 = new QueueNode(0, 0);
         QueueNode server4 = new QueueNode(0, 0);
         QueueNode server5 = new QueueNode(0, 0);
         QueueNode server6 = new QueueNode(0, 0);
         QueueNode server7 = new QueueNode(0, 0);
        for (int i = 0; i <= 19; i++) {//loop for 20 ticks, ticks = 1 min
            int cNum = getPoissonRandom(2);
            if (cNum > 0) {// if new customers come in create new customer objects
                for (int j = 0; j < cNum; j++) {
                    q.enqueue();//generates new customer objects and places them into the queue as well as generating service time.
                }
            }

            //set the server nodes equal to the work done by the method
            server0 = doWork(server0, q, i);
            server1 = doWork(server1, q, i);
            server2 = doWork(server2, q, i);
            server3 = doWork(server3, q, i);
            server4 = doWork(server4, q, i);
            server5 = doWork(server5, q, i);
            server6 = doWork(server6, q, i);
            server7 = doWork(server7, q, i);
            q.wTimeIncrement();

            //Print results
            System.out.println("========================");
            System.out.println("Tick #" + i);
            System.out.println("Customers in service: "+inService);
            System.out.println("Customers with completed service: " + completed);
            System.out.println("Customers in queue " + q.countEl());
            if(q.countEl() == 0){
                System.out.println("Nobody is waiting");
            }
            else {
                System.out.println("Total wait time: "+ q.totalWtiime());
                System.out.println("Minimum wait time " + q.minWTime());
                System.out.println("Maximum wait time " + q.maxWTime());
                System.out.println("Average wait time " + q.avgWTime());
                System.out.println("========================");
            }

        }
    }

     static void lightDemand(Queue q){//light demand
        //Add or remove servers here
         QueueNode server0 = new QueueNode(0, 0);
         QueueNode server1 = new QueueNode(0, 0);
         QueueNode server2 = new QueueNode(0, 0);
         QueueNode server3 = new QueueNode(0, 0);
         //QueueNode server4 = new QueueNode(0, 0);
         //QueueNode server5 = new QueueNode(0, 0);
         //QueueNode server6 = new QueueNode(0, 0);
         //QueueNode server7 = new QueueNode(0, 0);

        for(int i = 0; i<=19; i++){//loop for 20 ticks, ticks = 1 min
            int cNum = getPoissonRandom(.25);
            if (cNum > 0){// if new customers come in create new customer objects
                for(int j = 0; j<cNum; j++){
                    q.enqueueLow();//generates new customer objects and places them into the queue as well as generating service time.
                }
            }

            //set the server nodes equal to the work done by the method
            server0 = doWork(server0, q, i);
            server1 = doWork(server1, q, i);
            server2 = doWork(server2, q, i);
            server3 = doWork(server3, q, i);
            //server4 = doWork(server4, q, i);
            //server5 = doWork(server5, q, i);
            //server6 = doWork(server6, q, i);
            //server7 = doWork(server7, q, i);
            q.wTimeIncrement();

            //Print results
            System.out.println("========================");
            System.out.println("Tick #" + i);
            System.out.println("Customers in service: "+inService);
            System.out.println("Customers with completed service: " + completed);
            System.out.println("Customers in queue " + q.countEl());
            if(q.countEl() == 0){
                System.out.println("Nobody is waiting");
            }
            else {
                System.out.println("Total wait time: "+ q.totalWtiime());
                System.out.println("Minimum wait time " + q.minWTime());
                System.out.println("Maximum wait time " + q.maxWTime());
                System.out.println("Average wait time " + q.avgWTime());
                System.out.println("========================");
            }


        }
    }

     static QueueNode doWork(QueueNode server, Queue q, int i){//This method acts as the server and removes customers from the queue
        if(server.sTime == 0) {//if the server is done with the customer
            if(q.firstEl() == null)//if queue is empty
                System.out.println("No customers in the queue Server is idle");
            else {
                System.out.println("Server is open, Taking the first person from the queue ");
                inService++;
                server = q.firstEl();
                q.dequeue();
            }
        }
        else {//Server is busy
            System.out.println("Server is busy for "+server.sTime+" ticks");
            server.sTime--;
            if(server.sTime == 0) {
                inService--;
                completed++;
            }
        }
        return server;//update the server object
    }

    static void menu(){
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
