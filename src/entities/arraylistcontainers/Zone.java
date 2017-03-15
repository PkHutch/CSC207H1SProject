package entities.arraylistcontainers;

import entities.ArrayListContainer;
import java.util.ArrayList;

public class Zone extends ArrayListContainer<Floor, Aisle> {
    private final static int DEFAULT_AISLE_QUANTITY = 3;

    public Zone(Floor floor) {
        super(floor);

        for(int counter = 0; counter < DEFAULT_AISLE_QUANTITY; counter++) {
            this.addItem(new Aisle(this));
        }
    }
}
