package adp_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public abstract class ATestList {
    protected abstract AMyList<String> getList();

    protected abstract void createList();

    @BeforeEach
    void setUp() {
        this.createList();
    }

    @Test
    void push() {
        Assertions.assertTrue(this.getList().isEmpty());
        this.getList().push("1");
        Assertions.assertFalse(this.getList().isEmpty());
        Assertions.assertEquals("1", this.getList().get(0));
        this.getList().push("2");
        Assertions.assertEquals("1", this.getList().get(0));
        Assertions.assertEquals("2", this.getList().get(1));
    }

    @Test
    void pop() {
        Assertions.assertTrue(this.getList().isEmpty());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.getList().pop();
        });
        this.getList().push("1");
        this.getList().push("2");
        this.getList().push("3");
        Assertions.assertFalse(this.getList().isEmpty());
        Assertions.assertEquals("3", this.getList().pop());
        Assertions.assertFalse(this.getList().isEmpty());
        Assertions.assertEquals("2", this.getList().pop());
        Assertions.assertFalse(this.getList().isEmpty());
        Assertions.assertEquals("1", this.getList().pop());
        Assertions.assertTrue(this.getList().isEmpty());
    }

    @Test
    void unshift() {
        Assertions.assertTrue(this.getList().isEmpty());
        this.getList().unshift("1");
        Assertions.assertFalse(this.getList().isEmpty());
        Assertions.assertEquals("1", this.getList().get(0));
        this.getList().unshift("2");
        Assertions.assertEquals("2", this.getList().get(0));
        Assertions.assertEquals("1", this.getList().get(1));
    }

    @Test
    void shift() {
        Assertions.assertTrue(this.getList().isEmpty());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.getList().shift();
        });
        this.getList().push("1");
        this.getList().push("2");
        this.getList().push("3");
        Assertions.assertFalse(this.getList().isEmpty());
        Assertions.assertEquals("1", this.getList().shift());
        Assertions.assertFalse(this.getList().isEmpty());
        Assertions.assertEquals("2", this.getList().shift());
        Assertions.assertFalse(this.getList().isEmpty());
        Assertions.assertEquals("3", this.getList().shift());
        Assertions.assertTrue(this.getList().isEmpty());
    }

    @Test
    void dropI() {
        Assertions.assertTrue(this.getList().isEmpty());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.getList().drop(0);
        });
        Assertions.assertEquals(0, this.getList().length());
        this.getList().push("1");
        Assertions.assertEquals(1, this.getList().length());
        this.getList().push("2");
        Assertions.assertEquals(2, this.getList().length());
        this.getList().push("3");
        Assertions.assertEquals(3, this.getList().length());
        this.getList().push("4");
        Assertions.assertEquals(4, this.getList().length());
        Assertions.assertFalse(this.getList().isEmpty());
        Assertions.assertEquals("3", this.getList().drop(2));
        Assertions.assertEquals(3, this.getList().length());
        Assertions.assertEquals("4", this.getList().drop(2));
        Assertions.assertEquals(2, this.getList().length());

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.getList().drop(2);
        });

        Assertions.assertEquals("1", this.getList().drop(0));
        Assertions.assertEquals(1, this.getList().length());
        Assertions.assertEquals("2", this.getList().drop(0));
        Assertions.assertEquals(0, this.getList().length());
        Assertions.assertTrue(this.getList().isEmpty());
    }

    @Test
    void dropT() {
        Assertions.assertTrue(this.getList().isEmpty());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.getList().drop("1");
        });
        this.getList().push("1");
        this.getList().push("2");
        this.getList().push("3");
        this.getList().push("4");
        Assertions.assertFalse(this.getList().isEmpty());
        Assertions.assertFalse(this.getList().drop("10"));
        Assertions.assertTrue(this.getList().drop("4"));
        Assertions.assertFalse(this.getList().isEmpty());
        Assertions.assertTrue(this.getList().drop("1"));
        Assertions.assertTrue(this.getList().drop("2"));
        Assertions.assertTrue(this.getList().drop("3"));
        Assertions.assertTrue(this.getList().isEmpty());
    }

    @Test
    void add() {
        Assertions.assertTrue(this.getList().isEmpty());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.getList().add(5, "a");
        });
        Assertions.assertEquals(0, this.getList().length());
        Assertions.assertTrue(this.getList().isEmpty());
        Assertions.assertTrue(this.getList().add(0, "a"));
        Assertions.assertFalse(this.getList().isEmpty());
        Assertions.assertEquals(1, this.getList().length());
        Assertions.assertEquals("a", this.getList().toString());
        Assertions.assertTrue(this.getList().add(1, "d"));
        Assertions.assertEquals("a, d", this.getList().toString());
        Assertions.assertTrue(this.getList().add(1, "b"));
        Assertions.assertEquals("a, b, d", this.getList().toString());
        Assertions.assertTrue(this.getList().add(2, "c"));
        Assertions.assertEquals("a, b, c, d", this.getList().toString());
        Assertions.assertEquals("a", this.getList().get(0));
        Assertions.assertEquals("b", this.getList().get(1));
        Assertions.assertEquals("c", this.getList().get(2));
        Assertions.assertEquals("d", this.getList().get(3));
        Assertions.assertEquals(4, this.getList().length());
    }

    @Test
    void contains() {
        Assertions.assertFalse(this.getList().contains("a"));
        this.getList().push("a");
        this.getList().push("b");
        this.getList().push("c");
        this.getList().push("d");
        Assertions.assertFalse(this.getList().contains("e"));
        Assertions.assertTrue(this.getList().contains("c"));
        Assertions.assertTrue(this.getList().contains("d"));
        Assertions.assertTrue(this.getList().contains("a"));
    }

    @Test
    void testToString() {
        Assertions.assertEquals("", this.getList().toString());
        this.getList().push("a");
        this.getList().push("b");
        this.getList().push("c");
        this.getList().push("d");
        Assertions.assertEquals("a, b, c, d", this.getList().toString());
    }


    @Test
    void getIterator() {
        Assertions.assertFalse(this.getList().contains("a"));
        this.getList().push("a");
        this.getList().push("b");
        this.getList().push("c");
        this.getList().push("d");
        Iterator<String> inti = this.getList().getIterator();
        int i = 0;
        while (inti.hasNext()) {
            Assertions.assertEquals(this.getList().get(i++), inti.next());
        }
        Assertions.assertEquals(i, this.getList().length());
    }
}
