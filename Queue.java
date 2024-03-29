
public class Queue extends sim {
    private QueueNode head, tail;

    public Queue() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }


    public QueueNode firstEl() {

        return head;
    }

    //used too generate a service time for customers
    public static int getServiceTime(int i) {
        int serviceTime = r.nextInt(i);
        if (serviceTime == 0)// service time can not be 0;
            serviceTime = serviceTime + 1;
        return serviceTime;
    }

    public void enqueue() {
        if (!isEmpty()) {// if something is in queue put the new object in the back
            tail.next = new QueueNode(0, getServiceTime(13));
            tail = tail.next;
        }//if the queue is empty insert object into the queue
        else head = tail = new QueueNode(0, getServiceTime(13));
    }
    public void enqueueLow() {
        if (!isEmpty()) {// if something is in queue put the new object in the back
            tail.next = new QueueNode(0, getServiceTime(4));
            tail = tail.next;
        }//if the queue is empty insert object into the queue
        else head = tail = new QueueNode(0, getServiceTime(4));
    }

    public void dequeue() {
            if (head == tail) {//only for single list
                head = tail = null;
            }
            else {
                head = head.next;
            }


    }

    public int countEl() {
        QueueNode copy = head;
        int count = 0;
        if (!isEmpty()) {
            while (copy != null) {
                copy= copy.next;
                count++;
            }
            return count;

        }
        else return count;
    }

    public void wTimeIncrement(){
       QueueNode c = head;
       while(c != null){
           c.wTime++;
           c = c.next;
       }
    }

    public int minWTime(){
        QueueNode c = head;
        int min = 100;
        if(firstEl() == null)
            return 0;
        else {
            while (c != null) {
                if (c.wTime < min)
                    min = c.wTime;
                c = c.next;
            }
            return min;
        }
    }

    public int maxWTime(){
        QueueNode c = head;
        int max = 0;
        while(c != null){
            if(c.wTime>max)
                max = c.wTime;
            c = c.next;
        }
        return max;
    }

    public int avgWTime(){
        QueueNode c = head;
        int total = 0;
        while(c != null){
           total = total + c.wTime;
           c = c.next;
        }
        int d = countEl();
        if(d == 0)
            return 0;
        else {
            int avg = total / d;
            return avg;
        }
    }

    public int totalWtiime(){
        QueueNode c = head;
        int total = 0;
        while(c != null){
            total = total + c.wTime;
            c = c.next;
        }
        return total;
    }
}


