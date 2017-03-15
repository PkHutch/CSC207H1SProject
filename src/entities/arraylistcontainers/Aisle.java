package entities.arraylistcontainers;

import entities.ArrayListContainer;
import java.util.ArrayList;

public class Aisle extends ArrayListContainer<Zone, Rack>{
    private final static int DEFAULT_RACK_QUANTITY = 3;

    public Aisle(Zone zone) {
        super(zone);

        for(int counter = 0; counter < DEFAULT_RACK_QUANTITY; counter++) {
            this.addItem(new Rack(this));
        }
    }
}