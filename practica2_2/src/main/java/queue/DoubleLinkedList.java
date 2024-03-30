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

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        LinkedNode<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getItem();
    }

    @Override
    public boolean contains(T value) {
        LinkedNode<T> current = first;
        while (current != null) {
            if (current.getItem().equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void remove(T value) {
        LinkedNode<T> current = first;
        while (current != null) {
            if (current.getItem().equals(value)) {
                LinkedNode<T> prev = current.getPrevious();
                LinkedNode<T> next = current.getNext();
                if (prev != null) {
                    prev.setNext(next);
                } else {
                    first = next;
                }
                if (next != null) {
                    next.setPrevious(prev);
                } else {
                    last = prev;
                }
                size--;
                return; // Assuming only one occurrence needs to be removed
            }
            current = current.getNext();
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        // Using Bubble Sort algorithm for in-place sorting
        for (LinkedNode<T> i = first; i != null; i = i.getNext()) {
            for (LinkedNode<T> j = first; j.getNext() != null; j = j.getNext()) {
                if (comparator.compare(j.getItem(), j.getNext().getItem()) > 0) {
                    // Swap j and j.next
                    T temp = j.getItem();
                    j.setItem(j.getNext().getItem());
                    j.getNext().setItem(temp);
                }
            }
        }
    }
}
