package entities.arraylistcontainers;

import entities.ArrayListContainer;
import java.util.ArrayList;

public class Rack extends ArrayListContainer<Aisle, Level> {
    private final static int DEFAULT_LEVEL_QUANTITY = 4;

    public Rack(Aisle aisle) {
        super(aisle);

        for(int counter = 0; counter < DEFAULT_LEVEL_QUANTITY; counter++) {
            this.addItem(new Level(this));
        }
    }
}
