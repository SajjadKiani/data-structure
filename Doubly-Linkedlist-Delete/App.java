import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();

        DLL dll = new DLL();

        for (int i = 0; i < n; i++) {
            int data =scn.nextInt();
            dll.appendNode(new Node(data));
        }

        for (int i = 0; i < m; i++) {
            int index = scn.nextInt();

            dll.deleteNode(index);
        }

        dll.printNode();
    }


}

class Node {
    Node prev;
    Node next;
    int data;

    public Node (int data) {
        this.data = data;
    }
}

class DLL {
    Node head;
    Node start;

    public void appendNode (Node node) {
        if (head != null) {
            head.next = node;
            node.prev = head;
        } else 
            start = node;
        

        head = node;
    }

    public void deleteNode(int index) {
        Node temp = start;
        int i=0;
        while (temp != null) {
            
            if (i == index) {
                if (temp.next != null)
                    temp.next.prev = temp.prev;

                if (temp.prev != null)
                    temp.prev.next = temp.next;
            }

            temp = temp.next;
            i++;
        }

        if (index ==0)
            start = start.next;
    }

    public void printNode () {
        Node temp = start;
        
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null)
                System.out.print(", ");
            temp = temp.next;
        }
        System.out.println();
    }
}
