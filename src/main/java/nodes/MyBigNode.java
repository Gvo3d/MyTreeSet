package main.java.nodes;

/**
 * Created by Gvozd on 05.01.2016.
 */
public class MyBigNode extends MyNode{
    private MyNode left;
    private MyNode right;

    public MyBigNode(int id, Object data) {
        super(id, data, true);
    }

    public MyNode getLeft() {
        return left;
    }

    public void setLeft(MyNode left) {
        this.left = left;
    }

    public MyNode getRight() {
        return right;
    }

    public void setRight(MyNode right) {
        this.right = right;
    }
}
