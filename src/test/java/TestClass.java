package test.java;


import main.java.MyTreeSet;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Yakimov Denis on 05.01.2016.
 */
public class TestClass {

    @Test
    public void CreateTree() {
        MyTreeSet tree = new MyTreeSet();
        Assert.assertEquals("Creating Tree, asserting size", true, tree.isEmpty());
        System.out.println("Creating and method isEmpty tested successfully.");
    }


    @Test
    public void TreeAdding(){
        MyTreeSet tree = new MyTreeSet();
        tree.add(3);
        Assert.assertEquals("Creating Tree, asserting size after first element", 1, tree.size());
        ArrayList newArray = new ArrayList();
        newArray.add(5);
        newArray.add(2);
        newArray.add(1);
        newArray.add(2);
        tree.addAll(newArray);
        Assert.assertEquals("Asserting size after addAll", 5, tree.size());
        System.out.println("Adding (add, addAll) tested successfully.");
    }

    @Test
    public void TreeCurrentMethods(){
        MyTreeSet tree = new MyTreeSet();
        tree.add(3);
        tree.add(2);
        tree.add(6);
        tree.add(5);
        tree.add(5);
        tree.add(4);
        tree.add(2);
        tree.add(-2);
        tree.add(-4);
        tree.add(-1);
        tree.add(-2);


        tree.currentSetRoot();
        Assert.assertEquals("Asserting first element", 3, tree.currentGetData());
        tree.currentGetRight();
        tree.currentGetLeft();
        tree.currentGetCenter();
        Assert.assertEquals("Asserting element", 5, tree.currentGetData());
        tree.currentSetRoot();
        tree.currentToString();
        Assert.assertEquals("Asserting first element", 3, tree.currentGetData());
        tree.currentGetLeft();
        tree.currentGetLeft();
        tree.currentGetRight();
        Assert.assertEquals("Asserting element", -1, tree.currentGetData());
        System.out.println("Testing CurrentMethods ends successfully.");
    }

    @Test
    public void TreeContains(){
        MyTreeSet tree = new MyTreeSet();
        tree.add(3);
        tree.add(5);
        tree.add(4);
        tree.add(2);
        tree.add(-3);
        tree.add(6);
        tree.add(-2);
        tree.add(-1);
        tree.add(-4);
        tree.add(1);
        tree.add(-4);


        ArrayList newArray = new ArrayList();
        newArray.add(-3);
        newArray.add(2);
        newArray.add(-1);
        newArray.add(1);

        boolean singleContains = tree.contains(6);
        boolean multipleContains = tree.containsAll(newArray);

        newArray.add(8);
        boolean multipleFalseContains = tree.containsAll(newArray);

        Assert.assertEquals("Asserting single containable item", true, singleContains);
        Assert.assertEquals("Asserting containable array", true, multipleContains);
        Assert.assertEquals("Asserting not a containable array", false, multipleFalseContains);

        System.out.println("Contains methods tested successfully.");
    }

    @Test
    public void TreeRemoving(){
        MyTreeSet tree = new MyTreeSet();
        tree.add(3);
        tree.add(5);
        tree.add(4);
        tree.add(2);
        tree.add(-3);
        tree.add(7);
        tree.add(-2);
        tree.add(-1);
        tree.add(-4);
        tree.add(1);
        tree.add(-4);
        tree.add(8);
        tree.add(7);


        ArrayList newArray = new ArrayList();
        newArray.add(8);
        newArray.add(-2);
        newArray.add(-4);
        newArray.add(3);

        boolean beforeContains = tree.contains(4);
        tree.remove(4);
        boolean afterContains = tree.contains(4);
        tree.removeAll(newArray);



        Assert.assertEquals("Asserting deletable containable item", true, beforeContains);
        Assert.assertEquals("Asserting no deletable containable item", false, afterContains);

        System.out.println("Remove methods are passed.");
    }

    @Test
    public void TreeIterating(){
        MyTreeSet tree = new MyTreeSet();
        tree.add(3);
        ArrayList newArray = new ArrayList();
        newArray.add(5);
        newArray.add(2);
        newArray.add(1);
        newArray.add(2);
        tree.addAll(newArray);
        int counter=0;

        for (Object obj:tree){
            counter=counter+(int)obj;
        }

        Assert.assertEquals("Asserting iterable forEach", 13, counter);
        System.out.println("Iteration tested successfully.");
    }

}
