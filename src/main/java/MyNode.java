package main.java;

/**
 * Created by Gvozd on 05.01.2016.
 */
public abstract class MyNode {
    private long id;
    private Object data;
    private MyNode center;


    public MyNode(long id, Object data) {
        this.data = data;
        this.id = id;
    }

    public long getId() {
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

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return (String) this.getData().toString();
    }
}
