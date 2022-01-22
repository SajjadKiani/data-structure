import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner file1 = new Scanner( new File("testcase0input1.txt"));
        Scanner file2 = new Scanner( new File("testcase0input2.txt"));

        // train
        // create Prefix tree
        String[] train = new String[1000000];
        for (int i=0 ; i < 1000000 ; i++) {
            train[i] = file1.nextLine();
        }

        PrefixTree pt = new PrefixTree(train);

        pt.createTree();

        // test
        // find 1000 numbers in tree
        String[] test = new String[1000];
        for (int i = 0; i < 1000; i++) {
            test[i] = file2.nextLine();
        }

        // delete a Node
        pt.deleteNode("1081");

        // create a Node
        pt.createNodes("1081");

        pt.search(test);

    }
}

class Node {

    String value;
    int count;
    ArrayList<Node> adjancies;

    public Node (String value) {
        this.value = value;
        this.adjancies = new ArrayList<Node>();
    }
}

class PrefixTree {

    Node root;
    private String[] train;

    public PrefixTree ( String[] train) {
        this.train = train;
        this.root = new Node("*");
    }

    public void createNodes (String number) {
        Node currentNode = root;

        for (int i = 0; i < number.length(); i++) {

            String num = Character.toString (number.charAt(i));

            Node tempNode = findNode(num, currentNode);

            if (tempNode != null) {
                currentNode = tempNode;
            } else {
                Node newNode = new Node (num);
                currentNode.adjancies.add( newNode);
                currentNode = newNode;
            }
        }

        currentNode.count++;
    }

    public void createTree () {

        for (String number : train) {
            createNodes(number);
        }
    }

    // find a number with one digites (Adad yek raghami) in a node's adjancies
    private Node findNode (String num , Node node) {
        
        for (Node adjNode : node.adjancies) {
            if (adjNode.value.equals( num)) {
                return adjNode;
            }
        }

        return null;
    }

    public void search (String[] test) {
        
        for (String number : test) {
            searchNode(number);
        }
        
    }

    private void searchNode (String number) {
        Node currentNode  = root;

        for (int i = 0; i < number.length(); i++) {

            String num = Character.toString (number.charAt(i));
            Node tempNode = findNode(num, currentNode);

            if (tempNode != null)
                currentNode = tempNode;
            else 
                break;
        }

        System.out.println(currentNode.count);
        // System.out.println("[ " + number + " ] - [ " + currentNode.count + " ]");
    }

    public void deleteNode (String number) {
        Node currentNode = root;

        for (int i = 0; i < number.length(); i++) {

            String num = Character.toString (number.charAt(i));

            Node tempNode = findNode(num, currentNode);

            if (tempNode != null) {
                currentNode = tempNode;
            } else {
                break;
            }
        }

        currentNode.count--;
    }
}