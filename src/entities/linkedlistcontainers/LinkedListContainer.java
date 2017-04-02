// Defines package.
package entities.linkedlistcontainers;

// Defines imports.
import java.util.LinkedList;

/**
 * The LinkedListContainer is effectively a stack of an arbitrary type and maximum size.
 */
public abstract class LinkedListContainer<T> {
    // Defines variables.
    private int currentInventorySize;
    private final LinkedList<T> inventory;
    private final int maxInventorySize;

    // Defines contructor methods.
    /**
     * The main constructor, creates a LinkedListContainer with the max size being equal to
     * inventorySize.
     *
     * @param inventorySize the integer which will be assigned to the max inventory size.
     */
    public LinkedListContainer(int inventorySize) {
        this.inventory = new LinkedList<>();
        this.maxInventorySize = inventorySize;
    }

    // Defines functional methods.
    /**
     * The method for adding an item to the inventory, adds it to the last position.
     *
     * @param item the object of type T which will be assigned to the max inventory size.
     * @throws IllegalArgumentException the exception is thrown when an item is added and the
     *         LinkedListContainer is at max capacity.
     */
    public void addItem(T item) {
        System.out.println("Calling addItem of LinkedListContainer " + this.toString() + " with" +
            " argument item as " + item.toString() + ".");
        if(this.currentInventorySize == this.maxInventorySize) {
            throw new IllegalArgumentException("A LinkedListContainer is at max capacity but " +
                          "something is trying to add to it.");
        } else {
            this.currentInventorySize++;
            this.inventory.add(item);
        }
    }

    /**
     * The method for returning the inventory.
     *
     * @return the return is the LinkedList stored in the LinkedListContainer.
     */
    public LinkedList<T> getInventory() {
        return this.inventory;
    }

    /**
     * The method which returns whether or not the LinkedListContainer is empty.
     *
     * @return the boolean representing whether or not the LinkedListContainer is empty.
     */
    public boolean isEmpty() {
        return this.inventory.size() == 0;
    }

    /**
     * The removeItems method of LinkedListContainer pops all items from the inventory.
     *
     * @return the LinkedList<T> of the inventory that will no longer be contained in the
     *         LinkedListContainers inventory.
     */
    public LinkedList<T> popItems() {
        if(this.isEmpty()) {
            throw new IllegalArgumentException("A LinkedListContainer is empty but something is" +
                          " attempting to remove an item from it.");
        } else {
            LinkedList<T> returnedList = new LinkedList<>();
            while(this.inventory.size() > 0) {
                returnedList.add(this.inventory.pop());
                this.currentInventorySize--;
            }
            return returnedList;
        }
    }
}
