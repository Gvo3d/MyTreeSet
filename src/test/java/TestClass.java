package test.java;


import main.java.MyTreeSet;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Gvozd on 05.01.2016.
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
        System.out.println("RootNode: "+tree.currentToString());
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
        tree.add(3);

        tree.currentSetRoot();
        System.out.println(tree.currentToString());
        tree.currentGetLeft();
        System.out.println(tree.currentToString());


        System.out.println(tree.contains(3));
        tree.remove(3);
        System.out.println(tree.contains(3));

        tree.currentSetRoot();
        System.out.println(tree.currentToString());
        tree.currentGetLeft();
        System.out.println(tree.currentToString());


//        tree.currentGetLeft();
//        System.out.println(tree.currentToString());
    }

}
