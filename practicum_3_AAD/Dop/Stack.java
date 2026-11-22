package practicum_3_AAD.Dop;


public class Stack {

    private Node top;

    boolean isEmpty() {
        return top == null;
    }

    void push(int val) {
        top = new Node(val, top);
    }

    int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("stack is empty");
        }

        int result = top.getValue();
        top = top.getNext();
        return result;
    }

}
