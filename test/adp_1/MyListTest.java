package adp_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class MyListTest {

    private MyList<String> _testList = new MyList<>();

    @Test
    void push() {
        Assertions.assertTrue(this._testList.isEmpty());
        this._testList.push("1");
        Assertions.assertFalse(this._testList.isEmpty());
        Assertions.assertEquals("1", this._testList.get(0));
        this._testList.push("2");
        Assertions.assertEquals("1", this._testList.get(0));
        Assertions.assertEquals("2", this._testList.get(1));
    }

    @Test
    void pop() {
        Assertions.assertTrue(this._testList.isEmpty());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this._testList.pop();
        });
        this._testList.push("1");
        this._testList.push("2");
        this._testList.push("3");
        Assertions.assertFalse(this._testList.isEmpty());
        Assertions.assertEquals("3", this._testList.pop());
        Assertions.assertFalse(this._testList.isEmpty());
        Assertions.assertEquals("2", this._testList.pop());
        Assertions.assertFalse(this._testList.isEmpty());
        Assertions.assertEquals("1", this._testList.pop());
        Assertions.assertTrue(this._testList.isEmpty());
    }

    @Test
    void unshift() {
        Assertions.assertTrue(this._testList.isEmpty());
        this._testList.unshift("1");
        Assertions.assertFalse(this._testList.isEmpty());
        Assertions.assertEquals("1", this._testList.get(0));
        this._testList.unshift("2");
        Assertions.assertEquals("2", this._testList.get(0));
        Assertions.assertEquals("1", this._testList.get(1));
    }

    @Test
    void shift() {
        Assertions.assertTrue(this._testList.isEmpty());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this._testList.shift();
        });
        this._testList.push("1");
        this._testList.push("2");
        this._testList.push("3");
        Assertions.assertFalse(this._testList.isEmpty());
        Assertions.assertEquals("1", this._testList.shift());
        Assertions.assertFalse(this._testList.isEmpty());
        Assertions.assertEquals("2", this._testList.shift());
        Assertions.assertFalse(this._testList.isEmpty());
        Assertions.assertEquals("3", this._testList.shift());
        Assertions.assertTrue(this._testList.isEmpty());
    }

    @Test
    void dropI() {
        Assertions.assertTrue(this._testList.isEmpty());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this._testList.drop(0);
        });
        Assertions.assertEquals(0, this._testList.length());
        this._testList.push("1");
        Assertions.assertEquals(1, this._testList.length());
        this._testList.push("2");
        Assertions.assertEquals(2, this._testList.length());
        this._testList.push("3");
        Assertions.assertEquals(3, this._testList.length());
        this._testList.push("4");
        Assertions.assertEquals(4, this._testList.length());
        Assertions.assertFalse(this._testList.isEmpty());
        Assertions.assertEquals("3", this._testList.drop(2));
        Assertions.assertEquals(3, this._testList.length());
        Assertions.assertEquals("4", this._testList.drop(2));
        Assertions.assertEquals(2, this._testList.length());

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this._testList.drop(2);
        });

        Assertions.assertEquals("1", this._testList.drop(0));
        Assertions.assertEquals(1, this._testList.length());
        Assertions.assertEquals("2", this._testList.drop(0));
        Assertions.assertEquals(0, this._testList.length());
        Assertions.assertTrue(this._testList.isEmpty());
    }

    @Test
    void dropT() {
        Assertions.assertTrue(this._testList.isEmpty());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this._testList.drop("1");
        });
        this._testList.push("1");
        this._testList.push("2");
        this._testList.push("3");
        this._testList.push("4");
        Assertions.assertFalse(this._testList.isEmpty());
        Assertions.assertFalse(this._testList.drop("10"));
        Assertions.assertTrue(this._testList.drop("4"));
        Assertions.assertFalse(this._testList.isEmpty());
        Assertions.assertTrue(this._testList.drop("1"));
        Assertions.assertTrue(this._testList.drop("2"));
        Assertions.assertTrue(this._testList.drop("3"));
        Assertions.assertTrue(this._testList.isEmpty());
    }

    @Test
    void add() {
        Assertions.assertTrue(this._testList.isEmpty());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this._testList.add(5, "a");
        });
        Assertions.assertEquals(0, this._testList.length());
        Assertions.assertTrue(this._testList.isEmpty());
        Assertions.assertTrue(this._testList.add(0, "a"));
        Assertions.assertFalse(this._testList.isEmpty());
        Assertions.assertEquals(1, this._testList.length());
        Assertions.assertTrue(this._testList.add(1, "d"));
        Assertions.assertTrue(this._testList.add(1, "b"));
        Assertions.assertTrue(this._testList.add(2, "c"));
        Assertions.assertEquals("a", this._testList.get(0));
        Assertions.assertEquals("b", this._testList.get(1));
        Assertions.assertEquals("c", this._testList.get(2));
        Assertions.assertEquals("d", this._testList.get(3));
        Assertions.assertEquals(4, this._testList.length());
    }

    @Test
    void contains() {
        Assertions.assertFalse(this._testList.contains("a"));
        this._testList.push("a");
        this._testList.push("b");
        this._testList.push("c");
        this._testList.push("d");
        Assertions.assertFalse(this._testList.contains("e"));
        Assertions.assertTrue(this._testList.contains("c"));
        Assertions.assertTrue(this._testList.contains("d"));
        Assertions.assertTrue(this._testList.contains("a"));
    }

    @Test
    void testToString() {
        Assertions.assertEquals("", this._testList.toString());
        this._testList.push("a");
        this._testList.push("b");
        this._testList.push("c");
        this._testList.push("d");
        Assertions.assertEquals("a, b, c, d", this._testList.toString());
    }


    @Test
    void getIterator() {
        Assertions.assertFalse(this._testList.contains("a"));
        this._testList.push("a");
        this._testList.push("b");
        this._testList.push("c");
        this._testList.push("d");
        Iterator<String> inti = this._testList.getIterator();
        int i = 0;
        while (inti.hasNext()) {
            Assertions.assertEquals(this._testList.get(i++), inti.next());
        }
        Assertions.assertEquals(i, this._testList.length());
    }

    @BeforeEach
    void setUp() {
        this._testList = new MyList<>();
    }
}