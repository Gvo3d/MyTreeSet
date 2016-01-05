package main.java.nodes;

/**
 * Created by Gvozd on 05.01.2016.
 */
public class NodeComparingResult {
    private boolean isRight;
    private MyNode previous;

    public NodeComparingResult(boolean isRight, MyNode previous) {
        this.isRight = isRight;
        this.previous = previous;
    }

    public boolean isRight() {
        return isRight;
    }

    public MyNode getPrevious() {
        return previous;
    }
}
