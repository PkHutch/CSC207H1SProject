package entities.arraylistcontainers;

import entities.ArrayListContainer;
import entities.Warehouse;
import java.util.ArrayList;

public class Floor extends ArrayListContainer<Warehouse, Zone> {
    private final static int DEFAULT_ZONE_QUANTITY = 2;

    public Floor(Warehouse warehouse) {
        super(warehouse);

        for(int counter = 0; counter < DEFAULT_ZONE_QUANTITY; counter++) {
            this.addItem(new Zone(this));
        }
    }

    public Level getLevel(char zone, int aisle, int rack, int level) {
        return this.getItem(zone - 'A').getItem(aisle).getItem(rack).getItem(level);
    }
}