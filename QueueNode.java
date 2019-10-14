public class QueueNode {
    public int wTime;
    public int sTime;
    public QueueNode next = null;
    public QueueNode(int wTime, int sTime){
        this.wTime = wTime;
        this.sTime = sTime;
    }
}
