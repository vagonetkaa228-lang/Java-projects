package practicum_3_AAD.Dop;


public class Queue {

    private Node head;
    private Node tail;

    boolean isEmpty() {
        return head == null;
    }

    void enqueue(int val) {
        Node node = new Node(val, null);
        if (tail == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
    }

    int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty");
        }

        int result = head.getValue();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return result;
    }

}
