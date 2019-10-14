import java.util.Random;

public class Queue {
    private QueueNode head, tail;

    public Queue() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = tail = null;
    }

    public Object firstEl() {

        return head.sTime;
    }

    public int firstwT() {

        return head.wTime;
    }

    //used too generate a service time for customers
    public static int getServiceTime(int i) {
        Random random = new Random();
        int serviceTime = random.nextInt(i);
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

    public int dequeue() {
        if (!isEmpty()) {
            int el = head.sTime;
            if (head == tail)//only for single list
                head = tail = null;
            else head = head.next;
            return el;
        } else return 0;
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
        tail = head;
        if(!isEmpty()){
            while(head != null) {
                head.wTime = head.wTime++;
                head = head.next;
            }
            head = tail;
        }
    }
}


