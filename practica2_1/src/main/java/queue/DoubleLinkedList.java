package queue;

import java.util.Comparator;

public class DoubleLinkedList<T> implements DoubleLinkedQueue<T> {
    private LinkedNode<T> first;
    private LinkedNode<T> last;
    private int size;

    public DoubleLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void prepend(T value) {
        LinkedNode<T> node = new LinkedNode<>(value, null, first);
        if (first == null) {
            first = node;
            last = node;
        } else {
            first.setPrevious(node);
            node.setNext(first);
            first = node;
        }
        size++;
    }

    @Override
    public void append(T value) {
        LinkedNode<T> node = new LinkedNode<>(value, last, null);
        if (last == null) {
            first = node;
            last = node;
        } else {
            last.setNext(node);
            node.setPrevious(last);
            last = node;
        }
        size++;
    }

    @Override
    public void deleteFirst() {
        if (first != null) {
            first = first.getNext();
            if (first != null) {
                first.setPrevious(null);
            } else {
                last = null;
            }
            size--;
        } else {
            throw new DoubleLinkedQueueException("Cannot delete from an empty deque.");
        }
    }

    @Override
    public void deleteLast() {
        if (last != null) {
            last = last.getPrevious();
            if (last != null) {
                last.setNext(null);
            } else {
                first = null;
            }
            size--;
        } else {
            throw new DoubleLinkedQueueException("Cannot delete from an empty deque.");
        }
    }

    @Override
    public T first() {
        if (first != null) {
            return first.getItem();
        } else {
            throw new DoubleLinkedQueueException("Deque is empty, cannot retrieve first element.");
        }
    }

    @Override
    public T last() {
        if (last != null) {
            return last.getItem();
        } else {
            throw new DoubleLinkedQueueException("Deque is empty, cannot retrieve last element.");
        }
    }

    @Override
    public int size() {
        return size;
    }


}
