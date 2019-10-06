package adp_1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * seems to not be necessary to implement this with arrays, so a wrapper around an ArrayList
 */
public class MyList<T> extends AMyList<T> {

    private ArrayList<T> _elements = new ArrayList<>();

    @Override
    public void push(T element) {
        this._elements.add(element);
    }

    @Override
    public T pop() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return this._elements.remove(this.length() - 1);
    }

    @Override
    public void unshift(T element) {
        this._elements.add(0, element);
    }

    @Override
    public T shift() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return this._elements.remove(0);
    }

    @Override
    public T drop(int i) {
        if (this.isEmpty() || i > this.length() - 1) {
            throw new IndexOutOfBoundsException();
        }
        return this._elements.remove(i);
    }

    @Override
    public boolean drop(T element) {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return !!this._elements.remove(element);
    }

    @Override
    public boolean add(int i, T element) {
        if (this.length() == i) {
            this.push(element);
            return true;
        } else if (i < this.length()) {
            this._elements.add(i, element);
            return true;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean contains(T element) {
        return this._elements.contains(element);
    }

    @Override
    public T get(int i) {
        if(i >= this.length()){
            throw new IndexOutOfBoundsException();
        }
        return this._elements.get(i);
    }

    @Override
    public int length() {
        return this._elements.size();
    }

    @Override
    public Iterator<T> getIterator() {
        return this._elements.iterator();
    }
}
