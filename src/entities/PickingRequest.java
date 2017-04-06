// Defines the package.
package entities;

// Defines the imports.
import java.lang.IllegalArgumentException;

/**
 * The PickingRequest class handles a single picking request, which consists of
 * eight orders by default. If the status is zero, the picking request is
 * unassigned. If the status is one, the picking request is assigned. If the
 * status is two, the picking request is awaiting sequencing. If the status is
 * three, the picking request is sequenceable.
 */
public class PickingRequest {
    // Defines the instance variables.
    private final static int DEFAULT_PICKING_REQUEST_SIZE = 8;
    private final Integer[] skus;
    private int status;

    // Defines the constructors.
    /**
     * The only constructor for a PickingRequest.
     *
     * @param skus
     *            the Integer[] which is an array of SKUs and must be a size of
     *            eight.
     */
    public PickingRequest(Integer[] skus) {

        if (skus.length != DEFAULT_PICKING_REQUEST_SIZE) {
            throw new IllegalArgumentException(
                    "An attempt was made to make a PickingRequest " + "with an incorrect size of SKUs.");
        }

        this.skus = skus;
        this.status = 0;
    }

    // Defines the functional methods.
    /**
     * The getSKUs method of PickingRequest returns the Integer[] of SKUs that
     * this PickingRequest holds.
     *
     * @return the Integer[] that contains the SKUs of the PickingRequest.
     */
    public Integer[] getSKUs() {
        return this.skus;
    }

    /**
     * The getStatus method of PickingRequest returns the int status of the
     * PickingRequest, where if the status is zero, the picking request is
     * unassigned. If the status is one, the picking request is assigned. If the
     * status is two, the picking request is awaiting sequencing. If the status
     * is three, the picking request is sequenceable.
     *
     * @return the int which represents the status.
     */
    public int getStatus() {
        return status;
    }

    /**
     * The setStatus method of PickingRequest returns the int status of the
     * PickingRequest, where if the status is zero, the picking request is
     * unassigned. If the status is one, the picking request is assigned. If the
     * status is two, the picking request is awaiting sequencing. If the status
     * is three, the picking request is sequenceable.
     *
     * @param status
     *            the int which represents the status of the PickingRequest.
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
