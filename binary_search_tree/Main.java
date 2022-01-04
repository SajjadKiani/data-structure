import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scn = new Scanner(new File("testcase0input1.txt"));

        Tree tree = new Tree();

        System.out.println("=====start=====\n");

        while (scn.hasNextInt()) {
            tree.insert(new TreeNode(scn.nextInt()));
        }

        // TreeNode find = tree.search(tree.getRoot(), 1081);

        // System.out.println("value: " + find.getValue());
        // System.out.println("right: " + find.getRight().getValue());
        // System.out.println("left: " + find.getLeft().getValue());

        tree.inOrder(tree.getRoot());

        System.out.println("\n======end======");
    }
}

class Tree {
    private TreeNode  root;

    public void insert (TreeNode newNode) {
        if (root == null) 
            root = newNode;
        else
            insertNode(root,newNode);
    }

    public void insertNode (TreeNode current , TreeNode newNode) {

        if ( newNode.getValue() < current.getValue()){
            if (current.getLeft() == null) 
                current.setLeft(newNode);
            else
                insertNode(current.getLeft(), newNode);
        } else {
            if (current.getRight() == null) 
                current.setRight(newNode);
            else
                insertNode(current.getRight(), newNode);
        }
    }

    public TreeNode search (TreeNode root, int value) {
        if (root == null)
            return null;
        else {
            if (root.getValue() == value) 
                return root;
            else if (value < root.getValue())
                return search(root.getLeft(), value);
            else
                return search(root.getRight(), value);
        }

    }

    public void inOrder (TreeNode root) {
        if (root != null) {
            inOrder(root.getLeft());
            System.out.print(root.getValue() + " ");
            inOrder(root.getRight());
        }
    }

    public TreeNode  getRoot() {
        return root;
    }

    public void setRoot(TreeNode  root) {
        this.root = root;
    }
}

class TreeNode {
    private int value;
    private TreeNode  left;
    private TreeNode  right;

    public TreeNode(int value) {
        this.setValue(value);
        this.setLeft(null);
        this.setRight(null);
    }
    
    public int getValue() {
        return value;
    }


    public void setValue(int value) {
        this.value = value;
    }
    
    public TreeNode  getRight() {
        return right;
    }

    public void setRight(TreeNode  right) {
        this.right = right;
    }

    public TreeNode  getLeft() {
        return left;
    }

    public void setLeft(TreeNode  left) {
        this.left = left;
    }
}