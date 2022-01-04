
public class App {
    public static void main(String[] args) throws Exception {
        StackLinkedList stack = new StackLinkedList();

        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }

        stack.print();

        stack.pop();

        stack.print();
    }
}

class Node {
    int value;
    Node next;
}

class StackLinkedList {
    Node head;

    public StackLinkedList() {
        head = null;
    }

    public void push (int value) {
        
        Node oldHead = head;
        head = new Node();
        head.value = value;
        head.next = oldHead;
    
    }

    public void pop () {
        if (head == null) {
            System.out.println("stack empty");
        } else {
            head = head.next;
        }
    }

    public int peak () {
        return head.value;
    }

    public void print () {
        Node temp = head;
        while (temp != null){
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
