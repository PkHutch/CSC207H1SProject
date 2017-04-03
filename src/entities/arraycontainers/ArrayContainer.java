// Defines the package.
package entities.arraycontainers;

/**
 * A ArrayContainer is a generic abstract class which contains a collection of
 * objects, T, and contains a collection of objects, S.
 */
public abstract class ArrayContainer<S, T> {
    // Defines instance variables.
    private S containedBy;
    private T[] items;

    // Defines the constructors.
    /**
     * The default constructor of a ArrayContainer, which only sets the parent
     * of the container.
     *
     * @param containedBy
     *            the S, which is the parent type, is the object which contains
     *            the ArrayContainer.
     * @param items
     *            the T, the contained type, is the array of objects which this
     *            class contains.
     */
    public ArrayContainer(S containedBy, T[] items) {
        this.containedBy = containedBy;
        this.items = items;
    }

    // Defines the functional methods.
    /**
     * The getContainer method of ArrayContainer returns the object of the type
     * of the ArrayContainer's parent which contains it.
     *
     * @return the object which contains the ArrayContainer.
     */
    public S getContainer() {
        return this.containedBy;
    }

    /**
     * The getItems method of ArrayContainer returns the ArrayContainer's items.
     *
     * @return the array of T objects that the ArrayContainer contains, where T
     *         is the type of object that the ArrayContainer contains.
     */
    protected T[] getItems() {
        return this.items;
    }

    /**
     * The getIndex method of ArrayContainer returns the index of the Object
     * contained in the ArrayContainer. It should be noted this uses == for
     * comparison.
     *
     * @param object
     *            the object to be searched for.
     * @return the index of the object, if it exists, otherwise it will return
     *         negative one.
     */
    public int getIndex(T object) {
        for (int index = 0; index < this.items.length; index++) {
            if (this.items[index] == object) {
                return index;
            }
        }

        return -1;
    }

    /**
     * The getItem method of ArrayContainer returns the object at the
     * ArrayContainer's given index, of the type which of object which the
     * ArrayContainer contains.
     *
     * @param index
     *            the int which specifies the index which to grab of the
     *            ArrayContainer, it should be noted that this assumes the
     *            object exists at that index and there will be troubles if it
     *            does not.
     * @return the object which is stored at index, of the type T, which is the
     *         type of object that the ArrayContainer contains.
     */
    public T getItem(int index) {
        return this.items[index];
    }

    /**
     * The getSize method of ArrayContainer returns the amount of objects which
     * the ArrayContainer contains.
     *
     * @return the int, which represents the amount of objects that
     *         ArrayContainer contains.
     */
    protected int getSize() {
        return this.items.length;
    }

    /**
     * The setItem method of ArrayContainer sets the item at the given index,
     * this should not be utilized at any time other than construction.
     *
     * @param index
     *            the int at which the item is to be set.
     * @param item
     *            the T object to be set.
     */
    protected void setItem(int index, T item) {
        this.items[index] = item;
    }
}