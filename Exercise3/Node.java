package Exercise3;

public class Node {
    private Integer index;
    private Node left;
    private Node right;
    
    /**
     * Empty constructor
     */
    public Node(){}
    
    /**
     * Complete constructor for Node class
     * @param index
     * @param left
     * @param right
     */
    public Node(Integer index, Node left, Node right) {
        this.index = index;
        this.left = left;
        this.right = right;
    }

    public Integer getIndex() {return index;}

    public void setIndex(Integer index) {this.index = index;}

    public Node getLeft() {return left;}

    public void setLeft(Node left) {this.left = left;}

    public Node getRight() {return right;}

    public void setRight(Node right) {this.right = right;}

    @Override
    public String toString() {return "Node [index=" + index + "]";}

    

    
}
