import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));
        String input;
        while (true) {
            input = br.readLine();
            if (input == null || input.equals("")) {
                break;
            }
            root.add(new Node(Integer.parseInt(input)));
        }
        // data input end
        root.printPostOrder();
    }
}

class Node {
    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
    }

    public void add(Node node) {
        if (node.data < this.data) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    public void printPostOrder() {
        if (this.left != null) {
            this.left.printPostOrder();
        }
        if (this.right != null) {
            this.right.printPostOrder();
        }
        System.out.println(this.data);
    }
}