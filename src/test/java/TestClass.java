package test.java;


import main.java.MyTreeSet;
import org.junit.Test;

/**
 * Created by Gvozd on 05.01.2016.
 */
public class TestClass {

    @Test
    public void CreateTree() {
        MyTreeSet tree = new MyTreeSet();
        tree.add(3);
        tree.add(2);
        tree.add(4);
        tree.add(2);
        tree.add(-2);
        tree.add(-4);
        tree.add(-1);
        tree.add(-2);

        System.out.println(tree.toString());
    }

}
