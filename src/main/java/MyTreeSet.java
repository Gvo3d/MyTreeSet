package main.java;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Gvozd on 05.01.2016.
 */
public class MyTreeSet{
    private MyNode root = new MyBigNode(0, null);
    private ArrayList<MyNode> idCounter = new ArrayList<MyNode>(1);
    private MyNode currentNode = root;

    public Object getRootData(){
        return root.getData();
    }

    public MyTreeSet() {
        idCounter.add(0, root);
    }

    public long size(){
        return idCounter.size();
    }

    public boolean isEmpty() {
        MyBigNode myroot = (MyBigNode) root;
        if ((myroot.getData()==null)&&(myroot.getLeft()==null)&&(myroot.getRight()==null)&&(myroot.getCenter()==null)) {
            return true;
        }
        return false;
    }

    public boolean add(Object o) {
        MyNode NewBigNode = new MyBigNode(-1, o);
        if (this.isEmpty()==true) {
            root = NewBigNode;
            root.setId(0);
            idCounter.set(0, root);
            return true;
        }
        return add (root, NewBigNode);
    }

    private boolean add(MyNode addTo, MyNode addWhat) {
        MyBigNode addToB = (MyBigNode) addTo;
        MyBigNode addWhatB = (MyBigNode) addWhat;
        Comparable addToObject = (Comparable) addTo.getData();
        Comparable addWhatObject = (Comparable) addWhat.getData();

        if (addToObject.compareTo(addWhatObject)<0){
            if (addToB.getLeft()==null) {
                addToB.setLeft(addWhatB);
                addWhatB.setId(idCounter.size());
                idCounter.add(addWhatB);
                return true;
            } else return add(addToB.getLeft(), addWhat);
        } else if (addToObject.compareTo(addWhatObject)>0){
            if (addToB.getRight()==null) {
                addToB.setRight(addWhatB);
                addWhatB.setId(idCounter.size());
                idCounter.add(addWhatB);
                return true;
            } else return add(addToB.getRight(), addWhat);
        } else if (addToObject.compareTo(addWhatObject)==0){
            if (addToB.getCenter()==null){
                addToB.setCenter(new MySmallNode(idCounter.size(), addWhat.getData()));
                idCounter.add(addToB.getCenter());
                return true;
            } else return add(addToB.getCenter(), addWhat);
        }
        return false;
    }


    public boolean remove(Object o) {
        return false;
    }

    public boolean addAll(Collection c) {
        return false;
    }

    public void clear() {
        idCounter.clear();
        root= new MyBigNode(0, null);
        idCounter.add(0, root);
    }

    public boolean removeAll(Collection c) {
        return false;
    }

    public boolean retainAll(Collection c) {
        return false;
    }

    public boolean containsAll(Collection c) {
        return false;
    }

    public boolean contains(Object o) {
        return false;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public String toString(){
        String result="";
        for (MyNode node: idCounter) result=result+node.toString();
        return result;
    }

    public void currentSetRoot(){
        currentNode=root;
    }

    public void currentGetData(){
        System.out.println(currentNode.getData().toString());
    }

    public void currentGetLeft(){
        MyBigNode newnode = (MyBigNode) currentNode;
        currentNode= newnode.getLeft();
        currentGetData();
    }

    public void currentGetRight(){
        MyBigNode newnode = (MyBigNode) currentNode;
        currentNode= newnode.getRight();
        currentGetData();
    }

    public void currentGetCenter(){
        currentNode=currentNode.getCenter();
        currentGetData();
    }

}
