package entities;

import java.util.ArrayList;

public abstract class ArrayListContainer<S, T> {
    private S containedBy;
    private ArrayList<T> items;

    public ArrayListContainer(S containedBy) {
        this.containedBy = containedBy;
    }

    public ArrayListContainer(S containedBy, ArrayList<T> items) {
        this.containedBy = containedBy;
        this.items = items;
    }

    public void addItems(ArrayList<T> items) {
        this.items.addAll(items);
    }

    public void addItem(T item) {
        this.items.add(item);
    }

    public S getContainer() {
        return this.containedBy;
    }

    public ArrayList<T> getItems() {
        return this.items;
    }

    public T getItem(int index) {
        return this.items.get(index);
    }

    public int getSize() {
        return this.items.size();
    }

    public T removeItem() {
        return this.items.remove(0);
    }
}