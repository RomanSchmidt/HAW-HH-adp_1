package adp_1;

import java.util.Iterator;

public class LinkedList<T> extends AMyList<T> {
    private Element _first = new Element(null);
    private Element _last = new Element(null);
    private int _size = 0;

    LinkedList() {
        this._first.setChild(this._last);
        this._last.setParent(this._first);
    }

    @Override
    public void push(T value) {
        this._prependToElement(this._last, new Element(value));
        ++this._size;
    }

    @Override
    public T pop() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        Element oldElement = this._last.getParent();
        oldElement.remove();
        --this._size;
        return oldElement.getValue();
    }

    @Override
    public void unshift(T value) {
        ++this._size;
        this._appendToElement(this._first, new Element(value));
    }

    @Override
    public T shift() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        Element oldElement = this._first.getChild();
        oldElement.remove();
        --this._size;
        return oldElement.getValue();
    }

    @Override
    public T drop(int positionToDelete) {
        this._checkIndexOutOfBounce(positionToDelete);

        Element current = this._first;
        for (int i = 0; i <= positionToDelete; ++i) {
            current = current.getChild();
        }
        current.remove();
        --this._size;
        return current.getValue();
    }

    private void _checkIndexOutOfBounce(int index) {
        if (this.isEmpty() || index >= this.length() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean drop(T element) {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        boolean found = false;
        Element current = this._first;
        for (int i = 0; i < this.length(); ++i) {
            current = current.getChild();
            if (element == current.getValue()) {
                found = true;
                current.remove();
                --this._size;
                break;
            }
        }
        return found;
    }

    @Override
    public boolean add(int positionToAdd, T value) {
        if (positionToAdd < 0 || positionToAdd > this.length()) {
            throw new IndexOutOfBoundsException();
        }

        if (positionToAdd == 0) {
            this.unshift(value);
            return true;
        }
        if (this.length() == positionToAdd) {
            this.push(value);
            return true;
        }
        Element current = this._first;
        for (int i = 0; i < positionToAdd; ++i) {
            current = current.getChild();
        }
        this._appendToElement(current, new Element(value));
        ++this._size;
        return true;
    }

    private void _appendToElement(Element element, Element newElement) {
        Element oldChild = element.getChild();
        element.setChild(newElement);
        oldChild.setParent(newElement);
        newElement.setParent(element);
        newElement.setChild(oldChild);
    }

    private void _prependToElement(Element element, Element newElement) {
        Element oldParent = element.getParent();
        element.setParent(newElement);
        oldParent.setChild(newElement);
        newElement.setParent(oldParent);
        newElement.setChild(element);
    }

    @Override
    public boolean contains(T element) {
        Element current = this._first;
        for (int i = 0; i < this.length(); ++i) {
            current = current.getChild();
            if (current.getValue() == element) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int elemPosition) {
        this._checkIndexOutOfBounce(elemPosition);

        Element current = this._first;
        for (int i = 0; i <= elemPosition; ++i) {
            current = current.getChild();
        }
        return current.getValue();
    }

    @Override
    public int length() {
        return this._size;
    }

    @Override
    public Iterator<T> getIterator() {
        return new ListIterator();
    }

    /**
     * Element to add attributes to each single value
     */
    private class Element {
        private Element _parent;
        private Element _child;
        private T _value;

        Element(T value) {
            this._value = value;
        }

        Element getParent() {
            return this._parent;
        }

        void setParent(Element element) {
            this._parent = element;
        }

        Element getChild() {
            return this._child;
        }

        void setChild(Element element) {
            this._child = element;
        }

        T getValue() {
            return this._value;
        }

        void remove() {
            if (null != this._child) {
                this._child.setParent(this._parent);
            }
            if (null != this._parent) {
                this._parent.setChild(this._child);
            }
        }
    }

    /**
     * just hasNext and next for this iterator
     */
    private class ListIterator implements Iterator<T> {
        private Element _current = _first;

        public boolean hasNext() {
            if(isEmpty()) {
                return false;
            }
            return this._current.getChild() != _last;
        }

        public T next() {
            if(this._current.getChild() == _last) {
                return null;
            }
            this._current = _current.getChild();
            return this._current.getValue();
        }
    }
}
