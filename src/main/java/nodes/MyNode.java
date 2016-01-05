package main.java.nodes;

/**
 * Created by Gvozd on 05.01.2016.
 */
public abstract class MyNode implements Comparable {
    private int id;
    private final boolean isBigNode;
    private Object data;
    private MyNode center;


    public MyNode(int id, Object data, boolean isBigNode) {
        this.data = data;
        this.id = id;
        this.isBigNode = isBigNode;
    }

    public boolean isBigNode() {
        return isBigNode;
    }

    public int getId() {
        return this.id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MyNode getCenter() {
        return center;
    }

    public void setCenter(MyNode center) {
        this.center = center;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return (String) this.getData().toString();
    }

    @Override
    public int compareTo(Object o) {
//        MyNode TempNode = (MyNode) o;
        Comparable comparableOuterData = (Comparable) o;
        Comparable comparableInnerData = (Comparable) this.getData();
        if (comparableOuterData.compareTo(comparableInnerData)<0) return -1;
        if (comparableOuterData.compareTo(comparableInnerData)>0) return 1;
        else return 0;
    }
}
