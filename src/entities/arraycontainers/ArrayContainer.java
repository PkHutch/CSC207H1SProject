// Defines the package.
package entities.arraycontainers;

/**
 * A ArrayContainer is a generic abstract class which contains a collection of objects, T, and
 * contains a collection of objects, S.
 */
public abstract class ArrayContainer<S, T> {
    // Defines instance variables.
    private S containedBy;
    private T[] items;

    // Defines the constructors.
    /**
     * The default constructor of a ArrayContainer, which only sets the parent of the container.
     *
     * @param containedBy the S, which is the parent type, is the object which contains the
     *        ArrayContainer.
     * @param items the T, the contained type, is the array of objects which this class
     *        contains.
     */
    public ArrayContainer(S containedBy, T[] items) {
        System.out.println("Constructing ArrayContainer " + this.toString() + " with argument " +
            "containedBy as " + containedBy.toString() + ".");
        this.containedBy = containedBy;
        this.items = items;
    }

    // Defines the functional methods.
    /**
     * The getContainer method of ArrayContainer returns the object of the type of the
     * ArrayContainer's parent which contains it.
     *
     * @return the object which contains the ArrayContainer.
     */
    public S getContainer() {
        System.out.println("Calling getContainer of ArrayContainer " + this.toString() + ".");
        System.out.println("    Returning " + this.containedBy.toString() + ".");
        return this.containedBy;
    }

    /**
     * The getItems method of ArrayContainer returns the ArrayContainer's items.
     *
     * @return the array of T objects that the ArrayContainer contains, where T is the type of
     *         object that the ArrayContainer contains.
     */
    public T[] getItems() {
        System.out.println("Calling getItems of ArrayContainer " + this.toString() + ".");
        System.out.println("    Returning " + this.items.toString() + ".");
        return this.items;
    }

    /**
     * The getItem method of ArrayContainer returns the object at the ArrayContainer's given index,
     * of the type which of object which the ArrayContainer contains.
     *
     * @param index the int which specifies the index which to grab of the ArrayContainer, it
     *        should be noted that this assumes the object exists at that index and there will be
     *        troubles if it does not.
     * @return the object which is stored at index, of the type T, which is the type of object
     *         that the ArrayContainer contains.
     */
    public T getItem(int index) {
        System.out.println("Calling getItem of ArrayContainer " + this.toString() + " with " +
            "argument index as " + Integer.toString(index) + ".");
        System.out.println("    Returning " + this.items[index].toString() + ".");
        return this.items[index];
    }

    /**
     * The getSize method of ArrayContainer returns the amount of objects which the ArrayContainer
     * contains.
     *
     * @return the int, which represents the amount of objects that ArrayContainer contains.
     */
    public int getSize() {
        System.out.println("Calling getSize of ArrayContainer " + this.toString() + ".");
        System.out.println("    Returning " + Integer.toString(this.items.length) + ".");
        return this.items.length;
    }

    /**
     * The setItem method of ArrayContainer sets the item at the given index, this should not be
     * utilized at any time other than construction.
     *
     * @param index the int at which the item is to be set.
     * @param item the T object to be set.
     */
    public void setItem(int index, T item) {
        System.out.println("Calling getSize of ArrayContainer " + this.toString() + ".");
        System.out.println("    Returning " + Integer.toString(this.items.length) + ".");
        this.items[index] = item;
    }
}