package adp_1;

import java.util.Iterator;

public abstract class AMyList<T> {

    /**
     * add element to the bottom
     */
    public abstract void push(T element);

    /**
     * remove last Element
     */
    public abstract T pop();

    /**
     * add element to the top
     */
    public abstract void unshift(T element);

    /**
     * remove the first element
     */
    public abstract T shift();

    /**
     * remove element on position i
     */
    public abstract T drop(int i);

    /**
     * remove element on position i
     */
    public abstract boolean drop(T element);

    /**
     * add on position i
     */
    public abstract boolean add(int i, T element);

    /**
     * does element exist
     */
    public abstract boolean contains(T element);

    /**
     * get element from position i
     */
    public abstract T get(int i);

    /**
     * number of elements
     */
    public abstract int length();

    /**
     * has no elements?
     */
    public boolean isEmpty() {
        return this.length() == 0;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        this.getIterator().forEachRemaining(element -> {
            buffer.append(element.toString());
            buffer.append(", ");
        });
        if (this.length() > 0) {
            buffer.delete(buffer.length() - 2, buffer.length());
        }
        return buffer.toString();
    }

    /**
     * get an iterator
     */
    public abstract Iterator<T> getIterator();
}
