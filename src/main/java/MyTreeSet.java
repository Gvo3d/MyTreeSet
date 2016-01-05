package main.java;

import main.java.nodes.MyBigNode;
import main.java.nodes.MyNode;
import main.java.nodes.MySmallNode;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Gvozd on 05.01.2016.
 */
public class MyTreeSet extends AbstractSet implements TreeSetCurrentMethods {
    private MyNode root = new MyBigNode(0, null);
    private ArrayList<MyNode> idCounter = new ArrayList<MyNode>();
    private MyNode currentNode = root;

    public Object getRootData() {
        return root.getData();
    }

    public int size() {
        return idCounter.size();
    }

    public boolean isEmpty() {
        MyBigNode myroot = (MyBigNode) root;
        if ((myroot.getData() == null) && (myroot.getLeft() == null) && (myroot.getRight() == null) && (myroot.getCenter() == null)) {
            return true;
        }
        return false;
    }

    public boolean add(Object o) {
        MyNode NewBigNode = new MyBigNode(-1, o);
        if (this.isEmpty() == true) {
            root = NewBigNode;
            root.setId(0);
            idCounter.add(0, root);
            currentNode=root;
            return true;
        }
        return add(root, NewBigNode);
    }

    private boolean add(MyNode addTo, MyNode addWhat) {
        MyBigNode addToB = (MyBigNode) addTo;
        MyBigNode addWhatB = (MyBigNode) addWhat;
        Comparable addToObject = (Comparable) addTo.getData();
        Comparable addWhatObject = (Comparable) addWhat.getData();

        if (addWhatObject.compareTo(addToObject) < 0) {
            if (addToB.getLeft() == null) {
                addToB.setLeft(addWhatB);
                addWhatB.setId(idCounter.size());
                idCounter.add(addWhatB);
                return true;
            } else return add(addToB.getLeft(), addWhat);
        } else if (addWhatObject.compareTo(addToObject) > 0) {
            if (addToB.getRight() == null) {
                addToB.setRight(addWhatB);
                addWhatB.setId(idCounter.size());
                idCounter.add(addWhatB);
                return true;
            } else return add(addToB.getRight(), addWhat);
        } else if (addWhatObject.compareTo(addToObject) == 0) {
            if (addToB.getCenter() == null) {
                addToB.setCenter(new MySmallNode(idCounter.size(), addWhat.getData()));
                idCounter.add(addToB.getCenter());
                return true;
            } else return add(addToB.getCenter(), addWhat);
        }
        return false;
    }

    public boolean addAll(Collection c) {
        boolean resultIsGood=true;
        for (Object newObject:c){
            if (add(newObject)==false) resultIsGood=false;
        }
        return resultIsGood;
    }

    public void clear() {
        idCounter.clear();
        root = new MyBigNode(0, null);
        idCounter.add(0, root);
    }


    public boolean contains(Object o) {
        MyNode tempNode = currentNode;
        currentNode=root;
        MyNode result = nodeDataComparator(o);
        currentNode=tempNode;
        if (result.getId()==-1) return false;
        return true;
    }

    private MyNode nodeDataComparator(Object searchable){
        if (currentNode.compareTo(searchable)<0){
            if (currentHasLeft()){
                currentMoveLeft();
                return nodeDataComparator(searchable);
            } else return new MyBigNode (-1, null);

        } else if (currentNode.compareTo(searchable)>0) {
            if (currentHasRight()){
                currentGetRight();
                return nodeDataComparator(searchable);
            } else return new MyBigNode (-1, null);

        } else if (currentNode.compareTo(searchable)==0) {
            return currentNode;
        }
        return new MyBigNode (-1, null);
    }

    public boolean containsAll(Collection c) {
        boolean resultIsGood=true;
        boolean temporary;
        for (Object newObject:c){
            temporary = contains(newObject);
            if (temporary==false) resultIsGood=false;
        }
        return resultIsGood;
    }


    public boolean remove(Object o) {
        MyNode tempNode = currentNode;
        currentNode=root;
        MyNode result = nodeDataComparator(o);
        currentNode=tempNode;

        if (result.getId()==-1) return false;
        return true;

    }

    public boolean removeAll(Collection c) {
        return false;
    }

    public boolean retainAll(Collection c) {
        return false;
    }



    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public String toString() {
        String result = "";
        for (MyNode node : idCounter) result = result + node.toString();
        return result;
    }

    @Override
    public boolean currentSetRoot() {
        if (root.getData() == null) return false;
        currentNode = root;
        return true;
    }

    @Override
    public Object currentGetData() {
        return currentNode.getData();
    }

    @Override
    public Object currentGetLeft() {
        if (currentMoveLeft()==true) return currentNode.getData();
        return new String ("null");
//        else throw new NullPointerException("Current node has a null left link.");
    }

    @Override
    public Object currentGetRight() {
        if (currentMoveRight()==true) return currentNode.getData();
        return new String ("null");
//        else throw new NullPointerException("Current node has a null right link.");
    }

    @Override
    public Object currentGetCenter() {
        if (currentMoveCenter()==true) return currentNode.getData();
        return new String ("null");
//        else throw new NullPointerException("Current node has a null center link.");
    }

    @Override
    public boolean currentMoveLeft() {
        MyBigNode newnode = (MyBigNode) currentNode;
        if (currentHasLeft())  {
            currentNode = newnode.getLeft();
            return true;
        }
        return false;
    }

    @Override
    public boolean currentMoveRight() {
        MyBigNode newnode = (MyBigNode) currentNode;
        if (currentHasRight())  {
            currentNode = newnode.getRight();
            return true;
        }
        return false;
    }

    @Override
    public boolean currentMoveCenter() {
        if (currentHasCenter()) {
            currentNode = currentNode.getCenter();
            return true;
        }
        return false;
    }

    @Override
    public boolean currentHasLeft() {
        MyBigNode current = (MyBigNode) currentNode;
        if ((currentNode.isBigNode())&&current.getLeft()!=null) return true;
        return false;
    }

    @Override
    public boolean currentHasRight() {
        MyBigNode current = (MyBigNode) currentNode;
        if ((currentNode.isBigNode())&&current.getRight()!=null) return true;
        return false;
    }

    @Override
    public boolean currentHasCenter() {
        if (currentNode.getCenter()!=null) return true;
        return false;
    }

    @Override
    public String currentToString() {
        MyBigNode temp = (MyBigNode) currentNode;
        String leftnode, rightnode, centernode;

        if (temp.getLeft()==null) {
            leftnode = "null";
        } else leftnode=temp.getLeft().toString();

        if (temp.getRight()==null) {
            rightnode = "null";
        } else rightnode=temp.getRight().toString();

        if (temp.getCenter()==null) {
            centernode = "null";
        } else centernode=temp.getCenter().toString();

        String result;
        result="Data:"+currentGetData().toString()+" Left:"+leftnode+" Right:"+rightnode+" Center:"+centernode;
        return result;
    }

}
