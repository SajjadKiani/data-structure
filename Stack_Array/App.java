
public class App {
    public static void main(String[] args) throws Exception {
        StackArray stack = new StackArray(6);
        
        for (int i = 0; i < 6 ; i++) {
            stack.push(i);
        }

        stack.print();

        stack.pop();

        stack.print();

        stack.pop();

        stack.print();
        
        stack.push(10);

        stack.print();

        // for (int i : stack.arr) {
        //     System.out.println(i);
        // }


    }
}

class StackArray {
    int head;
    int size;
    int[] arr;

    public StackArray (int size) {
        this.size = size;
        arr = new int[size];
        this.head = -1;
    }

    public void push (int pushedElement) {
        if (!isFull()) {
            head++;
            arr[head] = pushedElement;
        } else {
            System.out.println("can't push: " + pushedElement);   
        }
    }

    public int pop (){
        if (!isEmpty()) {
            
            int beforeHead = head;
            head--;
            
            return beforeHead;
        } else {
            System.out.println("stack Empty!");            
        }

        return -1;
    }

    public int peak() {
        if (!isEmpty()) {
            return arr[head];
        } else {
            System.out.println("Stacck Empty");
            return -1;
        }
    }

    public void print () {
        for (int i = 0; i < head+1; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public Boolean isFull(){
        return head == size-1;
    }

    public Boolean isEmpty(){
        return head == -1;
    }
}
