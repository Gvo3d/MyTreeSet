package main.java;

import main.java.nodes.MyBigNode;
import main.java.nodes.MyNode;
import main.java.nodes.MySmallNode;
import main.java.nodes.NodeComparingResult;

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
        int tempSize=0;
        for (int i=0; i<idCounter.size(); i++){
            if (idCounter.get(i)!=null) tempSize++;
        }
        return tempSize;
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
        MyNode deletable = nodeDataComparator(o);
        currentNode=tempNode;
        if (deletable.getId()==-1) return false;

        int deletableId=deletable.getId();
        MyBigNode deletableNode = (MyBigNode) deletable;

        if (root.compareTo(o)<0) {
            if (deletableNode.getLeft()!=null) {
                if (deletableNode.getRight()!=null){
                    MyNode movingNode = deletableNode.getRight();
                    MyNode secondMovingNode = deletableNode.getLeft();
                    NodeComparingResult previousNodeCR = searchNodeByLink(deletableNode);
                    MyBigNode previousNode = (MyBigNode) previousNodeCR.getPrevious();
                    Boolean isRight = previousNodeCR.isRight();
                    MyBigNode otherMovingNode = (MyBigNode) searchFinalNode(false, movingNode);
                    otherMovingNode.setLeft(secondMovingNode);
                    if (deletableNode.getCenter()!=null){
                        MyBigNode removingnode = (MyBigNode) deletableNode.getCenter();
                    removingnode.setLeft(deletableNode.getLeft());
                    removingnode.setRight(deletableNode.getRight());
                        if (isRight) {
                            previousNode.setRight(removingnode);
                        } else previousNode.setLeft(removingnode);
                        idCounter.set(deletableId,null);
                        return true;
                    }
                    if (isRight) {
                        previousNode.setRight(otherMovingNode);
                    } else previousNode.setLeft(otherMovingNode);
                    idCounter.set(deletableId,null);
                    return true;
                } else {
                    MyNode secondMovingNode = deletableNode.getLeft();
                    NodeComparingResult previousNodeCR = searchNodeByLink(deletableNode);
                    MyBigNode previousNode = (MyBigNode) previousNodeCR.getPrevious();
                    Boolean isRight = previousNodeCR.isRight();
                    if (deletableNode.getCenter()!=null){
                        MyBigNode removingnode = (MyBigNode) deletableNode.getCenter();
                        removingnode.setLeft(deletableNode.getLeft());
                        removingnode.setRight(deletableNode.getRight());
                        if (isRight) {
                            previousNode.setRight(removingnode);
                        } else previousNode.setLeft(removingnode);
                        idCounter.set(deletableId,null);
                        return true;
                    }
                    if (isRight) {
                        previousNode.setRight(secondMovingNode);
                    } else previousNode.setLeft(secondMovingNode);
                    idCounter.set(deletableId,null);
                    return true;
                }
            } else if (deletableNode.getRight()!=null) {
                MyNode movingNode = deletableNode.getRight();
                NodeComparingResult previousNodeCR = searchNodeByLink(deletableNode);
                MyBigNode previousNode = (MyBigNode) previousNodeCR.getPrevious();
                Boolean isRight = previousNodeCR.isRight();
                if (deletableNode.getCenter()!=null){
                    MyBigNode removingnode = (MyBigNode) deletableNode.getCenter();
                    removingnode.setLeft(deletableNode.getLeft());
                    removingnode.setRight(deletableNode.getRight());
                    if (isRight) {
                        previousNode.setRight(removingnode);
                    } else previousNode.setLeft(removingnode);
                    idCounter.set(deletableId,null);
                    return true;
                }
                if (isRight) {
                    previousNode.setRight(movingNode);
                } else previousNode.setLeft(movingNode);
                idCounter.set(deletableId,null);
                return true;
            } else {
                NodeComparingResult previousNodeCR = searchNodeByLink(deletableNode);
                MyBigNode previousNode = (MyBigNode) previousNodeCR.getPrevious();
                Boolean isRight = previousNodeCR.isRight();
                if (deletableNode.getCenter()!=null){
                    MyBigNode removingnode = new MyBigNode(deletableNode.getCenter().getId(), deletableNode.getCenter().getData());
                    if (deletableNode.getCenter()!=null) removingnode.setCenter(deletableNode.getCenter().getCenter());
                    removingnode.setLeft(deletableNode.getLeft());
                    removingnode.setRight(deletableNode.getRight());
                    if (isRight) {
                        previousNode.setRight(removingnode);
                    } else previousNode.setLeft(removingnode);
                    idCounter.set(deletableId,null);
                    idCounter.set(removingnode.getId(), removingnode);
                    return true;
                }
                if (isRight) {
                    previousNode.setRight(null);
                } else  previousNode.setLeft(null);
                idCounter.set(deletableId,null);
                return true;
            }
        } else if (root.compareTo(o)>0) {
            if (deletableNode.getRight()!=null) {
                if (deletableNode.getLeft()!=null){
                    MyNode movingNode = deletableNode.getLeft();
                    MyNode secondMovingNode = deletableNode.getRight();
                    NodeComparingResult previousNodeCR = searchNodeByLink(deletableNode);
                    MyBigNode previousNode = (MyBigNode) previousNodeCR.getPrevious();
                    Boolean isRight = previousNodeCR.isRight();
                    MyBigNode otherMovingNode = (MyBigNode) searchFinalNode(true, movingNode);
                    otherMovingNode.setRight(secondMovingNode);
                    if (deletableNode.getCenter()!=null){
                        MyBigNode removingnode = (MyBigNode) deletableNode.getCenter();
                        removingnode.setLeft(deletableNode.getLeft());
                        removingnode.setRight(deletableNode.getRight());
                        if (isRight) {
                            previousNode.setRight(removingnode);
                        } else previousNode.setLeft(removingnode);
                        idCounter.set(deletableId,null);
                        return true;
                    }
                    if (isRight) {
                        previousNode.setRight(otherMovingNode);
                    } else previousNode.setLeft(otherMovingNode);
                    idCounter.set(deletableId,null);
                    return true;
                } else {
                    MyNode secondMovingNode = deletableNode.getRight();
                    NodeComparingResult previousNodeCR = searchNodeByLink(deletableNode);
                    MyBigNode previousNode = (MyBigNode) previousNodeCR.getPrevious();
                    Boolean isRight = previousNodeCR.isRight();
                    if (deletableNode.getCenter()!=null){
                        MyBigNode removingnode = (MyBigNode) deletableNode.getCenter();
                        removingnode.setLeft(deletableNode.getLeft());
                        removingnode.setRight(deletableNode.getRight());
                        if (isRight) {
                            previousNode.setRight(removingnode);
                        } else previousNode.setLeft(removingnode);
                        idCounter.set(deletableId,null);
                        return true;
                    }
                    if (isRight) {
                        previousNode.setRight(secondMovingNode);
                    } else previousNode.setLeft(secondMovingNode);
                    idCounter.set(deletableId,null);
                    return true;
                }
            } else if (deletableNode.getLeft()!=null) {
                MyNode movingNode = deletableNode.getLeft();
                NodeComparingResult previousNodeCR = searchNodeByLink(deletableNode);
                MyBigNode previousNode = (MyBigNode) previousNodeCR.getPrevious();
                Boolean isRight = previousNodeCR.isRight();
                if (deletableNode.getCenter()!=null){
                    MyBigNode removingnode = (MyBigNode) deletableNode.getCenter();
                    removingnode.setLeft(deletableNode.getLeft());
                    removingnode.setRight(deletableNode.getRight());
                    if (isRight) {
                        previousNode.setRight(removingnode);
                    } else previousNode.setLeft(removingnode);
                    idCounter.set(deletableId,null);
                    return true;
                }
                if (isRight) {
                    previousNode.setRight(movingNode);
                } else previousNode.setLeft(movingNode);
                idCounter.set(deletableId,null);
                return true;
            } else {
                NodeComparingResult previousNodeCR = searchNodeByLink(deletableNode);
                MyBigNode previousNode = (MyBigNode) previousNodeCR.getPrevious();
                Boolean isRight = previousNodeCR.isRight();
                if (deletableNode.getCenter()!=null){
                    MyBigNode removingnode = new MyBigNode(deletableNode.getCenter().getId(), deletableNode.getCenter().getData());
                    if (deletableNode.getCenter()!=null) removingnode.setCenter(deletableNode.getCenter().getCenter());
                    removingnode.setLeft(deletableNode.getLeft());
                    removingnode.setRight(deletableNode.getRight());
                    if (isRight) {
                        previousNode.setRight(removingnode);
                    } else previousNode.setLeft(removingnode);
                    idCounter.set(deletableId,null);
                    idCounter.set(removingnode.getId(), removingnode);
                    return true;
                }
                if (isRight) {
                    previousNode.setRight(null);
                } else  previousNode.setLeft(null);
                idCounter.set(deletableId,null);
                return true;
            }
        } else if (root.compareTo(o)==0) {
            if (root.getCenter()!=null) {
                deletableNode = (MyBigNode) root;
                MyBigNode removingnode = new MyBigNode(deletableNode.getCenter().getId(), deletableNode.getCenter().getData());
                if (deletableNode.getCenter()!=null) removingnode.setCenter(deletableNode.getCenter().getCenter());
                removingnode.setLeft(deletableNode.getLeft());
                removingnode.setRight(deletableNode.getRight());
                root=removingnode;
                idCounter.set(deletableId,null);
                return true;

            }
            MyBigNode rootNode = (MyBigNode) root;
            MyBigNode leftOvers = (MyBigNode) rootNode.getLeft();
            MyBigNode rightNewRoot = (MyBigNode) ((MyBigNode) root).getRight();
            MyBigNode otherMovingNode = (MyBigNode) searchFinalNode(false, rightNewRoot);
            otherMovingNode.setLeft(leftOvers);
            root=rightNewRoot;
            return true;
        }
        return false;
    }

    private NodeComparingResult searchNodeByLink(MyNode searchable){
        MyNode tempNode = currentNode;
        currentNode=root;
        NodeComparingResult newsearchable = new NodeComparingResult(false, searchable);
        NodeComparingResult result= searchNodeByLinkBody(newsearchable);
        currentNode=tempNode;
        return result;
    }

    private NodeComparingResult searchNodeByLinkBody(NodeComparingResult searchable){
            MyBigNode tempnode = (MyBigNode)currentNode;
            MyBigNode searchableNode = (MyBigNode) searchable.getPrevious();

            if (tempnode.getRight()==searchableNode) return new NodeComparingResult(true, tempnode);
            if (tempnode.getLeft()==searchableNode) return new NodeComparingResult(false, tempnode);

        if (currentNode.compareTo(searchableNode)>0){
            if (currentHasLeft()){
                currentMoveLeft();
                return searchNodeByLinkBody(new NodeComparingResult(true, searchableNode));
            } else return new NodeComparingResult(false, tempnode);

        } else if (currentNode.compareTo(searchableNode)<0) {
            if (currentHasRight()){
                currentGetRight();
                return searchNodeByLinkBody(new NodeComparingResult(true, searchableNode));
            } else return new NodeComparingResult(false, tempnode);
        }
        return new NodeComparingResult(true, new MyBigNode(-1, null));
    }

    private MyNode searchFinalNode (boolean isRight, MyNode searchable){
        MyBigNode castedSearchable = (MyBigNode) searchable;
        if (isRight) {
            if (castedSearchable.getRight()!=null) {
                return searchFinalNode(isRight, castedSearchable.getRight());
            } else return castedSearchable;
        } else if (castedSearchable.getLeft()!= null) {
            return searchFinalNode(isRight, castedSearchable.getRight());
        } else return castedSearchable;
    }

    public boolean removeAll(Collection c) {
            boolean resultIsGood=true;
            boolean temporary;
            for (Object newObject:c){
                temporary = remove(newObject);
                if (temporary==false) resultIsGood=false;
            }
            return resultIsGood;
    }

    public boolean retainAll(Collection c) {
        Object tempObj;
        boolean forDeleting=true;
        int per=0;
        for (Object obj:toArray()){
            MyNode tempNode = (MyNode) obj;
            tempObj=((MyNode) obj).getData();

            for (Object cData:c){
                if (tempObj.equals(cData)) {
                    forDeleting = false;
                    per++;
                }
            }
            if (forDeleting){
                remove(tempObj);
                forDeleting=false;
            }
        }
        return (per==c.size());
    }

    public Object[] toArray() {
        Object[] arr = new Object[size()];
        int counter=0;
        for (int i=0; i<idCounter.size(); i++){
            if (idCounter.get(i)!=null) {
                arr[counter]=idCounter.get(i);
                counter++;
            }
        }
        return arr;
    }

    @Override
    public Iterator iterator() {
        return new DataIterator();
    }

    private class DataIterator implements Iterator {
        private MyNode iteratorCurrentNode;
        private Object[] iterableTree = toArray();
        private int itSize;
        private int counter;

        public DataIterator() {
            itSize=iterableTree.length;
        }

        @Override
        public boolean hasNext() {
            if (counter<itSize) {
                return true;
            } else return false;
        }

        @Override
        public Object next() {
            iteratorCurrentNode = (MyNode) iterableTree[counter];
            Object temp = iteratorCurrentNode.getData();
            counter++;
           return temp;
        }
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
